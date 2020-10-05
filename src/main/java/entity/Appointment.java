package entity;

import java.time.LocalDate;

public class Appointment {
    private final Long id;
    private final Long timeslotId;
    private final LocalDate date;
    private final Long clientId;
    private final Long specialistId;
    private final Long treatmentId;
    private final boolean isPaid;
    private final boolean isDone;

    public Appointment(Long id, Long timeslotId, LocalDate date, Long clientId,
                       Long specialistId, Long treatmentId, boolean isPaid, boolean isDone) {
        this.id = id;
        this.timeslotId = timeslotId;
        this.date = date;
        this.clientId = clientId;
        this.specialistId = specialistId;
        this.treatmentId = treatmentId;
        this.isPaid = isPaid;
        this.isDone = isDone;
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

    public static class AppointmentBuilder {
        private Long id;
        private Long timeslotId;
        private LocalDate date;
        private Long clientId;
        private Long specialistId;
        private Long treatmentId;
        private boolean isPaid;
        private boolean isDone;

        public AppointmentBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public AppointmentBuilder setTimeslotId(Long timeslotId) {
            this.timeslotId = timeslotId;
            return this;
        }

        public AppointmentBuilder setDate(LocalDate date) {
            this.date = date;
            return this;
        }

        public AppointmentBuilder setClientId(Long clientId) {
            this.clientId = clientId;
            return this;
        }

        public AppointmentBuilder setSpecialistId(Long specialistId) {
            this.specialistId = specialistId;
            return this;
        }

        public AppointmentBuilder setTreatmentId(Long serviceId) {
            this.treatmentId = serviceId;
            return this;
        }

        public AppointmentBuilder setPaid(boolean paid) {
            isPaid = paid;
            return this;
        }

        public AppointmentBuilder setDone(boolean done) {
            isDone = done;
            return this;
        }

        public Appointment build() {
            return new Appointment(id, timeslotId, date, clientId, specialistId,
                    treatmentId, isPaid, isDone);
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
}
