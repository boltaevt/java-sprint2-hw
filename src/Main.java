import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        YearlyReport yreport = new YearlyReport(2021, "resources/y.2021.csv");
        MonthlyReport mreport1 = new MonthlyReport(01, "resources/m.202101.csv");
        MonthlyReport mreport2 = new MonthlyReport(02, "resources/m.202102.csv");
        MonthlyReport mreport3 = new MonthlyReport(03, "resources/m.202103.csv");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            int command = scanner.nextInt();
            if (command == 1) {
                if ((mreport1 != null) & (mreport2 != null) & (mreport3 != null)) {
                    System.out.println("Всё хорошо, месячные отчеты считаны.");
                }
                else {
                    System.out.println("Где-то закралась ошибка.");
                }
            } else if (command == 2) {
                if (yreport != null) {
                    System.out.println("Годовой отчёт считан");
                }
                else {
                    System.out.println("Где-то закралась ошибка.");
                }
            } else if (command == 3) {
                //сверить доходы за январь
                if ((mreport1.profit() == yreport.profitJan()) & (mreport1.expenditure() == yreport.expenditureJan())) {
                    System.out.println("С данными за январь всё хорошо.");
                } else {
                    System.out.println("Ошибка в данных за январь.");
                }
                if ((mreport2.profit() == yreport.profitFeb()) & (mreport2.expenditure() == yreport.expenditureFeb())) {
                    System.out.println("С данными за февраль всё хорошо.");
                } else {
                    System.out.println("Ошибка в данных за февраль.");
                }
                if ((mreport3.profit() == yreport.profitMar()) & (mreport3.expenditure() == yreport.expenditureMar())) {
                    System.out.println("С данными за март всё хорошо.");
                } else {
                    System.out.println("Ошибка в данных за март.");
                }
            } else if (command == 4) {
                mreport1.printMonthName();
                mreport1.maxExpense();
                mreport1.maxProfit();
                System.out.println();
                mreport2.printMonthName();
                mreport2.maxExpense();
                mreport2.maxProfit();
                System.out.println();
                mreport3.printMonthName();
                mreport3.maxExpense();
                mreport3.maxProfit();
            } else if (command == 5) {
                yreport.printYearlyReport();
            } else if (command == 0) {
                System.out.println("Вы вышли из программы. Будем признательны вашему ревью снова.");
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

