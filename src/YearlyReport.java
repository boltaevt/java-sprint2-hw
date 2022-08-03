import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class YearlyReport {
    int year;
    ArrayList<YRecord> rows = new ArrayList<> ();

    public void readYearlyReport() {
        String content = readFileContentsOrNull("resources/y.2021.csv");
        String[] lines = content.split("\n");
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            String[] parts = line.split(",");
            int month = Integer.parseInt(parts[0]);
            //System.out.println(month);
            int amount = Integer.parseInt(parts[1]);
            //System.out.println(amount);
            boolean isExpense = Boolean.parseBoolean(parts[2]);
            //System.out.println(isExpense);
            YRecord record = new YRecord(month, amount, isExpense);
            rows.add(record);
            //System.out.println(rows.get(0).amount);
        }
        System.out.println("Годовой отчёт успешно считан!");
    }

    private String readFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл. Возможно, вы указали некорректный путь к файлу.");
            return null;
        }
    }

    public void printYearlyReport() {
        System.out.println("\nПрибыль по каждому месяцу составила:\n");

        int averageExpenditure = 0;
        int averageProfit = 0;
        int netProfit1 = 0;
        int netProfit2 = 0;
        int netProfit3 = 0;

        for (YRecord row : rows) {
            if (!row.isExpense) {
                row.amount = -row.amount;
                averageExpenditure -= row.amount;
                if (row.month == 1) {
                    netProfit1 += row.amount;
                } else if (row.month == 2) {
                    netProfit2 += row.amount;
                } else {
                    netProfit3 += row.amount;
                }
            } else {
                averageProfit += row.amount;
                if (row.month == 1) {
                    netProfit1 += row.amount;
                } else if (row.month == 2) {
                    netProfit2 += row.amount;
                } else {
                    netProfit3 += row.amount;
                }
            }
        }
        averageProfit = averageProfit / 3;
        averageExpenditure = averageExpenditure / 3;
        System.out.println("\nПрибыль за январь составила: " + netProfit1);
        System.out.println("\nПрибыль за февраль составила: " + netProfit2);
        System.out.println("\nПрибыль за март составила: " + netProfit3);
        System.out.println("\nСредний расход за все месяцы в году составил: " + averageExpenditure);
        System.out.println("\nСредний доход за все месяцы в году составил: " + averageProfit);
    }

    //
    // Не исправил сравнение годового отчёта и месячных отчётов. Предлагается использовать HashMap, где ключем будет либом номер месяца, либо его название.
    // Правильно ли понимаю, что предлагается заменить использование списка в классе YearlyReport? Зато пока делал пришла идея с сохранением значений -
    // в ArrayList, кажется, что это похоже на то, что предлагалось вами.
    //
    public ArrayList<Integer> profitMonthsInYearlyReport() {
        ArrayList<Integer> resProfitMonthsInYearlyReport = new ArrayList<Integer>();
        int profitJan = 0;
        int profitFeb = 0;
        int profitMar = 0;
        for (YRecord row : rows) {
            if (!row.isExpense) {
                if (row.month == 1) {
                    profitJan = row.amount;
                } else if (row.month == 2) {
                    profitFeb = row.amount;
                } else if (row.month == 3) {
                    profitMar = row.amount;
                }
            }
        }
        resProfitMonthsInYearlyReport.add(profitJan);
        resProfitMonthsInYearlyReport.add(profitFeb);
        resProfitMonthsInYearlyReport.add(profitMar);
        //System.out.println(resProfitMonthsInYearlyReport);
        return resProfitMonthsInYearlyReport;
    }
    public ArrayList<Integer> expenditureInYear() {
        ArrayList<Integer> expenditureInYear = new ArrayList<>();
        int expenditureJan = 0;
        int expenditureFeb = 0;
        int expenditureMar = 0;
        for (YRecord row : rows) {
            if (row.isExpense) {
                if (row.month == 1) {
                    expenditureJan = row.amount;
                } else if (row.month == 2) {
                    expenditureFeb = row.amount;
                }
                else if (row.month == 3) {
                    expenditureMar = row.amount;
                }
            }
        }
        expenditureInYear.add(expenditureJan);
        expenditureInYear.add(expenditureFeb);
        expenditureInYear.add(expenditureMar);
        return expenditureInYear;
    }
}


