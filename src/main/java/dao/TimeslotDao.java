package dao;

import entity.Timeslot;

import java.time.LocalTime;

public interface TimeslotDao extends CrudDao<Timeslot> {
    Timeslot findByStartTime(LocalTime parse);

}
