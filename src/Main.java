import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        YearlyReport yreport = new YearlyReport();
        MonthlyReport mreport = new MonthlyReport();

        boolean yRHasBeenRead = false;
        boolean mRHasBeenRead = false;

        Scanner scanner = new Scanner(System.in);

        while (true) {

            printMenu();

            int command = scanner.nextInt();

            if (command == 1) {
                mreport.readMonthlyReport();
                mRHasBeenRead = true;

            } else if (command == 2) {
                yreport.readYearlyReport();
                yRHasBeenRead = true;
            } else if (command == 3) {
                if ((mRHasBeenRead) && (yRHasBeenRead)) {
                    if ((Objects.equals(mreport.findMonthlyProfits().get(0), yreport.profitsMonthlyInYear().get(0))) &&
                            (Objects.equals(mreport.findMonthlyExpenses().get(0), yreport.expensesMonthlyInYear().get(0)))) {
                        System.out.println("В январе все хорошо");
                    } else {
                        System.out.println("Error in January data");
                    }
                    if ((Objects.equals(mreport.findMonthlyProfits().get(1), yreport.profitsMonthlyInYear().get(1))) &&
                            (Objects.equals(mreport.findMonthlyExpenses().get(1), yreport.expensesMonthlyInYear().get(1))))  {
                        System.out.println("В феврале все хорошо");
                    } else {
                        System.out.println("Error in February data");
                    }
                    if ((Objects.equals(mreport.findMonthlyProfits().get(2), yreport.profitsMonthlyInYear().get(2))) &&
                            (Objects.equals(mreport.findMonthlyExpenses().get(2), yreport.expensesMonthlyInYear().get(2))))  {
                        System.out.println("В марте все хорошо");
                    } else {
                        System.out.println("Error in data for March");
                    }
                } else {
                    System.out.println("ERROOOOR");
                }

            } else if (command == 4) {
                if (mRHasBeenRead) {
                    System.out.println("Вам доступна следующая информация по месяцам: ");
                    System.out.println();
                    mreport.findMonthlyMostExpensiveItem();
                    mreport.findProfitableItem();
                } else {
                    System.out.println("Сначала необходимо считать отчёты!");
                }
            } else if (command == 5) {
                if (yRHasBeenRead) {
                    System.out.println("Вам доступна следующая информация по годовому отчету:\n");
                    yreport.profitsMonthlyInYear();
                    System.out.println();
                    yreport.expensesMonthlyInYear();
                    System.out.println();
                    yreport.getAverageExpenses();
                    System.out.println();
                    yreport.getAverageProfits();
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