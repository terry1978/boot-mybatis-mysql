package org.penguin.boot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@JsonIgnoreProperties(value = {"handler"})
public class Role implements Serializable {

    private Long id;

    @NotBlank(message = "姓名不能为空")
    private String name;

    private List<User> users;

    private Boolean enabled;

    private Date createdTime;
}
