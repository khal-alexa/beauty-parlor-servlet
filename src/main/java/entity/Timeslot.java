package entity;

import java.time.LocalTime;

public class Timeslot {
    private Long id;
    private LocalTime startTime;

    public Timeslot() {
    }

    public Timeslot(Long id, LocalTime startTime) {
        this.id = id;
        this.startTime = startTime;
    }

    public Long getId() {
        return id;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

}
