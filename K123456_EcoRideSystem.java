// File: K123456_EcoRideSystem.java

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class K123456_EcoRideSystem {

    private List<K123456_Car> cars = new ArrayList<>();
    private List<K123456_Customer> customers = new ArrayList<>();
    private List<K123456_Reservation> reservations = new ArrayList<>();

    private int reservationCounter = 1;

    public K123456_EcoRideSystem() {
        // Optional: preload some cars
        cars.add(new K123456_Car("C001", "Toyota Aqua", "Hybrid Car", 7500, "Available"));
        cars.add(new K123456_Car("C002", "Nissan Leaf", "Electric Car", 10000, "Available"));
        cars.add(new K123456_Car("C003", "Toyota Corolla", "Compact Petrol Car", 5000, "Available"));
        cars.add(new K123456_Car("C004", "BMW X5", "Luxury SUV", 15000, "Under Maintenance"));
    }

    // === MENU METHODS ===

    public void vehicleMenu(Scanner sc) {
        int choice = 0;
        do {
            System.out.println("\n=== Vehicle Management ===");
            System.out.println("1. Add Vehicle");
            System.out.println("2. Update Vehicle");
            System.out.println("3. Remove Vehicle");
            System.out.println("4. Change Availability");
            System.out.println("5. List All Vehicles");
            System.out.println("0. Back");
            System.out.print("Enter choice: ");

            String line = sc.nextLine();
            if (line.isEmpty()) continue;
            choice = Integer.parseInt(line);

            switch (choice) {
                case 1: addCar(sc); break;
                case 2: updateCar(sc); break;
                case 3: removeCar(sc); break;
                case 4: changeCarStatus(sc); break;
                case 5: listCars(); break;
                case 0: break;
                default: System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    public void customerMenu(Scanner sc) {
        int choice = 0;
        do {
            System.out.println("\n=== Customer Management ===");
            System.out.println("1. Register Customer");
            System.out.println("2. List Customers");
            System.out.println("0. Back");
            System.out.print("Enter choice: ");

            String line = sc.nextLine();
            if (line.isEmpty()) continue;
            choice = Integer.parseInt(line);

            switch (choice) {
                case 1: registerCustomer(sc); break;
                case 2: listCustomers(); break;
                case 0: break;
                default: System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    public void reservationMenu(Scanner sc) {
        int choice = 0;
        do {
            System.out.println("\n=== Reservation Management ===");
            System.out.println("1. Make Reservation");
            System.out.println("2. Search Reservation by ID");
            System.out.println("3. Search Reservation by Customer Name");
            System.out.println("4. View Reservations by Rental Date");
            System.out.println("5. Cancel Reservation");
            System.out.println("0. Back");
            System.out.print("Enter choice: ");

            String line = sc.nextLine();
            if (line.isEmpty()) continue;
            choice = Integer.parseInt(line);

            switch (choice) {
                case 1: makeReservation(sc); break;
                case 2: searchReservationById(sc); break;
                case 3: searchReservationByName(sc); break;
                case 4: viewReservationsByDate(sc); break;
                case 5: cancelReservation(sc); break;
                case 0: break;
                default: System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    public void invoiceMenu(Scanner sc) {
        System.out.print("Enter Reservation ID to generate invoice: ");
        String resId = sc.nextLine();
        K123456_Reservation res = findReservationById(resId);
        if (res == null) {
            System.out.println("Reservation not found.");
            return;
        }
        K123456_Invoice invoice = new K123456_Invoice(res);
        invoice.printInvoice();
    }

    // === VEHICLE METHODS ===

    private void addCar(Scanner sc) {
        System.out.print("Enter Car ID: ");
        String id = sc.nextLine();

        // Duplicate Car ID check
        K123456_Car existingCar = findCarById(id);
        if (existingCar != null) {
            System.out.println("Car ID already exists. Please enter a unique ID.");
            return; // stop here – do not add duplicate
        }

        System.out.print("Enter Model: ");
        String model = sc.nextLine();

        System.out.println("Select Category:");
        System.out.println("1. Compact Petrol Car");
        System.out.println("2. Hybrid Car");
        System.out.println("3. Electric Car");
        System.out.println("4. Luxury SUV");
        System.out.print("Enter choice: ");
        int catChoice = Integer.parseInt(sc.nextLine());

        String category;
        double dailyRate;
        switch (catChoice) {
            case 1:
                category = "Compact Petrol Car";
                dailyRate = 5000;
                break;
            case 2:
                category = "Hybrid Car";
                dailyRate = 7500;
                break;
            case 3:
                category = "Electric Car";
                dailyRate = 10000;
                break;
            case 4:
                category = "Luxury SUV";
                dailyRate = 15000;
                break;
            default:
                System.out.println("Invalid category. Car not added.");
                return;
        }

        System.out.print("Enter Availability Status (Available/Reserved/Under Maintenance): ");
        String status = sc.nextLine();

        K123456_Car car = new K123456_Car(id, model, category, dailyRate, status);
        cars.add(car);
        System.out.println("Car added successfully.");
    }

    private void updateCar(Scanner sc) {
        System.out.print("Enter Car ID to update: ");
        String id = sc.nextLine();
        K123456_Car car = findCarById(id);
        if (car == null) {
            System.out.println("Car not found.");
            return;
        }

        System.out.print("Enter new Model (leave blank to keep same): ");
        String model = sc.nextLine();
        if (!model.isEmpty()) car.setModel(model);

        System.out.print("Enter new Daily Rate (leave blank to keep same): ");
        String rateStr = sc.nextLine();
        if (!rateStr.isEmpty()) {
            double rate = Double.parseDouble(rateStr);
            car.setDailyRentalPrice(rate);
        }

        System.out.print("Enter new Status (leave blank to keep same): ");
        String status = sc.nextLine();
        if (!status.isEmpty()) car.setAvailabilityStatus(status);

        System.out.println("Car updated successfully.");
    }

    private void removeCar(Scanner sc) {
        System.out.print("Enter Car ID to remove: ");
        String id = sc.nextLine();
        K123456_Car car = findCarById(id);
        if (car == null) {
            System.out.println("Car not found.");
            return;
        }
        cars.remove(car);
        System.out.println("Car removed successfully.");
    }

    private void changeCarStatus(Scanner sc) {
        System.out.print("Enter Car ID: ");
        String id = sc.nextLine();
        K123456_Car car = findCarById(id);
        if (car == null) {
            System.out.println("Car not found.");
            return;
        }
        System.out.print("Enter new Status (Available/Reserved/Under Maintenance): ");
        String status = sc.nextLine();
        car.setAvailabilityStatus(status);
        System.out.println("Status updated successfully.");
    }

    private void listCars() {
        System.out.println("\n--- Vehicle List ---");
        if (cars.isEmpty()) {
            System.out.println("No vehicles found.");
            return;
        }
        for (K123456_Car car : cars) {
            System.out.println(car);
        }
    }

    private K123456_Car findCarById(String id) {
        for (K123456_Car car : cars) {
            if (car.getCarId().equalsIgnoreCase(id)) {
                return car;
            }
        }
        return null;
    }

    // === CUSTOMER METHODS ===

    private void registerCustomer(Scanner sc) {

        System.out.print("Enter NIC/Passport: ");
        String nic = sc.nextLine();

        // NIC VALIDATION: 12 digits OR 9 digits + V/v
        if (!nic.matches("\\d{12}") && !nic.matches("\\d{9}[Vv]")) {
            System.out.println("Invalid NIC. Please enter a valid NIC number.");
            return;
        }

        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Phone: ");
        String phone = sc.nextLine();
        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        K123456_Customer existing = findCustomerByNic(nic);
        if (existing != null) {
            System.out.println("Customer already exists with this NIC/Passport.");
            return;
        }

        K123456_Customer customer = new K123456_Customer(nic, name, phone, email);
        customers.add(customer);
        System.out.println("Customer registered successfully.");
    }

    private void listCustomers() {
        System.out.println("\n--- Customer List ---");
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
            return;
        }
        for (K123456_Customer c : customers) {
            System.out.println(c);
        }
    }

    private K123456_Customer findCustomerByNic(String nic) {
        for (K123456_Customer c : customers) {
            if (c.getNicOrPassport().equalsIgnoreCase(nic)) {
                return c;
            }
        }
        return null;
    }

    // === RESERVATION METHODS ===

    private void makeReservation(Scanner sc) {
        System.out.print("Enter Customer NIC/Passport: ");
        String nic = sc.nextLine();
        K123456_Customer customer = findCustomerByNic(nic);
        if (customer == null) {
            System.out.println("Customer not found. Please register first.");
            return;
        }

        System.out.println("\nAvailable Cars:");
        boolean anyAvailable = false;
        for (K123456_Car car : cars) {
            if ("Available".equalsIgnoreCase(car.getAvailabilityStatus())) {
                System.out.println(car);
                anyAvailable = true;
            }
        }
        if (!anyAvailable) {
            System.out.println("No cars available for reservation.");
            return;
        }

        System.out.print("Enter Car ID to book: ");
        String carId = sc.nextLine();
        K123456_Car car = findCarById(carId);
        if (car == null) {
            System.out.println("Car not found.");
            return;
        }
        if (!"Available".equalsIgnoreCase(car.getAvailabilityStatus())) {
            System.out.println("Car is not available for reservation.");
            return;
        }

        System.out.print("Enter Rental Start Date (YYYY-MM-DD): ");
        String dateStr = sc.nextLine();
        LocalDate rentalStart;
        try {
            rentalStart = LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format.");
            return;
        }

        LocalDate bookingDate = LocalDate.now();
        if (!bookingDate.isBefore(rentalStart.minusDays(2))) {
            System.out.println("Booking must be made at least 3 days prior to rental start date.");
            return;
        }

        // Validate number of days (> 0)
        System.out.print("Enter Number of Days: ");
        String daysStr = sc.nextLine();
        int days;
        try {
            days = Integer.parseInt(daysStr);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number of days. Please enter a value greater than 0");
            return;
        }
        if (days <= 0) {
            System.out.println("Invalid number of days. Please enter a value greater than 0");
            return;
        }

        System.out.print("Enter Total KM planned: ");
        double km = Double.parseDouble(sc.nextLine());

        String resId = "R" + String.format("%03d", reservationCounter++);
        K123456_Reservation reservation = new K123456_Reservation(
                resId, customer, car, days, km, bookingDate, rentalStart
        );
        reservations.add(reservation);

        car.setAvailabilityStatus("Reserved");

        System.out.println("Reservation created successfully. Reservation ID: " + resId);
    }

    private void cancelReservation(Scanner sc) {
        System.out.print("Enter Reservation ID to cancel: ");
        String id = sc.nextLine();
        K123456_Reservation res = findReservationById(id);
        if (res == null) {
            System.out.println("Reservation not found.");
            return;
        }

        // Free the car
        K123456_Car car = res.getCar();
        if (car != null) {
            car.setAvailabilityStatus("Available");
        }

        // Remove reservation
        reservations.remove(res);

        System.out.println("Reservation cancelled successfully and vehicle marked as Available.");
    }

    private void searchReservationById(Scanner sc) {
        System.out.print("Enter Reservation ID: ");
        String id = sc.nextLine();
        K123456_Reservation res = findReservationById(id);
        if (res == null) {
            System.out.println("Reservation not found.");
        } else {
            System.out.println(res);
        }
    }

    private void searchReservationByName(Scanner sc) {
        System.out.print("Enter Customer Name: ");
        String name = sc.nextLine();
        boolean found = false;
        for (K123456_Reservation r : reservations) {
            if (r.getCustomer().getName().equalsIgnoreCase(name)) {
                System.out.println(r);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No reservations found for this customer.");
        }
    }

    private void viewReservationsByDate(Scanner sc) {
        System.out.print("Enter Rental Date (YYYY-MM-DD): ");
        String dateStr = sc.nextLine();
        LocalDate rentalDate;
        try {
            rentalDate = LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format.");
            return;
        }

        boolean found = false;
        for (K123456_Reservation r : reservations) {
            if (r.getRentalStartDate().equals(rentalDate)) {
                System.out.println(r);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No reservations found for this date.");
        }
    }

    private K123456_Reservation findReservationById(String id) {
        for (K123456_Reservation r : reservations) {
            if (r.getReservationId().equalsIgnoreCase(id)) {
                return r;
            }
        }
        return null;
    }
}
