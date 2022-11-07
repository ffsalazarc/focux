package biz.intelix.focuX.followup.service;

import biz.intelix.focuX.followup.model.Category;
import biz.intelix.focuX.followup.model.RequestPeriod;
import biz.intelix.focuX.followup.repository.RequestPeriodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestPeriodService {

    private final RequestPeriodRepository requestPeriodRepository;

    @Autowired
    public RequestPeriodService(RequestPeriodRepository requestPeriodRepository) {
        this.requestPeriodRepository = requestPeriodRepository;
    }

    public List<RequestPeriod> getAllRequestPeriod() {
        return requestPeriodRepository.findAll();
    }

    public ResponseEntity<RequestPeriod> findRequestPeriodById(Integer id) {
        RequestPeriod requestPeriod = requestPeriodRepository.findRequestPeriodById(id);
        return ResponseEntity.ok().body(requestPeriod);
    }

    public RequestPeriod saveRequestPeriod(RequestPeriod requestPeriod) {
        return requestPeriodRepository.save(requestPeriod);
    }

    public RequestPeriod updateRequestPeriod(RequestPeriod newRequestPeriod, Integer id) {

        return requestPeriodRepository.findById(id)
                .map(requestPeriod -> {
                    requestPeriod.setId(newRequestPeriod.getId());
                    requestPeriod.setName(newRequestPeriod.getName());
                    requestPeriod.setDescription(newRequestPeriod.getDescription());
                    requestPeriod.setUpdated(newRequestPeriod.getUpdated());
                    requestPeriod.setUpdatedBy(newRequestPeriod.getUpdatedBy());
                    return requestPeriodRepository.save(requestPeriod);
                })
                .orElseGet(() -> {
                    newRequestPeriod.setId(id);
                    return requestPeriodRepository.save(newRequestPeriod);
                });
    }

    public RequestPeriod updateRequestPeriodStatus(Integer id, RequestPeriod requestPeriod) {

        return requestPeriodRepository.findById(id)
                .map(requestPeriodWithNewStatus -> {
                    requestPeriodWithNewStatus.setIsActive(requestPeriod.getIsActive());
                    requestPeriodWithNewStatus.setUpdated(requestPeriod.getUpdated());
                    requestPeriodWithNewStatus.setUpdatedBy(requestPeriod.getUpdatedBy());
                    return requestPeriodRepository.save(requestPeriodWithNewStatus);
                }).get();

    }

}
