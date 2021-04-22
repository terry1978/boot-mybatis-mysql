package org.penguin.boot.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data
public class Organization implements Serializable {

    private static final long serialVersionUID = -7917846903371727474L;

    private Long id;

    @NotBlank(message = "国家名称不能为空")
    private String name;

    @NotBlank(message = "国家代码不能为空")
    private String code;

    private String status;

    private Date createdTime;
}
