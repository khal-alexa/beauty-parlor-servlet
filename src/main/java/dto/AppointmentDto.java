package dto;

import java.time.LocalDate;
import java.util.Objects;

public class AppointmentDto {
    private final Long id;
    private final String timeslot;
    private final LocalDate date;
    private final Long specialistId;
    private final String specialistName;
    private final String treatmentName;
    private final Long clientId;
    private final String clientName;
    private final Boolean available;
    private final Boolean isDone;
    private final Boolean isPaid;

    private AppointmentDto(AppointmentDtoBuilder builder) {
        this.id = builder.id;
        this.timeslot = builder.timeslot;
        this.date = builder.date;
        this.specialistId = builder.specialistId;
        this.specialistName = builder.specialistName;
        this.treatmentName = builder.treatmentName;
        this.clientId = builder.clientId;
        this.clientName = builder.clientName;
        this.available = builder.available;
        this.isDone = builder.isDone;
        this.isPaid = builder.isPaid;
    }

    public Long getId() {
        return id;
    }

    public String getTimeslot() {
        return timeslot;
    }

    public LocalDate getDate() {
        return date;
    }

    public Long getSpecialistId() {
        return specialistId;
    }

    public String getSpecialistName() {
        return specialistName;
    }

    public String getTreatmentName() {
        return treatmentName;
    }

    public Long getClientId() {
        return clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public Boolean getAvailable() {
        return available;
    }

    public Boolean getDone() {
        return isDone;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public static class AppointmentDtoBuilder {
        private Long id;
        private String timeslot;
        private LocalDate date;
        private Long specialistId;
        private String specialistName;
        private String treatmentName;
        private Long clientId;
        private String clientName;
        private Boolean available;
        private Boolean isDone;
        private Boolean isPaid;

        public AppointmentDtoBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public AppointmentDtoBuilder setTimeslot(String timeslot) {
            this.timeslot = timeslot;
            return this;
        }

        public AppointmentDtoBuilder setDate(LocalDate date) {
            this.date = date;
            return this;
        }

        public AppointmentDtoBuilder setSpecialistId(Long specialistId) {
            this.specialistId = specialistId;
            return this;
        }

        public AppointmentDtoBuilder setSpecialistName(String specialistName) {
            this.specialistName = specialistName;
            return this;
        }

        public AppointmentDtoBuilder setTreatmentName(String treatmentName) {
            this.treatmentName = treatmentName;
            return this;
        }

        public AppointmentDtoBuilder setClientId(Long clientId) {
            this.clientId = clientId;
            return this;
        }

        public AppointmentDtoBuilder setClientName(String clientName) {
            this.clientName = clientName;
            return this;
        }

        public AppointmentDtoBuilder setAvailable(Boolean available) {
            this.available = available;
            return this;
        }

        public AppointmentDtoBuilder setDone(Boolean done) {
            isDone = done;
            return this;
        }

        public AppointmentDtoBuilder setPaid(Boolean paid) {
            isPaid = paid;
            return this;
        }

        public AppointmentDto build() {
            return new AppointmentDto(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AppointmentDto that = (AppointmentDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(timeslot, that.timeslot) &&
                Objects.equals(date, that.date) &&
                Objects.equals(specialistId, that.specialistId) &&
                Objects.equals(specialistName, that.specialistName) &&
                Objects.equals(treatmentName, that.treatmentName) &&
                Objects.equals(clientId, that.clientId) &&
                Objects.equals(clientName, that.clientName) &&
                Objects.equals(available, that.available) &&
                Objects.equals(isDone, that.isDone) &&
                Objects.equals(isPaid, that.isPaid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timeslot, date, specialistId, specialistName, treatmentName, clientId, clientName, available, isDone, isPaid);
    }

    @Override
    public String toString() {
        return "AppointmentDto{" +
                "id=" + id +
                ", timeslot='" + timeslot + '\'' +
                ", date=" + date +
                ", specialistId=" + specialistId +
                ", specialistName='" + specialistName + '\'' +
                ", treatmentName='" + treatmentName + '\'' +
                ", clientId=" + clientId +
                ", clientName='" + clientName + '\'' +
                ", available=" + available +
                ", isDone=" + isDone +
                ", isPaid=" + isPaid +
                '}';
    }

}
