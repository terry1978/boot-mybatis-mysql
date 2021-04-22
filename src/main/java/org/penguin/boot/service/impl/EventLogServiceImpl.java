package org.penguin.boot.service.impl;

import org.penguin.boot.mapper.EventLogMapper;
import org.penguin.boot.model.EventLog;
import org.penguin.boot.service.EventLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventLogServiceImpl implements EventLogService {

    @Autowired
    EventLogMapper mapper;

    @Override
    public List<EventLog> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public int insertEventLog(EventLog eventLog) {
        System.out.println("Terry -EventLog> " + eventLog);
        return mapper.insertEventLog(eventLog);
    }
}
