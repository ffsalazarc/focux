package biz.intelix.focuX.followup.model.dto;

import biz.intelix.focuX.followup.model.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class CollaboratorWithOcuppationPercentageDTO {

        private Integer id;   
        private Integer idFile;
        private String name;
        private String lastName;
        private EmployeePosition employeePosition;    
        private Status status;
        private Date companyEntryDate;
        private Date organizationEntryDate;
        private String gender;
        private Date bornDate;
        private String nationality;
        private String mail;
        private Integer isActive;
        private String assignedLocation;
        private String technicalSkills;
        private Integer isCentralAmerican;
        private List<CollaboratorKnowledge> knowledges;
        private List<Phone> phones;
        private Client client;
        Set<OccupationAssigmentDTO> assigments;
        Collaborator leader;
        private Integer occupationPercentage;
    public CollaboratorWithOcuppationPercentageDTO(Collaborator c, Integer occupationPercentage, Set<OccupationAssigmentDTO> assigments) {
        this.id = c.getId();   
        this.idFile = c.getIdFile();
        this.name = c.getName();
        this.lastName = c.getLastName();
        this.employeePosition = c.getEmployeePosition();    
        this.status = c.getStatus();
        this.companyEntryDate = c.getCompanyEntryDate();
        this.organizationEntryDate = c.getOrganizationEntryDate();
        this.gender = c.getGender();
        this.bornDate = c.getBornDate();
        this.nationality = c.getNationality();
        this.mail = c.getMail();
        this.isActive = c.getIsActive();
        this.assignedLocation = c.getAssignedLocation();
        this.technicalSkills = c.getTechnicalSkills();
        this.isCentralAmerican = c.getIsCentralAmerican();
        this.knowledges = c.getKnowledges();
        this.phones = c.getPhones();
        this.client = c.getClient();
        this.assigments = assigments;
        this.leader = c.getLeader();
        this.occupationPercentage = occupationPercentage;
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

    public Set<OccupationAssigmentDTO> getAssigments() {
        return this.assigments;
    }

    public void setAssigments(Set<OccupationAssigmentDTO> assigments) {
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

    public Integer getOccupationPercentage() {
        return this.occupationPercentage;
    }

    public void setOccupationPercentage(Integer occupationPercentage) {
        this.occupationPercentage = occupationPercentage;
    }

}
