package biz.intelix.focuX.followup.repository;

import biz.intelix.focuX.followup.model.Collaborator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CollaboratorRepository extends JpaRepository<Collaborator, Integer> {

    Collaborator findCollaboratorById(Integer id);
    Optional<Collaborator> findCollaboratorByMail(String mail);
    Optional<Collaborator> findCollaboratorByIdFile(Integer id);
    Collaborator findCollaboratorByNameAndLastName(String name, String lastName);
    List<Collaborator> findCollaboratorsByTechnicalSkills(String technicalSkills);
}
