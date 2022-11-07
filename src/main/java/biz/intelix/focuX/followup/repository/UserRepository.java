package biz.intelix.focuX.followup.repository;

import biz.intelix.focuX.followup.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

    Users findByUsername(String username);
    Users findUsersByCollaboratorId(Integer id);
    Optional<Users> findByCollaboratorId(Integer id);
}
