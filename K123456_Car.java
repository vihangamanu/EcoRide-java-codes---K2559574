// File: K123456_Car.java

public class K123456_Car {
    private String carId;
    private String model;
    private String category;          // Compact Petrol, Hybrid, Electric, Luxury SUV
    private double dailyRentalPrice;
    private String availabilityStatus; // Available, Reserved, Under Maintenance

    public K123456_Car(String carId, String model, String category, double dailyRentalPrice, String availabilityStatus) {
        this.carId = carId;
        this.model = model;
        this.category = category;
        this.dailyRentalPrice = dailyRentalPrice;
        this.availabilityStatus = availabilityStatus;
    }

    public String getCarId() {
        return carId;
    }

    public String getModel() {
        return model;
    }

    public String getCategory() {
        return category;
    }

    public double getDailyRentalPrice() {
        return dailyRentalPrice;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDailyRentalPrice(double dailyRentalPrice) {
        this.dailyRentalPrice = dailyRentalPrice;
    }

    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    @Override
    public String toString() {
        return "Car ID: " + carId +
               ", Model: " + model +
               ", Category: " + category +
               ", Daily Rate: " + dailyRentalPrice +
               ", Status: " + availabilityStatus;
    }
}
