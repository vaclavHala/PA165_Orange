package cz.muni.fi.pa165.dominatingspecies.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class AnimalDetailUpdateCharacteristicsDTO {

    @NotNull
    @Min(value = 0)
    private Double foodNeeded;
    @NotNull
    @Min(value = 0)
    private Double reproductionRate;

    public Double getFoodNeeded() {
        return foodNeeded;
    }

    public void setFoodNeeded(Double foodNeeded) {
        this.foodNeeded = foodNeeded;
    }

    public Double getReproductionRate() {
        return reproductionRate;
    }

    public void setReproductionRate(Double reproductionRate) {
        this.reproductionRate = reproductionRate;
    }
}
