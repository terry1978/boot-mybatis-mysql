package org.penguin.boot.service;

import org.penguin.boot.model.EventLog;

import java.util.List;

public interface EventLogService {

    List<EventLog> selectAll();

    int insertEventLog(EventLog eventLog);
}
