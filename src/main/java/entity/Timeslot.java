package entity;

import java.time.LocalTime;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Timeslot timeslot = (Timeslot) o;
        return Objects.equals(id, timeslot.id) &&
                Objects.equals(startTime, timeslot.startTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startTime);
    }

}
