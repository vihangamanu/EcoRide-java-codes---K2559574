// File: K123456_Reservation.java

import java.time.LocalDate;

public class K123456_Reservation {
    private String reservationId;
    private K123456_Customer customer;
    private K123456_Car car;
    private int days;
    private double totalKm;
    private LocalDate bookingDate;
    private LocalDate rentalStartDate;
    private double depositAmount = 5000.0; // fixed as per business rule

    public K123456_Reservation(String reservationId,
                               K123456_Customer customer,
                               K123456_Car car,
                               int days,
                               double totalKm,
                               LocalDate bookingDate,
                               LocalDate rentalStartDate) {
        this.reservationId = reservationId;
        this.customer = customer;
        this.car = car;
        this.days = days;
        this.totalKm = totalKm;
        this.bookingDate = bookingDate;
        this.rentalStartDate = rentalStartDate;
    }

    public String getReservationId() {
        return reservationId;
    }

    public K123456_Customer getCustomer() {
        return customer;
    }

    public K123456_Car getCar() {
        return car;
    }

    public int getDays() {
        return days;
    }

    public double getTotalKm() {
        return totalKm;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public LocalDate getRentalStartDate() {
        return rentalStartDate;
    }

    public double getDepositAmount() {
        return depositAmount;
    }

    @Override
    public String toString() {
        return "Reservation ID: " + reservationId +
               ", Customer: " + customer.getName() +
               ", Car: " + car.getModel() +
               ", Days: " + days +
               ", Total KM: " + totalKm +
               ", Booking Date: " + bookingDate +
               ", Rental Start: " + rentalStartDate;
    }
}
