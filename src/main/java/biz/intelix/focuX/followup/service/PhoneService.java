package biz.intelix.focuX.followup.service;

import biz.intelix.focuX.followup.model.Phone;
import biz.intelix.focuX.followup.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneService {

    private final PhoneRepository phoneRepository;

    @Autowired
    public PhoneService(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    public List<Phone> getAllPhones() {
        return phoneRepository.findAll();
    }

    public ResponseEntity<Phone> findPhoneById(Integer id) {
        Phone phone = phoneRepository.findPhoneById(id);
        return ResponseEntity.ok().body(phone);
    }

    public Phone savePhone(Phone phone) {
        return phoneRepository.save(phone);
    }

    public Phone updatePhone(Phone newPhone, Integer id) {

        return phoneRepository.findById(id)
                .map(phone -> {
                    phone.setId(newPhone.getId());
                    phone.setNumber(newPhone.getNumber());
                    phone.setType(newPhone.getType());
                    phone.setUpdated(newPhone.getUpdated());
                    phone.setUpdatedBy(newPhone.getUpdatedBy());
                    return phoneRepository.save(phone);
                })
                .orElseGet(() -> {
                    newPhone.setId(id);
                    return phoneRepository.save(newPhone);
                });
    }

    public Phone updatePhoneStatus(Integer id, Phone phone) {
        System.out.println(phone);
        return phoneRepository.findById(id)
                .map(businessTypeWithNewStatus -> {
                    businessTypeWithNewStatus.setIsActive(phone.getIsActive());
                    businessTypeWithNewStatus.setUpdated(phone.getUpdated());
                    businessTypeWithNewStatus.setUpdatedBy(phone.getUpdatedBy());
                    return phoneRepository.save(businessTypeWithNewStatus);
                }).get();

    }
}
