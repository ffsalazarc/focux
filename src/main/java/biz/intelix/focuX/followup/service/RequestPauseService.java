package biz.intelix.focuX.followup.service;

import biz.intelix.focuX.followup.model.RequestPause;
import biz.intelix.focuX.followup.repository.RequestPauseRepository;

import org.apache.catalina.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestPauseService {

    private final RequestPauseRepository requestPauseRepository;

    @Autowired
    public RequestPauseService(RequestPauseRepository requestPauseRepository) {
        this.requestPauseRepository = requestPauseRepository;
    }

    public List<RequestPause> getAllRequestPauses() {
        return requestPauseRepository.findAll();
    }

    public ResponseEntity<RequestPause> findRequestPauseById(Long id) {
        RequestPause requestPause = requestPauseRepository.findRequestPauseById(id);
        return ResponseEntity.ok().body(requestPause);
    }

    public RequestPause saveRequestPause(RequestPause requestPause) {
        return requestPauseRepository.save(requestPause);
    }

    public RequestPause updateRequestPause(RequestPause newRequestPause, Long id) {

        return requestPauseRepository.findById(id)
                .map(requestPause -> {
                    requestPause.setId(newRequestPause.getId());                                        
                    requestPause.setComments(newRequestPause.getComments());
                    requestPause.setDateEndPause(newRequestPause.getDateEndPause());
                    requestPause.setDateInitPause(newRequestPause.getDateInitPause());
                    return requestPauseRepository.save(requestPause);
                })
                .orElseGet(() -> {
                    newRequestPause.setId(id);
                    return requestPauseRepository.save(newRequestPause);
                });
    }
}
