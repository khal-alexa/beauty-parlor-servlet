package dto;

import java.math.BigDecimal;
import java.util.Objects;

public class TreatmentDto {
    private final String treatmentName;
    private final BigDecimal price;
    private final String specialistName;
    private final Double rate;

    private TreatmentDto(TreatmentDtoBuilder builder) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TreatmentDto that = (TreatmentDto) o;
        return Objects.equals(treatmentName, that.treatmentName) &&
                Objects.equals(price, that.price) &&
                Objects.equals(specialistName, that.specialistName) &&
                Objects.equals(rate, that.rate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(treatmentName, price, specialistName, rate);
    }

    @Override
    public String toString() {
        return "TreatmentDto{" +
                "treatmentName='" + treatmentName + '\'' +
                ", price=" + price +
                ", specialistName='" + specialistName + '\'' +
                ", rate=" + rate +
                '}';
    }

}
