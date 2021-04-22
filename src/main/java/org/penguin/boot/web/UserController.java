package org.penguin.boot.web;

import org.penguin.boot.model.User;
import org.penguin.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/type1/{id}")
    public ResponseEntity<User> getOrganizationsByName1(@PathVariable Long id) {
        return ResponseEntity.ok(userService.selectUserAndRoleById1(id));
    }

    @GetMapping("/type2/{id}")
    public ResponseEntity<User> getOrganizationsByName2(@PathVariable Long id) {
        return ResponseEntity.ok(userService.selectUserAndRoleById2(id));
    }

    @GetMapping("/type3/{id}")
    public ResponseEntity<User> getOrganizationsByName3(@PathVariable Long id) {
        return ResponseEntity.ok(userService.selectUserAndRoleById3(id));
    }

    @GetMapping("/type4/{id}")
    public ResponseEntity<User> getOrganizationsByName4(@PathVariable Long id) {
        return ResponseEntity.ok(userService.selectUserAndRoleById4(id));
    }

    @GetMapping("/type5/{id}")
    public ResponseEntity<User> getOrganizationsByName5(@PathVariable Long id) {
        return ResponseEntity.ok(userService.selectUserAndRoleById5(id));
    }

    @GetMapping("/type6/{id}")
    public ResponseEntity<User> getOrganizationsByName6(@PathVariable Long id) {
        return ResponseEntity.ok(userService.selectUserAndRoleById6(id));
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody @Validated User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }
}
