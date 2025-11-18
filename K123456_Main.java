// File: K123456_Main.java

import java.util.Scanner;

public class K123456_Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        K123456_EcoRideSystem system = new K123456_EcoRideSystem();

        int choice = 0;
        do {
            System.out.println("\n=== EcoRide Car Rental System (CLI) ===");
            System.out.println("1. Vehicle Management");
            System.out.println("2. Customer Management");
            System.out.println("3. Reservation Management");
            System.out.println("4. Generate Invoice");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            String line = scanner.nextLine();
            if (line.isEmpty()) continue;
            choice = Integer.parseInt(line);

            switch (choice) {
                case 1:
                    system.vehicleMenu(scanner);
                    break;
                case 2:
                    system.customerMenu(scanner);
                    break;
                case 3:
                    system.reservationMenu(scanner);
                    break;
                case 4:
                    system.invoiceMenu(scanner);
                    break;
                case 0:
                    System.out.println("Exiting system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 0);

        scanner.close();
    }
}
