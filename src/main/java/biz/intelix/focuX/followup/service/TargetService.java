package biz.intelix.focuX.followup.service;
import biz.intelix.focuX.followup.model.Target;
import biz.intelix.focuX.followup.repository.TargetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TargetService {
    private final TargetRepository targetRepository;

    @Autowired
    public TargetService(TargetRepository targetRepository) {
        this.targetRepository = targetRepository;
    }

    public List<Target> getAllTarget() {
        return targetRepository.findAll();
    }

    public ResponseEntity<Target> findTargetById(Integer id) {
        Target target = targetRepository.findTargetById(id);
        return ResponseEntity.ok().body(target);
    }

    public Target saveTarget(Target target) {
        return targetRepository.save(target);
    }

    public Target updateTarget(Target newTarget, Integer id) {

        return targetRepository.findById(id)
                .map(client -> {
                    client.setId(newTarget.getId());
                    client.setName(newTarget.getName());
                    client.setDescription(newTarget.getDescription());
                    client.setType(newTarget.getType());
                    return targetRepository.save(client);
                })
                .orElseGet(() -> {
                    newTarget.setId(id);
                    return targetRepository.save(newTarget);
                });
    }

    public Target updateTargetStatus(Integer id, Target target) {

        return targetRepository.findById(id)
                .map(clientWithNewStatus -> {
                    clientWithNewStatus.setIsActive(target.getIsActive());
                    return targetRepository.save(clientWithNewStatus);
                }).get();

    }
}
