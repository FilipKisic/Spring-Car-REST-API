package hr.algebra.springproject.model;

import lombok.Builder;


@Builder
public record CarDTO(Long id, String brand, String model, String color, Integer powerInHp){
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if(!(obj instanceof CarDTO)) {
            return false;
        }
        return this.id.equals(((CarDTO) obj).id);
    }
}
