
import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        YearlyReport yReport = new YearlyReport();
        MonthlyReport mReport = new MonthlyReport();

        boolean yRHasBeenRead = false;
        boolean mRHasBeenRead = false;

        Scanner scanner = new Scanner(System.in);

        while (true) {

            printMenu();

            int command = scanner.nextInt();

            if (command == 1) {
                if (!mRHasBeenRead) {
                    mReport.readMonthlyReport();
                    mRHasBeenRead = true;
                } else {
                    System.out.println("The reports have already been read.");
                }

            } else if (command == 2) {
                if (!yRHasBeenRead) {

                    yReport.readYearlyReport();
                    yRHasBeenRead = true;
                } else {
                    System.out.println("Report has already been read.");
                }

            } else if (command == 3) {
                if ((mRHasBeenRead) && (yRHasBeenRead)) {
                    if ((Objects.equals(mReport.findMonthlyProfits().get(0), yReport.profitsMonthlyInYear().get(0))) &&
                            (Objects.equals(mReport.findMonthlyExpenses().get(0), yReport.expensesMonthlyInYear().get(0)))) {
                        System.out.println("All good with the info for January");
                    } else {
                        System.err.println("Error in January data");
                    }
                    if ((Objects.equals(mReport.findMonthlyProfits().get(1), yReport.profitsMonthlyInYear().get(1))) &&
                            (Objects.equals(mReport.findMonthlyExpenses().get(1), yReport.expensesMonthlyInYear().get(1))))  {
                        System.out.println("All good with the info for February");
                    } else {
                        System.err.println("Error in February data");
                    }
                    if ((Objects.equals(mReport.findMonthlyProfits().get(2), yReport.profitsMonthlyInYear().get(2))) &&
                            (Objects.equals(mReport.findMonthlyExpenses().get(2), yReport.expensesMonthlyInYear().get(2))))  {
                        System.out.println("All good with the info for March");
                    } else {
                        System.err.println("Error in data for March");
                    }
                    // Have excluded the "ERRROORRR" message since if there is any inconsistency
                    // it will be detected by the above code
                }

            } else if (command == 4) {
                if (mRHasBeenRead) {
                    System.out.println("The following information is available regarding monthly reports:");
                    mReport.printMonth();

                } else {
                    System.err.println("You first need to read the reports! Use command no. 1");
                }

            } else if (command == 5) {
                if (yRHasBeenRead) {
                    System.out.println("The following information is available for the yearly report:\n");
                    yReport.profitsMonthlyInYear();
                    System.out.println();
                    yReport.expensesMonthlyInYear();
                    System.out.println();
                    yReport.getAverageExpenses();
                    System.out.println();
                    yReport.getAverageProfits();
                } else {
                    System.err.println("First need to read the reports! i.e. press command no.2");
                }

            } else if (command == 0) {
                System.out.println("You have now exited the program. Will aprreciate your rreview!");
                return;

            } else {
                System.err.println("There is no such command.");
            }
        }
    }
    public static void printMenu() {
        System.out.println("1 - Read monthly reports.");
        System.out.println("2 - Read yearly report.");
        System.out.println("3 - Compare monthly and yearly reports.");
        System.out.println("4 - Print out info re monthly reports.");
        System.out.println("5 - Print out info re yearly report");
        System.out.println("0 - Exit");
    }
}