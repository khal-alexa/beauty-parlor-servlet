package entity;

import java.time.LocalDate;
import java.util.Objects;

public class Appointment {
    private final Long id;
    private Long timeslotId;
    private final LocalDate date;
    private final Long clientId;
    private final Long specialistId;
    private final Long treatmentId;
    private boolean isPaid;
    private boolean isDone;

    private Appointment(Builder builder) {
        this.id = builder.id;
        this.timeslotId = builder.timeslotId;
        this.date = builder.date;
        this.clientId = builder.clientId;
        this.specialistId = builder.specialistId;
        this.treatmentId = builder.treatmentId;
        this.isPaid = builder.isPaid;
        this.isDone = builder.isDone;
    }

    public Long getId() {
        return id;
    }

    public Long getTimeslotId() {
        return timeslotId;
    }

    public LocalDate getDate() {
        return date;
    }

    public Long getClientId() {
        return clientId;
    }

    public Long getSpecialistId() {
        return specialistId;
    }

    public Long getTreatmentId() {
        return treatmentId;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    public void setPaid(boolean paid) {
        this.isDone = paid;
    }

    public void setTimeslotId(Long timeslotId) {
        this.timeslotId = timeslotId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private Long timeslotId;
        private LocalDate date;
        private Long clientId;
        private Long specialistId;
        private Long treatmentId;
        private boolean isPaid;
        private boolean isDone;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setTimeslotId(Long timeslotId) {
            this.timeslotId = timeslotId;
            return this;
        }

        public Builder setDate(LocalDate date) {
            this.date = date;
            return this;
        }

        public Builder setClientId(Long clientId) {
            this.clientId = clientId;
            return this;
        }

        public Builder setSpecialistId(Long specialistId) {
            this.specialistId = specialistId;
            return this;
        }

        public Builder setTreatmentId(Long serviceId) {
            this.treatmentId = serviceId;
            return this;
        }

        public Builder setPaid(boolean paid) {
            isPaid = paid;
            return this;
        }

        public Builder setDone(boolean done) {
            isDone = done;
            return this;
        }

        public Appointment build() {
            return new Appointment(this);
        }
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", timeslotId=" + timeslotId +
                ", date=" + date +
                ", clientId=" + clientId +
                ", specialistId=" + specialistId +
                ", treatmentId=" + treatmentId +
                ", isPaid=" + isPaid +
                ", isDone=" + isDone +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Appointment that = (Appointment) o;
        return isPaid == that.isPaid &&
                isDone == that.isDone &&
                Objects.equals(id, that.id) &&
                Objects.equals(timeslotId, that.timeslotId) &&
                Objects.equals(date, that.date) &&
                Objects.equals(clientId, that.clientId) &&
                Objects.equals(specialistId, that.specialistId) &&
                Objects.equals(treatmentId, that.treatmentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timeslotId, date, clientId, specialistId, treatmentId, isPaid, isDone);
    }

}
