package biz.intelix.focuX.followup.repository;

import biz.intelix.focuX.followup.model.RequestPause;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestPauseRepository extends JpaRepository<RequestPause, Long> {

    RequestPause findRequestPauseById(Long id);
}