package org.penguin.boot.model;

import lombok.Data;

import java.util.Date;

@Data
public class EventLog {

    private Long id;
    private User user;
    private Date createdTime;

}
