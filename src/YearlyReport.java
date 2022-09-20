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
            int amount = Integer.parseInt(parts[1]);
            boolean isExpense = Boolean.parseBoolean(parts[2]);
            YRecord record = new YRecord(month, amount, isExpense);
            rows.add(record);
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

    public ArrayList<Integer> profitsMonthlyInYear() {
        ArrayList<Integer> profits = new ArrayList<>();
        int profit = 0;
        for (YRecord row : rows) {
            if (row.month == 1 && !row.isExpense) {
                profit += row.amount;
            }
        }
        profits.add(profit);
        profit = 0;
        for (YRecord row : rows) {
            if (row.month == 2 && !row.isExpense) {
                profit += row.amount;
            }
        }
        profits.add(profit);
        profit = 0;
        for (YRecord row : rows) {
            if (row.month == 3 && !row.isExpense) {
                profit += row.amount;
            }
        }
        profits.add(profit);
        return profits;
    }
    public ArrayList<Integer> expensesMonthlyInYear() {
        ArrayList<Integer> expenses = new ArrayList<>();
        int expense = 0;
        for (YRecord row : rows) {
            if (row.month == 1 && row.isExpense) {
                expense += row.amount;
            }
        }
        expenses.add(expense);
        expense = 0;
        for (YRecord row : rows) {
            if (row.month == 2 && row.isExpense) {
                expense += row.amount;
            }
        }
        expenses.add(expense);
        expense = 0;
        for (YRecord row : rows) {
            if (row.month == 3 && row.isExpense) {
                expense += row.amount;
            }
        }
        expenses.add(expense);
        return expenses;
    }
    public void getAverageExpenses() {
        int averageExpense = 0;
        for (YRecord row : rows) {
            if (row.isExpense) {
                averageExpense += row.amount;
            }
        }
        System.out.println("Средний расход составил: " + (averageExpense / 3));
    }
    public void getAverageProfits() {
        int averageProfit = 0;
        for (YRecord row : rows) {
            if (!row.isExpense) {
                averageProfit += row.amount;
            }
        }
        System.out.println("Средний доход составил: " + (averageProfit / 3));
    }
}


