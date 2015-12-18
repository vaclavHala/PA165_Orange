package cz.muni.fi.pa165.dominatingspecies.dto;

public class AnimalDetailUpdateCharacteristicsDTO {

    private Double foodNeeded;
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
