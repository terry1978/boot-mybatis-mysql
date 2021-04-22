package org.penguin.boot.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.penguin.boot.model.EventLog;

import java.util.List;

@Mapper
public interface EventLogMapper {

    List<EventLog> selectAll();

    int insertEventLog(EventLog eventLog);
}
