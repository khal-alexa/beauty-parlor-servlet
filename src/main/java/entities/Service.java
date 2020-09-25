package entities;

import java.math.BigDecimal;

public class Service {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;

    public Service(Long id, String name, String description, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
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

    public static class ServiceBuilder {
        private Long id;
        private String name;
        private String description;
        private BigDecimal price;

        public ServiceBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public ServiceBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ServiceBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public ServiceBuilder setPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Service build() {
            return new Service(id, name, description, price);
        }
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }

}
