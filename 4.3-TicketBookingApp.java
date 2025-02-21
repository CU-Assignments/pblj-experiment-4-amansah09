import java.util.Scanner;

class TicketBookingSystem {
    private final String[] seats;
    private final Object lock = new Object();

    public TicketBookingSystem(int totalSeats) {
        seats = new String[totalSeats];
        for (int i = 0; i < totalSeats; i++) {
            seats[i] = "Available";
        }
    }

    public boolean bookTicket(String user, boolean isVIP) {
        synchronized (lock) {
            for (int i = 0; i < seats.length; i++) {
                if (seats[i].equals("Available")) {
                    seats[i] = user;
                    System.out.println("Seat " + (i + 1) + " booked for " + user + ".");
                    return true;
                }
            }
            System.out.println("Sorry, " + user + ". No available seats.");
            return false;
        }
    }

    public void showSeats() {
        System.out.println("Current seat status:");
        for (int i = 0; i < seats.length; i++) {
            System.out.println("Seat " + (i + 1) + ": " + seats[i]);
        }
    }
}

class BookingThread extends Thread {
    private final TicketBookingSystem bookingSystem;
    private final String user;
    private final boolean isVIP;

    public BookingThread(TicketBookingSystem bookingSystem, String user, boolean isVIP) {
        this.bookingSystem = bookingSystem;
        this.user = user;
        this.isVIP = isVIP;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000); // Simulate processing time
            if (isVIP) {
                System.out.println("VIP booking for " + user + " is being processed...");
            } else {
                System.out.println("Booking for " + user + " is being processed...");
            }
            bookingSystem.bookTicket(user, isVIP);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class TicketBookingApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input for total number of seats
        System.out.print("Enter total number of seats: ");
        int totalSeats = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        TicketBookingSystem bookingSystem = new TicketBookingSystem(totalSeats);

        while (true) {
            System.out.print("\nEnter your name to book a ticket (or 'exit' to quit): ");
            String user = scanner.nextLine();
            if (user.equalsIgnoreCase("exit")) {
                break;
            }

            System.out.print("Are you a VIP? (yes/no): ");
            String vipResponse = scanner.nextLine();
            boolean isVIP = vipResponse.equalsIgnoreCase("yes");

            BookingThread bookingThread = new BookingThread(bookingSystem, user, isVIP);

            if (isVIP) {
                bookingThread.setPriority(Thread.MIN_PRIORITY);
            } else {
                bookingThread.setPriority(Thread.NORM_PRIORITY);
            }

            bookingThread.start();
        }

        bookingSystem.showSeats();
        scanner.close();
    }
}
