package biz.intelix.focuX.clockify.controller;

import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import biz.intelix.focuX.followup.core.UtilityService;

@RestController
@RequestMapping("/api/v1/clockify")
@CrossOrigin("*")
public class ClockifyIntegrationController {

    private final String workspaceID = "clockify.workspaceID";
    private final String apiBaseEndpoint = "clockify.apiBaseEndpoint";    
    private final String xApiKey = "clockify.xApiKey";
    private final UtilityService utilityService;
    @Autowired
    public ClockifyIntegrationController(UtilityService utilityService) {
        this.utilityService = utilityService;
    }

    @GetMapping("/getclients")
    private String getAllClients(){
        String uri = "/clients";
        return doRequest(uri);
    }

    @GetMapping("/getprojects")
    private String getAllProjects(){
        String uri = "/projects";
        return doRequest(uri);
    }

    @GetMapping("/gettags")
    private String getAllTags(){
        String uri = "/tags";
        return doRequest(uri);
    }

    @GetMapping("/getusers")
    private String getUsers(){
        String uri = "/users";
        return doRequest(uri);
    }

    @GetMapping("/getgroups")
    private String getGroups(){
        String uri = "/user-groups";
        return doRequest(uri);
    }

    @GetMapping("/gettask")
    private String getAllTask(@RequestParam(value = "projectId") String projectId){
        String uri = "/projects/" + projectId + "/tasks";
        return doRequest(uri);
    }

    private String doRequest(String uri) {
        RestTemplate restTemplate = new RestTemplate();
        Properties properties = utilityService.readConstants();
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", properties.getProperty(xApiKey));

        HttpEntity entity = new HttpEntity(headers);
        HttpEntity<String> response =
                restTemplate.exchange(properties.getProperty(apiBaseEndpoint) + properties.getProperty(workspaceID) + uri, HttpMethod.GET, entity, String.class);

        return response.getBody();
    }
}
