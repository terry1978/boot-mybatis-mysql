package org.penguin.boot.controllerr;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.penguin.boot.exception.CustomException;
import org.penguin.boot.model.Organization;
import org.penguin.boot.model.User;
import org.penguin.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import static java.lang.String.format;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/type1/{id}")
    public ResponseEntity<User> selectUserAndOrganizationUsingResultTypeByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(userService.selectUserAndOrganizationUsingResultTypeByUserId(id));
    }

    @GetMapping("/type2/{id}")
    public ResponseEntity<User> selectUserAndRoleUsingResultMapByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(userService.selectUserAndRoleUsingResultMapByUserId(id));
    }

    @GetMapping("/type3/{id}")
    public ResponseEntity<User> selectUserAndLazyOrganizationByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(userService.selectUserAndLazyOrganizationByUserId(id));
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody @Validated User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping("/create/demo")
    public ResponseEntity<User> createDemoUser() {
        User user = new User();
        long random = System.currentTimeMillis();
        user.setFullName(format("测试员%s", random));
        user.setUserName(format("test%s", random));
        user.setPassword("test123");
        user.setEmail(format("test%s@pengin.org", random));
        user.setComment(format("优秀测试员编码: %s", random));
        user.setCreatedTime(new Date());
        Organization sjzOrganization = new Organization();
        sjzOrganization.setId(2L); // 因为在service中做了检查，如果找不到组织，会将organizationId设置为null
        user.setOrganization(sjzOrganization);
        try {
            byte[] bytes =FileUtils.readFileToByteArray(new File("C:\\Users\\terry\\Pictures\\Saved Pictures\\iconfinder_dog-01_2140043.png"));
            user.setLogo(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(userService.createUser(user));
    }

    @PostMapping("/update/{id}/logo")
    public ResponseEntity<User> updateLogo(HttpServletRequest request, @PathVariable("id") Long userId, @RequestParam("file") MultipartFile file, @RequestParam(value = "description", defaultValue = "一个图像文件") String description) throws CustomException {
        User user = userService.selectUserAndRoleUsingResultMapByUserId(userId);
        if (user == null) {
            throw new CustomException(format("找不到用户: %d", userId));
        }
        try {
            user.setLogo(file.getBytes());
            user.setComment(description);
            userService.updateUser(user);
            File parentFile = new File(request.getServletContext().getRealPath(format("/user/%s/logos", userId)));
            if (!parentFile.exists()){
                parentFile.mkdirs();
            }
            File savingFile = new File(parentFile,file.getOriginalFilename());
            file.transferTo(savingFile);
            log.debug("Terry -> {}, {}", description, savingFile.getAbsolutePath());
        } catch (IOException e) {
            throw new CustomException(format("发生IOException: %s", e.getMessage()));
        }
        return ResponseEntity.ok(user);
    }
}
