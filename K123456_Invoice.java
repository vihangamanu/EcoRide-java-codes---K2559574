// File: K123456_Invoice.java

public class K123456_Invoice {
    private K123456_Reservation reservation;
    private double basePrice;
    private double extraKmCharge;
    private double discountAmount;
    private double taxAmount;
    private double finalPayable;

    public K123456_Invoice(K123456_Reservation reservation) {
        this.reservation = reservation;
        calculateInvoice();
    }

    private void calculateInvoice() {
        K123456_Car car = reservation.getCar();

        double dailyRate = car.getDailyRentalPrice();
        int days = reservation.getDays();
        double totalKm = reservation.getTotalKm();

        String category = car.getCategory();
        double freeKmPerDay;
        double extraKmRate;
        double taxRate;

        switch (category.toLowerCase()) {
            case "compact petrol car":
                freeKmPerDay = 100;
                extraKmRate = 50;
                taxRate = 0.10;
                break;
            case "hybrid car":
                freeKmPerDay = 150;
                extraKmRate = 60;
                taxRate = 0.12;
                break;
            case "electric car":
                freeKmPerDay = 200;
                extraKmRate = 40;
                taxRate = 0.08;
                break;
            case "luxury suv":
                freeKmPerDay = 250;
                extraKmRate = 75;
                taxRate = 0.15;
                break;
            default:
                freeKmPerDay = 100;
                extraKmRate = 50;
                taxRate = 0.10;
        }

        basePrice = dailyRate * days;

        double allowedKm = freeKmPerDay * days;
        if (totalKm > allowedKm) {
            double extraKm = totalKm - allowedKm;
            extraKmCharge = extraKm * extraKmRate;
        } else {
            extraKmCharge = 0.0;
        }

        // Discount 10% on base price if days >= 7
        if (days >= 7) {
            discountAmount = 0.10 * basePrice;
        } else {
            discountAmount = 0.0;
        }

        double subtotal = basePrice + extraKmCharge - discountAmount;
        taxAmount = subtotal * taxRate;

        double grossAmount = subtotal + taxAmount;
        finalPayable = grossAmount - reservation.getDepositAmount();
    }

    public void printInvoice() {
        System.out.println("\n=== INVOICE ===");
        System.out.println("Reservation ID: " + reservation.getReservationId());
        System.out.println("Customer: " + reservation.getCustomer().getName());
        System.out.println("Car ID: " + reservation.getCar().getCarId());
        System.out.println("Model: " + reservation.getCar().getModel());
        System.out.println("Category: " + reservation.getCar().getCategory());
        System.out.println("Daily Rate: " + reservation.getCar().getDailyRentalPrice());
        System.out.println("Days: " + reservation.getDays());
        System.out.println("Total KM: " + reservation.getTotalKm());

        System.out.println("\nBase Price: " + basePrice);
        System.out.println("Extra KM Charge: " + extraKmCharge);
        System.out.println("Discount: -" + discountAmount);
        System.out.println("Tax: " + taxAmount);
        System.out.println("Deposit Deducted: -" + reservation.getDepositAmount());
        System.out.println("-----------------------------");
        System.out.println("Final Payable Amount: " + finalPayable);
        System.out.println("=============================\n");
    }
}
