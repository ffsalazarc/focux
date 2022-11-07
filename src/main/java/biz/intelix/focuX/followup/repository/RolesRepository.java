package biz.intelix.focuX.followup.repository;

import biz.intelix.focuX.followup.model.Roles;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolesRepository extends CrudRepository<Roles, Integer> {
    List<Roles> findAll();
    Roles findRolesById(Integer id);
    Roles findByName(String name);
}
