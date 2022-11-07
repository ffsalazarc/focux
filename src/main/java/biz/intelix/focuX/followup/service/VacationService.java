package biz.intelix.focuX.followup.service;

import biz.intelix.focuX.followup.model.Vacation;
import biz.intelix.focuX.followup.repository.VacationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacationService {

    private final VacationRepository vacationRepository;

    @Autowired
    public VacationService(VacationRepository vacationRepository) {
        this.vacationRepository = vacationRepository;
    }

    public List<Vacation> getAllVacations() {
        return vacationRepository.findAll();
    }

    public ResponseEntity<Vacation> findVacationByIdCollaborator(Integer id) {
        Vacation vacation = vacationRepository.findVacationByIdCollaborator(id);
        return ResponseEntity.ok().body(vacation);
    }

    public Vacation saveVacation(Vacation vacation) {
        return vacationRepository.save(vacation);
    }

    public Vacation updateVacation(Vacation newVacation, Integer id) {

        return vacationRepository.findById(id)
                .map(vacation -> {
                    vacation.setId(newVacation.getId());
                    vacation.setIdCollaborator(newVacation.getIdCollaborator());
                    vacation.setCurrentVacationPeriod(newVacation.getCurrentVacationPeriod());
                    vacation.setCurrentInitPeriod(newVacation.getCurrentInitPeriod());
                    vacation.setCurrentEndPeriod(newVacation.getCurrentEndPeriod());
                    vacation.setPendingPeriods(newVacation.getPendingPeriods());
                    vacation.setUpdated(newVacation.getUpdated());
                    vacation.setUpdatedBy(newVacation.getUpdatedBy());
                    return vacationRepository.save(vacation);
                })
                .orElseGet(() -> {
                    newVacation.setId(id);
                    return vacationRepository.save(newVacation);
                });
    }

    public Vacation updateVacationStatus(Integer id, Vacation vacation) {

        return vacationRepository.findById(id)
                .map(clientWithNewStatus -> {
                    clientWithNewStatus.setIsActive(vacation.getIsActive());
                    clientWithNewStatus.setUpdated(vacation.getUpdated());
                    clientWithNewStatus.setUpdatedBy(vacation.getUpdatedBy());
                    return vacationRepository.save(clientWithNewStatus);
                }).get();

    }

}
