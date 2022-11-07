package biz.intelix.focuX.followup.repository;

import biz.intelix.focuX.followup.model.Phone;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Integer> {

    Long deleteById(Long id);

    Phone findPhoneById(Integer id);
}