package org.penguin.boot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data
@JsonIgnoreProperties(value = {"handler"})
public class User implements Serializable {

    private Long id;

    @NotBlank(message = "姓名不能为空")
    private String fullName;

    @NotBlank(message = "账号不能为空")
    private String userName;

    private String password;

    private String email;

    private Organization organization;

//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;
}
