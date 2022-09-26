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
        System.out.println("Yearly report has been successfully read!");
    }

    private String readFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Pathway to file cannot be read.");
            return null;
        }
    }

    public ArrayList<Integer> profitsMonthlyInYear() {
        ArrayList<Integer> profits = new ArrayList<>();
        int profit = 0;

        for (int i = 1; i < 4; i++) {
            for (YRecord row : rows) {
                if (row.month == i && !row.isExpense) {
                    profit += row.amount;
                }
            }
            profits.add(profit);
            profit = 0;

        }
        return profits;
    }

    public ArrayList<Integer> expensesMonthlyInYear() {
        ArrayList<Integer> expenses = new ArrayList<>();
        int expense = 0;

        for (int i = 1; i < 4; i++) {
            for (YRecord row : rows) {
                if (row.month == i && row.isExpense) {
                    expense += row.amount;
                }
            }
            expenses.add(expense);
            expense = 0;
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
        System.out.println("Average expense: " + (averageExpense / 3));
    }
    public void getAverageProfits() {
        int averageProfit = 0;
        for (YRecord row : rows) {
            if (!row.isExpense) {
                averageProfit += row.amount;
            }
        }
        System.out.println("Average profit: " + (averageProfit / 3));
    }
}
