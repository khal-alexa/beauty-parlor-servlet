package dto;

import java.math.BigDecimal;

public class TreatmentDto {
    private final String treatmentName;
    private final BigDecimal price;
    private final String specialistName;
    private final Double rate;

    public TreatmentDto(TreatmentDtoBuilder builder) {
        treatmentName = builder.treatmentName;
        price = builder.price;
        specialistName = builder.specialistName;
        rate = builder.rate;
    }

    public String getTreatmentName() {
        return treatmentName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getSpecialistName() {
        return specialistName;
    }

    public Double getRate() {
        return rate;
    }

    public static class TreatmentDtoBuilder {
        private String treatmentName;
        private BigDecimal price;
        private String specialistName;
        private Double rate;

        public TreatmentDtoBuilder() {
        }

        public TreatmentDtoBuilder(TreatmentDto copy) {
            this.treatmentName=copy.treatmentName;
            this.price=copy.price;
            this.treatmentName=copy.treatmentName;
        }

        public TreatmentDtoBuilder setTreatmentName(String treatmentName) {
            this.treatmentName = treatmentName;
            return this;
        }

        public TreatmentDtoBuilder setPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public TreatmentDtoBuilder setSpecialistName(String specialistName) {
            this.specialistName = specialistName;
            return this;
        }

        public TreatmentDtoBuilder setRate(Double rate) {
            this.rate = rate;
            return this;
        }

        public TreatmentDto build() {
            return new TreatmentDto(this);
        }

    }

}
