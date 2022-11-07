package biz.intelix.focuX.followup.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "CAT_COLLABORATOR", schema = "FOLLOWUP")
public class Collaborator {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
   
    @Column(name = "id_file")
    private Integer idFile;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;
    
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_employee_position", nullable = false)
    private EmployeePosition employeePosition;
    
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_status", nullable = false)
    private Status status;

    @Column(name = "company_entry_date")
    private Date companyEntryDate;

    @Column(name = "organization_entry_date")
    private Date organizationEntryDate;

    @Column(name = "gender")
    private String gender;

    @Column(name = "born_date")
    private Date bornDate;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "mail")
    private String mail;

    @Column(name = "is_active")
    private Integer isActive;

    @Column(name = "assigned_location")
    private String assignedLocation;

    @Column(name = "technical_skills")
    private String technicalSkills;

    @Column(name = "is_central_american")
    private Integer isCentralAmerican;


    @OneToMany(mappedBy = "collaborator", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<CollaboratorKnowledge> knowledges;
    
    @OneToMany(mappedBy = "collaborator", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Phone> phones;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_client", nullable = false)
    private Client client;

    @OneToOne(mappedBy = "collaborator")
    @JsonManagedReference
    private Users users;

    @OneToMany(mappedBy = "collaborator")
    @JsonManagedReference(value ="collaborator-occupation")
    Set<OccupationAssignment> assigments;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_leader")
    Collaborator leader;

    @Column(name = "image_loc")
    String image;

    public Collaborator() {
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getIdFile() {
        return idFile;
    }
    
    public void setIdFile(Integer idFile) {
        this.idFile = idFile;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public EmployeePosition getEmployeePosition() {
        return employeePosition;
    }
    
    public void setEmployeePosition(EmployeePosition employeePosition) {
        this.employeePosition = employeePosition;
    }
    
    public Date getCompanyEntryDate() {
        return companyEntryDate;
    }
    
    public void setCompanyEntryDate(Date companyEntryDate) {
        this.companyEntryDate = companyEntryDate;
    }
    
    public Date getOrganizationEntryDate() {
        return organizationEntryDate;
    }
    
    public void setOrganizationEntryDate(Date organizationEntryDate) {
        this.organizationEntryDate = organizationEntryDate;
    }
    
    public String getGender() {
        return gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public Date getBornDate() {
        return bornDate;
    }
    
    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }
    
    public String getNationality() {
        return nationality;
    }
    
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    
    public String getMail() {
        return mail;
    }
    
    public void setMail(String mail) {
        this.mail = mail;
    }
    
    public String getAssignedLocation() {
        return assignedLocation;
    }
    
    public void setAssignedLocation(String assignedLocation) {
        this.assignedLocation = assignedLocation;
    }
    
    public Integer getIsActive() {
        return isActive;
    }
    
    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }
    
    public String getTechnicalSkills() {
        return technicalSkills;
    }
    
    public void setTechnicalSkills(String technicalSkills) {
        this.technicalSkills = technicalSkills;
    }
    
    public List<CollaboratorKnowledge> getKnowledges() {
        return this.knowledges;
    }
    
    public void setKnowledges(List<CollaboratorKnowledge> knowledges) {
        this.knowledges = knowledges;
    }
    
    public List<Phone> getPhones() {
        return this.phones;
    }
    
    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<OccupationAssignment> getAssigments() {
        return this.assigments;
    }

    public void setAssigments(Set<OccupationAssignment> assigments) {
        this.assigments = assigments;
    }

    public Integer getIsCentralAmerican() {
        return this.isCentralAmerican;
    }

    public void setIsCentralAmerican(Integer isCentralAmerican) {
        this.isCentralAmerican = isCentralAmerican;
    }

    public Collaborator getLeader() {
        return this.leader;
    }

    public void setLeader(Collaborator leader) {
        this.leader = leader;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void addKnowledges(List<CollaboratorKnowledge> knowledges) {
        for (CollaboratorKnowledge knowledge: knowledges) {
            knowledge.setCollaborator(this);
        }
        this.setKnowledges(knowledges);        
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
