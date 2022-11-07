package biz.intelix.focuX.followup.service;

import biz.intelix.focuX.followup.model.Position;
import biz.intelix.focuX.followup.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {

    private final PositionRepository positionRepository;

    @Autowired
    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public List<Position> getAllPosition() {
        return positionRepository.findAll();
    }

    public ResponseEntity<Position> findPositionById(Integer id) {
        Position position = positionRepository.findPositionById(id);
        return ResponseEntity.ok().body(position);
    }

    public Position savePosition(Position position) {
        return positionRepository.save(position);
    }

    public Position updatePosition(Position newPosition, Integer id) {

        return positionRepository.findById(id)
                .map(position -> {
                    position.setId(newPosition.getId());
                    position.setName(newPosition.getName());
                    position.setDescription(newPosition.getDescription());
                    position.setUpdated(newPosition.getUpdated());
                    position.setUpdatedBy(newPosition.getUpdatedBy());
                    return positionRepository.save(position);
                })
                .orElseGet(() -> {
                    newPosition.setId(id);
                    return positionRepository.save(newPosition);
                });
    }

    public Position updatePositionStatus(Integer id, Position position) {
        return positionRepository.findById(id)
                .map(positionWithNewStatus -> {
                    positionWithNewStatus.setIsActive(position.getIsActive());
                    positionWithNewStatus.setUpdated(position.getUpdated());
                    positionWithNewStatus.setUpdatedBy(position.getUpdatedBy());
                    return positionRepository.save(positionWithNewStatus);
                }).get();

    }
}
