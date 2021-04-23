package org.penguin.boot.controllerr;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.penguin.boot.model.Organization;
import org.penguin.boot.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/organization")
@Slf4j
public class OrganizationController {

    @Autowired
    OrganizationService organizationService;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Organization>> getOrganizationsByName(@PathVariable String name) {
        log.debug("Terry -输入参数>{}的长度{}", name, name.length());
        return ResponseEntity.ok(organizationService.getOrganizationsByName(name));
    }

    @PostMapping("/create")
    public ResponseEntity<Organization> createOrganization(@RequestBody @Validated Organization organization) {
        log.debug("Terry -输入参数>{}", organization);
        int insertedRow = organizationService.insertOrganization(organization);
        return ResponseEntity.ok(organization);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Integer> deleteOrganization(@PathVariable Long id) {
        log.debug("Terry -输入参数>{}", id);
        int deleteOrganizationRow = organizationService.deleteOrganization(id);
        return ResponseEntity.ok(deleteOrganizationRow);
    }

    @PostMapping("/create/list")
    public ResponseEntity<List<Organization>> createOrganizationList(@RequestBody @Validated List<Organization> organizationList) {
        log.debug("Terry -输入参数>{}", organizationList);
        int insertedRow = organizationService.insertOrganizationList(organizationList);
        return ResponseEntity.ok(organizationList);
    }

    @GetMapping("/name/{name}/status/{status}")
    public ResponseEntity<List<Organization>> getOrganizationsBuNameAndStatus(@PathVariable String name, @PathVariable String status) {
        log.debug("Terry -输入参数>{}的长度{}, {}的长度{}", name, name.length(), status, status.length());
        return ResponseEntity.ok(organizationService.getOrganizationsBuNameAndStatus(name, status));
    }

    @GetMapping("/rest/custom")
    public JsonNode getOrganizationsBuNameAndStatusOfRestTemplate() throws JsonProcessingException {
        String searchOrganizationUrl = "http://localhost:8080/organization/name/分";
        ResponseEntity<String> response = restTemplate.getForEntity(searchOrganizationUrl, String.class);
        JsonNode root = objectMapper.readTree(response.getBody());
        log.debug("Terry -> isArray: {}", root.isArray());
        return root;
    }


}
