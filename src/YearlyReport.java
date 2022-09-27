import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class YearlyReport {
    ArrayList<YRecord> rows = new ArrayList<> ();
    ArrayList<Integer> expenses = new ArrayList<>();
    ArrayList<Integer> profits = new ArrayList<>();
    DecimalFormat df = new DecimalFormat("#.##");

    public void readYearlyReport() {
        String content = readFileContentsOrNull("resources/y.2021.csv");
        if (content != null) {
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
        double averageExpense = 0;
        for (YRecord row : rows) {
            if (row.isExpense) {
                averageExpense += row.amount;
            }
        }
        System.out.println("Average expense: " + df.format(100 * averageExpense / 300));
    }
    public void getAverageProfits() {
        double averageProfit = 0.00;
        for (YRecord row : rows) {
            if (!row.isExpense) {
                averageProfit += row.amount;
            }
        }
        System.out.println("Average profit: " + df.format(100 * averageProfit / 300));
    }
    public void printInfoYear() {
        for (int i = 0; i < 3; i++) {
            String month;
            switch (i) {
                case 0:
                    month = "January";
                    System.out.println(month + " net profit: " + (profitsMonthlyInYear().get(i) - expensesMonthlyInYear().get(i)));
                    break;
                case 1:
                    month = "February";
                    System.out.println(month + " net profit: " + (profitsMonthlyInYear().get(i) - expensesMonthlyInYear().get(i)));
                    break;
                case 2:
                    month = "March";
                    System.out.println(month + " net profit: " + (profitsMonthlyInYear().get(i) - expensesMonthlyInYear().get(i)));
                    break;
                default:
                    System.out.println("The month requested is absent in the yearly report.");
            }
        }
        getAverageExpenses();
        getAverageProfits();
    }
}
