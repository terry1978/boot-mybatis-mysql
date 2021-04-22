package org.penguin.boot.web;

import lombok.extern.slf4j.Slf4j;
import org.penguin.boot.model.EventLog;
import org.penguin.boot.model.User;
import org.penguin.boot.service.EventLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/event/log")
@Slf4j
public class EventLogController {


    @Autowired
    EventLogService eventLogService;

    @GetMapping("/create")
    public ResponseEntity<EventLog> createEventLog() {
        EventLog eventLog = new EventLog();
        User user = new User();
        user.setCreatedTime(new Date());
        user.setEmail("admin2@penguin.org");
        user.setFullName("系统管理员已");
        user.setUserName("admin2");
        user.setPassword("admin123");
        eventLog.setUser(user);
        eventLog.setCreatedTime(new Date());
        eventLogService.insertEventLog(eventLog);
        return ResponseEntity.ok(eventLog);
    }

    @GetMapping("/get")
    public ResponseEntity<List<EventLog>> getEventLogById() {
        List<EventLog> eventLogList = eventLogService.selectAll();
        return ResponseEntity.ok(eventLogList);
    }
}
