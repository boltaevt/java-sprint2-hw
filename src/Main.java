import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        YearlyReport yreport = new YearlyReport();
        MonthlyReport mreport = new MonthlyReport();
        boolean yearlyReportHasBeenRead = false;
        boolean monthlyReportHasBeenRead = false;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            int command = scanner.nextInt();
            if (command == 1) {
                mreport.readMonthlyReport();
                monthlyReportHasBeenRead = true;
            } else if (command == 2) {
                yreport.readYearlyReport();
                yearlyReportHasBeenRead = true;
            } else if (command == 3) {
                if ((monthlyReportHasBeenRead) & (yearlyReportHasBeenRead)) {
                    if ((Objects.equals(mreport.profitMonth().get(0), yreport.profitMonthsInYearlyReport().get(0))) & (Objects.equals(mreport.expenditureByMonths().get(0), yreport.expenditureInYear().get(0)))) {
                        System.out.println("С январем проблем нет!");
                    } else {
                        System.out.println("Error in January data");
                    };
                    if ((Objects.equals(mreport.profitMonth().get(1), yreport.profitMonthsInYearlyReport().get(1))) & (Objects.equals(mreport.expenditureByMonths().get(1), yreport.expenditureInYear().get(1)))) {
                        System.out.println("С февралем проблем нет!");
                    } else {
                        System.out.println("Error in February data");
                    };
                    if ((Objects.equals(mreport.profitMonth().get(2), yreport.profitMonthsInYearlyReport().get(2))) & (Objects.equals(mreport.expenditureByMonths().get(2), yreport.expenditureInYear().get(2)))) {
                        System.out.println("С мартом проблем нет!");
                    } else {
                        System.out.println("Error in March data");
                    }
                } else {
                    System.out.println("ERROOOOR");
                }
            } else if (command == 4) {
                if (monthlyReportHasBeenRead) {
                    mreport.printMonthName();
                } else {
                    System.out.println("Сначала необходимо считать отчёты!");
                }
            } else if (command == 5) {
                if (yearlyReportHasBeenRead) {
                    yreport.printYearlyReport();
                } else {
                    System.out.println("Сначала необходимо считать отчёт!");
                }
            } else if (command == 0) {
                System.out.println("Вы вышли из программы. Будем признательны вашему ревью.");
                return;

            } else {
                System.out.println("Такой команды нет.");
            }
        }
    }
    public static void printMenu() {
        System.out.println("1 - Считать все месячные отчёты.");
        System.out.println("2 - Считать годовой отчёт.");
        System.out.println("3 - Сверить отчёты.");
        System.out.println("4 - Вывести информацию о всех месячных отчётах.");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("0 - Выйти");
    }
}