package com.redck.restaurantmsbff.service.mapper;

import com.redck.restaurantmsbff.domain.Schedule;
import com.redck.restaurantmsbff.service.model.ScheduleDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ScheduleMapper
{
    Schedule mapScheduleDTOToSchedule(ScheduleDTO scheduleDTO);

    ScheduleDTO mapScheduleToScheduleDTO(Schedule schedule);

    List<Schedule> mapScheduleDTOListToScheduleList(List<ScheduleDTO> scheduleDTO);

    List<ScheduleDTO> mapScheduleListToScheduleDTOList(List<Schedule> schedule);
}
