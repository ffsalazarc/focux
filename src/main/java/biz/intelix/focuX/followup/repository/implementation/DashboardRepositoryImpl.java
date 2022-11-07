package biz.intelix.focuX.followup.repository.implementation;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.TupleElement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.engine.spi.EntityUniqueKey;

import biz.intelix.focuX.followup.model.Request;
import biz.intelix.focuX.followup.model.dto.PieCharts;
import biz.intelix.focuX.followup.model.dto.ResponseColumnCharts;
import biz.intelix.focuX.followup.model.dto.ColumnCharts;
import biz.intelix.focuX.followup.model.dto.GraphAnswer;
import biz.intelix.focuX.followup.repository.DashboardRepository;

public class DashboardRepositoryImpl implements DashboardRepository {
    
    private final EntityManager entityManager;
    private static Logger log = LogManager.getLogger(DashboardRepositoryImpl.class);
    public DashboardRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    @Override
    public List<Request> getActiveRequestsLastYear() {
        String query = "SELECT r from Request r WHERE r.dateRealEnd IS NULL";
        Query q = entityManager.createQuery(query, Request.class);
        return q.getResultList();        
    }

    @Override
    public List<GraphAnswer> cumulativeSumActiveRequests(Date dateInit, Date dateEnd) {

        String queryInactives = "WITH cte AS ( SELECT \n" + 
        "        date_trunc('month', \n" + 
        "        r.date_real_end) AS mon, \n" + 
        "       date_trunc('month', r.date_real_end), \n" + 
        "        count(r.id) AS mon_sum \n" + 
        "    FROM \n" + 
        "        \"FOLLOWUP\".\"CAT_REQUEST\" r \n" + 
        "    GROUP  BY \n" + 
        "        1     )   \n" + 
        "    SELECT \n" + 
        "        to_char(mon, 'YYYY-mm') AS mon_text, \n" + 
        "        COALESCE(sum(c.mon_sum)  OVER (ORDER BY mon),0) AS running_sum \n" + 
        "    FROM \n" + 
        "        generate_series(:dateInit, :dateEnd,interval '1 month') mon \n" + 
        "    LEFT   JOIN \n" + 
        "        cte c USING (mon) \n" + 
        "    ORDER  BY \n" + 
        "        mon";
        
        Query qInactives = entityManager.createNativeQuery(queryInactives, Tuple.class);
        qInactives.setParameter("dateInit", dateInit);
        qInactives.setParameter("dateEnd", dateEnd);        
        List<Tuple> listInactives = qInactives.getResultList();
        
        String queryActives = queryInactives.replace("date_real_end", "date_init");
        Query qActives = entityManager.createNativeQuery(queryActives, Tuple.class);
        qActives.setParameter("dateInit", dateInit);
        qActives.setParameter("dateEnd", dateEnd);        
        List<Tuple> listActives = qActives.getResultList();
        List<GraphAnswer> data = new ArrayList<>();
        for (int i = 0; i < listActives.size(); i++) {
            Tuple active = listActives.get(i);
            Tuple inactive = listInactives.get(i);
            GraphAnswer dataPoint = new GraphAnswer(active.get("mon_text").toString(),  toInteger(active.get("running_sum")) - toInteger(inactive.get("running_sum")));
            data.add(dataPoint);
        }

        return data;
    }

    private Integer toInteger(Object num) {
        BigDecimal bd = (BigDecimal) num;
        return bd.intValue();
    }

    @Override
    public List<GraphAnswer> numberOfInactiveRequestsByMonth(Date dateInit, Date dateEnd) {
        String query = " WITH cte AS ( SELECT \n" +
        "        date_trunc('month', \n" +
        "        r.date_real_end) AS mon, \n" +
        "        date_trunc('month', r.date_real_end), \n" +
        "        count(r.id) AS mon_sum \n" +
        "    FROM \n" +
        "        \"FOLLOWUP\".\"CAT_REQUEST\" r \n" +
        "    GROUP  BY \n" +
        "        1     )   \n" +
        "    SELECT \n" +
        "        to_char(mon, 'YYYY-mm') AS mon_text, \n" +
        "        COALESCE(sum(c.mon_sum),0) AS running_sum \n" +
        "    FROM \n" +
        "        generate_series('2022-01-01', '2023-12-25',interval '1 month') mon \n" +
        "    LEFT   JOIN \n" +
        "        cte c USING (mon) \n" +
        "    GROUP BY mon \n" +
        "	ORDER  BY \n" +
        "        mon";

        Query q = entityManager.createNativeQuery(query, GraphAnswer.class);

        return q.getResultList();
    }

    @Override
    public String requestStatusByBusiness() {
        String query = " select new biz.intelix.focuX.followup.model.dto.ColumnCharts(bt.code, r.status.id, s.name, count(c.businessType.id)) \n" +
        "        from Request as r \n" +
        "        join Client as c ON r.client.id = c.id \n" +
        "        join BusinessType as bt ON bt.id = c.businessType.id \n" +
        "        join Status as s ON r.status.id = s.id \n" +
        "        group by bt.code, r.status.id, s.name ";
        Query q = entityManager.createQuery(query);

        return "inhabilitado column charts";
    }

    public PieCharts requestsByDepartment() {
        String query = " select id_solver_group,d.name as name ,count(id_solver_group) as total\n" +
        "        from \"FOLLOWUP\".\"CAT_REQUEST\" as r \n" +
        "        join \"FOLLOWUP\".\"CAT_DEPARTMENT\" as d on r.id_solver_group = d.id \n" +
        "        where r.id_status <> 7 and r.date_real_end is not null \n" +
        "        group by id_solver_group, d.name ";
        Query queryres = entityManager.createNativeQuery(query, Tuple.class);      
        List<Tuple> listRes = queryres.getResultList();
        
        long totaldata=0;
        double[] series= new double[listRes.size()] ;
        String[] labels= new String[listRes.size()];

        for (int i = 0; i < listRes.size(); i++) {
            Tuple active = listRes.get(i);
            labels[i] = active.get("name").toString();
            totaldata = totaldata + Long.parseLong(active.get("total").toString());
        }
        for (int i = 0; i < listRes.size(); i++) {
            Tuple active = listRes.get(i);
            double a = Integer.parseInt(active.get("total").toString());
            double b = Math.toIntExact(totaldata);
            series[i] = a/b*100;
        }

        PieCharts data = new PieCharts(totaldata, series, labels);
        return data;
    }

    public PieCharts requestsByArea() {
        String query = " select id_commercial_area, a.name as name, count(id_commercial_area) as total \n" +
        "        from \"FOLLOWUP\".\"CAT_REQUEST\" as r \n" +
        "        join \"FOLLOWUP\".\"CAT_COMMERCIAL_AREA\" as a ON a.id = r.id_commercial_area \n" +
        "        where r.id_status <> 7 and r.date_real_end is not null \n" +
        "        group by id_commercial_area, a.name ";
        Query queryres = entityManager.createNativeQuery(query, Tuple.class);      
        List<Tuple> listRes = queryres.getResultList();
        
        long totaldata=0;
        double[] series= new double[listRes.size()] ;
        String[] labels= new String[listRes.size()];

        for (int i = 0; i < listRes.size(); i++) {
            Tuple active = listRes.get(i);
            labels[i] = active.get("name").toString();
            totaldata = totaldata + Long.parseLong(active.get("total").toString());
        }
        for (int i = 0; i < listRes.size(); i++) {
            Tuple active = listRes.get(i);
            double a = Integer.parseInt(active.get("total").toString());
            double b = Math.toIntExact(totaldata);
            series[i] = a/b*100;
        }

        PieCharts data = new PieCharts(totaldata, series, labels);
        return data;
    }

    public ResponseColumnCharts requestsByBusiness() { // refactorizar para hacer dinamico agnostico de estatus activos
        String query = " select distinct t1.code, \n" +
        "        max (case when t1.id_status =8 then contar else 0 end ) as Progreso, \n" +
        "        max (case when t1.id_status =6 then contar else 0 end ) as Siniciar \n" +
        "        from \n" +
        "        	(select bt.code, case when (r.id_status isnull)  then 0 else r.id_status end , s.name, count(r.id) as contar \n" +
        "        	from \"FOLLOWUP\".\"CAT_BUSINESS_TYPE\" as bt \n" +
        "        	join \"FOLLOWUP\".\"CAT_CLIENT\" as c ON bt.id = c.id_business_type \n" +
        "        	left join \"FOLLOWUP\".\"CAT_REQUEST\" as r ON r.id_client = c.id \n" +
        "        	left join \"FOLLOWUP\".\"CAT_STATUS\" as s ON s.id = r.id_status \n" +
        "        	where s.id in (6,8) \n" +
        "        	group by bt.code, r.id_status,s.name  \n" +
        "        	order by bt.code, r.id_status) as t1  \n" +
        "        group by t1.code ";
        Query queryres = entityManager.createNativeQuery(query, Tuple.class);      
        List<Tuple> listRes = queryres.getResultList();

        String[] categories= new String[listRes.size()];
        Integer[] arrpro= new Integer[listRes.size()];
        Integer[] arrsin= new Integer[listRes.size()];
        ColumnCharts[] results= new ColumnCharts[2];
        
        int cont = 0;
        for (Tuple line : listRes) {

            categories[cont] = line.get("code").toString();

            arrpro[cont] = Integer.parseInt(line.get("progreso").toString());
            arrsin[cont] = Integer.parseInt(line.get("siniciar").toString());

            cont++;
        }

        ColumnCharts progreso = new ColumnCharts("progreso", arrpro);
        ColumnCharts siniciar = new ColumnCharts("siniciar", arrsin);
        results[0] = progreso;
        results[1] = siniciar;


        // for (int i = 0; i < listRes.size(); i++) {
        //     Tuple line = listRes.get(i);

        //     categories[i] = line.get("code").toString();

            //List<TupleElement<?>> column = line.getElements();
            // for (int j = 1; j < column.size(); j++) {
    
            //     String alias = column.get(j).getAlias();
            //     if(i==0){
            //         series.put(alias, arract);
            //     }
            //     //holaa[i] = null;
            //     Integer h = Integer.parseInt(line.get(alias).toString());
            //     if(alias == "activo") {
            //         arract[i] = h;
            //         series.replace(alias, arract);
            //     }
            //     if(alias == "progreso") {arrpro[i] = h;series.replace(alias, arrpro);}
            //     if(alias == "siniciar") {arrsin[i] = h;series.replace(alias, arrsin);}
            //     //Integer h = Integer.parseInt(active.get(hola).toString());
            //     //holaa[j-1] = h;
            //     System.out.println("had");
                
            // }
            
        ResponseColumnCharts res = new ResponseColumnCharts(categories,results);
        return res;
    }
}
