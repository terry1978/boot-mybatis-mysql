package org.penguin.boot.model;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Role implements Serializable {

    private Long id;

    @NotBlank(message = "姓名不能为空")
    private String name;

    private List<User> users;

    private Date createdTime;
}
