package entity;

import java.time.LocalDateTime;

public class Feedback {
    private Long id;
    private int rate;
    private String text;
    private Long clientId;
    private Long specialistId;
    private LocalDateTime dateTime;

    public Feedback(Long id, int rate, String text, Long clientId, Long specialistId, LocalDateTime dateTime) {
        this.id = id;
        this.rate = rate;
        this.text = text;
        this.clientId = clientId;
        this.specialistId = specialistId;
        this.dateTime = dateTime;
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

    public static class FeedbackBuilder {
        private Long id;
        private int rate;
        private String text;
        private Long clientId;
        private Long specialistId;
        private LocalDateTime dateTime;

        public FeedbackBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public FeedbackBuilder setRate(int rate) {
            this.rate = rate;
            return this;
        }

        public FeedbackBuilder setText(String text) {
            this.text = text;
            return this;
        }

        public FeedbackBuilder setClientId(Long clientId) {
            this.clientId = clientId;
            return this;
        }

        public FeedbackBuilder setSpecialistId(Long specialistId) {
            this.specialistId = specialistId;
            return this;
        }

        public FeedbackBuilder setDateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
            return this;
        }

        public Feedback build() {
            return new Feedback(id, rate, text, clientId, specialistId, dateTime);
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

}
