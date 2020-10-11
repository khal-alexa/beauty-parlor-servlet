package entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Feedback {
    private final Long id;
    private final int rate;
    private final String text;
    private final Long clientId;
    private final Long specialistId;
    private final LocalDateTime dateTime;

    private Feedback(Builder builder) {
        this.id = builder.id;
        this.rate = builder.rate;
        this.text = builder.text;
        this.clientId = builder.clientId;
        this.specialistId = builder.specialistId;
        this.dateTime = builder.dateTime;
    }

    public Long getId() {
        return id;
    }

    public int getRate() {
        return rate;
    }

    public String getText() {
        return text;
    }

    public Long getClientId() {
        return clientId;
    }

    public Long getSpecialistId() {
        return specialistId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private int rate;
        private String text;
        private Long clientId;
        private Long specialistId;
        private LocalDateTime dateTime;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setRate(int rate) {
            this.rate = rate;
            return this;
        }

        public Builder setText(String text) {
            this.text = text;
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

        public Builder setDateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
            return this;
        }

        public Feedback build() {
            return new Feedback(this);
        }
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", rate=" + rate +
                ", text='" + text + '\'' +
                ", clientId=" + clientId +
                ", specialistId=" + specialistId +
                ", dateTime=" + dateTime +
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
        Feedback feedback = (Feedback) o;
        return rate == feedback.rate &&
                Objects.equals(id, feedback.id) &&
                Objects.equals(text, feedback.text) &&
                Objects.equals(clientId, feedback.clientId) &&
                Objects.equals(specialistId, feedback.specialistId) &&
                Objects.equals(dateTime, feedback.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rate, text, clientId, specialistId, dateTime);
    }

}
