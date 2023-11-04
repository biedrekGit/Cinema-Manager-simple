public class CinemaImproved {
    public class Main {

        public static void theMenu() {
            System.out.println();
            System.out.println("""
                1. Show the seats
                2. Buy a ticket
                0. Exit
                """);
        }

        public static void showTheSeats(int n, int p) {
            System.out.println();
            System.out.println("Cinema:");
            System.out.print(" " + " ");
            char[][] cinema = new char[n][p];
            for (int i = 1; i <= p; i++) {
                System.out.print(i + " ");
            }
            System.out.println();
            for (int i = 0; i < n; i++) {
                System.out.print(i + 1 + " ");
                for (int j = 0; j < p; j++) {
                    cinema[i][j] = 'S';
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
            System.out.println("Enter a row number:");
            Scanner scanner = new Scanner(System.in);
            int rowNum = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int seatNum = scanner.nextInt();
            System.out.println();

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

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter the number of rows:");
            int rows = scanner.nextInt();

            System.out.println("Enter the number of seats in each row:");
            int seats = scanner.nextInt();

            char[][] cinemaSeats = new char[rows][seats];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < seats; j++) {
                    cinemaSeats[i][j] = 'S';
                }
            }

            theMenu();

            while (scanner.hasNextInt()) {
                switch (scanner.nextInt()) {
                    case 1 :
                        System.out.println();
                        System.out.println("Cinema:");
                        System.out.print(" " + " ");
                        for (int i = 1; i <= seats; i++) {
                            System.out.print(i + " ");
                        }
                        System.out.println();
                        for (int i = 0; i < rows; i++) {
                            System.out.print(i + 1 + " ");
                            for (int j = 0; j < seats; j++) {
                                System.out.print(cinemaSeats[i][j] + " ");
                            }
                            System.out.println();
                        }
                        System.out.println();
                        theMenu();
                        break;
                    case 2 :
                        buyATicket(rows, seats, cinemaSeats);
                        theMenu();
                        break;
                    case 0 :
                        return;

                }
            }

        }
    }
}
