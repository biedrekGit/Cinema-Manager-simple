import java.util.Scanner;

public class Main {

    public static void theMenu() {
        System.out.println();
        System.out.println("""
                1. Show the seats
                2. Buy a ticket
                3. Statistics
                0. Exit
                """);
    }

    public static void showTheSeats(int n, int p, char[][] cinema) {
        System.out.println("\nCinema:");
        System.out.print(" " + " ");
        for (int i = 1; i <= p; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < p; j++) {
                if (cinema[i][j] == 0) {
                    cinema[i][j] = 'S';
                }
                System.out.print(cinema[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void buyATicket(int n, int p, char[][] cinema) {
        if (cinema.length < 1) {
            return;
        }
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nEnter a row number:");
        int rowNum = 0;
        while (scanner.hasNextInt()) {
            int rowInput = scanner.nextInt();
            if (rowInput > n || rowInput <= 0) {
                System.out.println("\nWrong input!\n\nEnter a row number:");
            } else {
                rowNum = rowInput;
                break;
            }
        }


        System.out.println("Enter a seat number in that row:");
        int seatNum = 0;
        while (scanner.hasNextInt()) {
            int seatInput = scanner.nextInt();
            if (seatInput > p || seatInput <= 0) {
                System.out.println("\nWrong input!\n\nEnter a row number:");
            } else {
                seatNum = seatInput;
                break;
            }
        }

        if (cinema[rowNum - 1][seatNum - 1] == 'B') {
            System.out.println("\nThat ticket has already been purchased!");
            buyATicket(n, p, cinema);
        }

        int ticketPrice;
        if (n * p <= 60) {
            ticketPrice = 10;
        } else {
            if (rowNum <= n / 2) {
                ticketPrice = 10;
            } else {
                ticketPrice = 8;
            }
        }
        System.out.println("Ticket price:" + " " + '$' + ticketPrice);
        System.out.println();

        cinema[rowNum - 1][seatNum - 1] = 'B';

    }

    public static void staTs(int n, int p, char[][] cinema) {
        double counter = 0.00; //number of purchased tickets
        double counter2 = 0.00; //number of free seats left
        for (char[] eachRow : cinema) {
            for (char ch : eachRow) {
                if (ch == 'B') {
                    counter++;
                } else {
                    counter2++;
                }
            }
        } //counting number of purchased seats
        double perc = counter * 100 / (counter + counter2); //number of purchased tickets represented as a percentage

        //System.out.println();
        System.out.printf("\nNumber of purchased tickets: %.0f", counter);
        System.out.println();
        System.out.printf("Percentage: %.2f", perc);
        System.out.print('%');
        System.out.println();

        int ticket10 = 0; //number of purchased 10$ tickets
        int ticket8 = 0; //number of purchased 8$ tickets
        int totalIncome;
        if (n * p <= 60) {
            totalIncome = 10 * n * p;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < p; j++) {
                    if (cinema[i][j] == 'B') {
                        ticket10++;
                    }
                }
            } //counting purchased 10$ tickets in cinema with seats <= 60
        } else {
            totalIncome = 10 * ( n - 1 ) / 2 * p + 8 * ( n - ( n - 1 ) / 2 ) * p; //OK
            for (int i = 0; i < (n - 1) / 2; i++) {
                for (int j = 0; j < p; j++) {
                    if (cinema[i][j] == 'B') {
                        ticket10++;
                    }
                }
            } //counting purchased 10$ tickets in cinema with seats > 60
            for (int i = (n - 1) / 2; i < n; i++) {
                for (int j = 0; j < p; j++) {
                    if (cinema[i][j] == 'B') {
                        ticket8++;
                    }
                }
            } //counting purchased 8$ tickets in cinema with seats > 60

        }
        int income = ticket10 * 10 + ticket8 * 8;
        System.out.println("Current income: $" + income);
        System.out.println("Total income: $" + totalIncome);

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();

        char[][] cinemaSeats = new char[rows][seats];

        theMenu();
        while (scanner.hasNextInt()) {
            switch (scanner.nextInt()) {
                case 1 :
                    showTheSeats(rows, seats, cinemaSeats);
                    theMenu();
                    break;
                case 2 :
                    buyATicket(rows, seats, cinemaSeats);
                    theMenu();
                    break;
                case 3 :
                    staTs(rows, seats, cinemaSeats);
                    theMenu();
                    break;
                case 0 :
                    return;
                default :
                    System.out.println("Error. Choose one of the options.");
                    theMenu();
                    break;
            }
        }

    }
}