package org.penguin.boot.controllerr;

import lombok.extern.slf4j.Slf4j;
import org.penguin.boot.model.Role;
import org.penguin.boot.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleAndUsersById(@PathVariable("id") Long roleId) {
        return ResponseEntity.ok(roleService.selectRoleAndUsersById(roleId));
    }

    @GetMapping("/lazy/{id}")
    public ResponseEntity<Role> getRoleAndLazyUsersById(@PathVariable("id") Long roleId) {
        return ResponseEntity.ok(roleService.selectRoleAndLazyUsersById(roleId));
    }
}
