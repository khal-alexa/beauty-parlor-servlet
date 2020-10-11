package entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Treatment {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;

    private Treatment(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.price = builder.price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String name;
        private String description;
        private BigDecimal price;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Treatment build() {
            return new Treatment(this);
        }
    }

    @Override
    public String toString() {
        return "Treatment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
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
        Treatment treatment = (Treatment) o;
        return Objects.equals(id, treatment.id) &&
                Objects.equals(name, treatment.name) &&
                Objects.equals(description, treatment.description) &&
                Objects.equals(price, treatment.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price);
    }

}
