package dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class FeedbackDto {
    private final Long id;
    private final int rate;
    private final String text;
    private final Long clientId;
    private final String specialistName;
    private final LocalDateTime createdOn;

    private FeedbackDto(Builder builder) {
        this.id = builder.id;
        this.rate = builder.rate;
        this.text = builder.text;
        this.clientId = builder.clientId;
        this.specialistName = builder.specialistName;
        this.createdOn = builder.createdOn;
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

    public String getSpecialistName() {
        return specialistName;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private int rate;
        private String text;
        private Long clientId;
        private String specialistName;
        private LocalDateTime createdOn;

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

        public Builder setSpecialistName(String specialistName) {
            this.specialistName = specialistName;
            return this;
        }

        public Builder setCreatedOn(LocalDateTime createdOn) {
            this.createdOn = createdOn;
            return this;
        }

        public FeedbackDto build() {
            return new FeedbackDto(this);
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
        FeedbackDto that = (FeedbackDto) o;
        return rate == that.rate &&
                Objects.equals(id, that.id) &&
                Objects.equals(text, that.text) &&
                Objects.equals(clientId, that.clientId) &&
                Objects.equals(specialistName, that.specialistName) &&
                Objects.equals(createdOn, that.createdOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rate, text, clientId, specialistName, createdOn);
    }

    @Override
    public String toString() {
        return "FeedbackDto{" +
                "id=" + id +
                ", rate=" + rate +
                ", text='" + text + '\'' +
                ", clientId=" + clientId +
                ", specialistName='" + specialistName + '\'' +
                ", createdOn=" + createdOn +
                '}';
    }

}
