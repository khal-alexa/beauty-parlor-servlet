package dto;

import java.time.LocalDateTime;

public class FeedbackDto {
    private final Long id;
    private final int rate;
    private final String text;
    private final Long clientId;
    private final String specialistName;
    private final LocalDateTime createdOn;

    public FeedbackDto(FeedbackDtoBuilder feedbackDtoBuilder) {
        this.id = feedbackDtoBuilder.id;
        this.rate = feedbackDtoBuilder.rate;
        this.text = feedbackDtoBuilder.text;
        this.clientId = feedbackDtoBuilder.clientId;
        this.specialistName = feedbackDtoBuilder.specialistName;
        this.createdOn = feedbackDtoBuilder.createdOn;
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

    public static class FeedbackDtoBuilder {
        private Long id;
        private int rate;
        private String text;
        private Long clientId;
        private String specialistName;
        private LocalDateTime createdOn;

        public FeedbackDtoBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public FeedbackDtoBuilder setRate(int rate) {
            this.rate = rate;
            return this;
        }

        public FeedbackDtoBuilder setText(String text) {
            this.text = text;
            return this;
        }

        public FeedbackDtoBuilder setClientId(Long clientId) {
            this.clientId = clientId;
            return this;
        }

        public FeedbackDtoBuilder setSpecialistName(String specialistName) {
            this.specialistName = specialistName;
            return this;
        }

        public FeedbackDtoBuilder setCreatedOn(LocalDateTime createdOn) {
            this.createdOn = createdOn;
            return this;
        }

        public FeedbackDto build() {
            return new FeedbackDto(this);
        }

    }

}
