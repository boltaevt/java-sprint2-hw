import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MonthlyReport {
    ArrayList<MRecord> rows = new ArrayList<>();

    public void readMonthlyReport() {
        for (int i = 1; i < 4; i++) {
            String path = ("resources/m.20210" + i + ".csv");
            String content = readFileContentsOrNull(path);
            String[] lines = content.split("\n");
            for (int j = 1; j < lines.length; j++) {
                int month = i;
                String line = lines[j];
                String[] parts = line.split(",");
                String itemName = (parts[0]);
                boolean isExpense = Boolean.parseBoolean(parts[1]);
                int quantity = Integer.parseInt(parts[2]);
                int sumOfOne = Integer.parseInt(parts[3]);
                MRecord record = new MRecord(itemName, isExpense, quantity, sumOfOne, month);
                rows.add(record);
            }
        }
        System.out.println("Все месячные отчёты успешно считаны!");
    }
    private String readFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл. Возможно, вы указали некорректный путь к файлу.");
            return null;
        }
    }
    public ArrayList<Integer> findMonthlyExpenses() {
        ArrayList<Integer> expenses = new ArrayList<>();
        int expense = 0;
        for (MRecord row : rows) {
            if ((row.month == 1) & (row.isExpense)) {
                expense += row.sumOfOne * row.quantity;
            }
        }
        expenses.add(expense);
        expense = 0;
        for (MRecord row : rows) {
            if ((row.month == 2) && (row.isExpense)) {
                expense += row.sumOfOne * row.quantity;
            }
        }
        expenses.add(expense);
        expense = 0;
        for (MRecord row : rows) {
            if ((row.month == 3) && (row.isExpense)) {
                expense += row.sumOfOne * row.quantity;
            }
        }
        expenses.add(expense);
        return expenses;
    }
    public ArrayList<Integer> findMonthlyProfits() {
        ArrayList<Integer> profits = new ArrayList<>();
        int profit = 0;
        for (MRecord row : rows) {
            if ((row.month == 1) & (!row.isExpense)) {
                profit += row.sumOfOne * row.quantity;
            }
        }
        profits.add(profit);
        profit = 0;
        for (MRecord row : rows) {
            if ((row.month == 2) && (!row.isExpense)) {
                profit += row.sumOfOne * row.quantity;
            }
        }
        profits.add(profit);
        profit = 0;
        for (MRecord row : rows) {
            if ((row.month == 3) && (!row.isExpense)) {
                profit += row.sumOfOne * row.quantity;
            }
        }
        profits.add(profit);
        return profits;
    }
    public void findMonthlyMostExpensiveItem() {
        ArrayList <ExpensiveItem> expensiveItems = new ArrayList<>();
        String itemName = "";
        int costOfItem = 0;
        for (MRecord row : rows) {
            if ((row.month == 1) && (row.isExpense)) {
                if (row.quantity*row.sumOfOne > costOfItem) {
                    costOfItem = row.quantity * row.sumOfOne;
                    itemName = row.itemName;
                }
            }
        }
        ExpensiveItem expItem1 = new ExpensiveItem(itemName, costOfItem);
        System.out.println("В январе самый большой расход был по статье \n\n" + itemName + "\n\nв размере " + costOfItem);
        costOfItem = 0;
        for (MRecord row : rows) {
            if ((row.month == 2) && (row.isExpense)) {
                if (row.quantity*row.sumOfOne > costOfItem) {
                    costOfItem = row.quantity * row.sumOfOne;
                    itemName = row.itemName;
                }
            }
        }
        ExpensiveItem expItem2 = new ExpensiveItem(itemName, costOfItem);
        System.out.println("В феврале самый большой расход был по статье \n\n" + itemName + "\n\nв размере " + costOfItem);
        costOfItem = 0;
        for (MRecord row : rows) {
            if ((row.month == 3) && (row.isExpense)) {
                if (row.quantity*row.sumOfOne > costOfItem) {
                    costOfItem = row.quantity * row.sumOfOne;
                    itemName = row.itemName;
                }
            }
        }
        ExpensiveItem expItem3 = new ExpensiveItem(itemName, costOfItem);
        System.out.println("В марте самый большой расход был по статье \n\n" + itemName + "\n\nв размере " + costOfItem);
    }

    public class ExpensiveItem {
        String itemName;
        int cost;
        public ExpensiveItem(String itemName, int cost) {
            this.itemName = itemName;
            this.cost = cost;
        }
    }

    public void findProfitableItem() {
        ArrayList <ExpensiveItem> profitableItems = new ArrayList<>();
        String itemName = "";
        int profitOfItem = 0;
        for (MRecord row : rows) {
            if ((row.month == 1) && (!row.isExpense)) {
                if (row.quantity*row.sumOfOne > profitOfItem) {
                    profitOfItem = row.quantity * row.sumOfOne;
                    itemName = row.itemName;
                }
            }
        }
        ProfitableItem expItem1 = new ProfitableItem(itemName, profitOfItem);
        System.out.println("В январе самый большой доход принесла статья: \n\n" + itemName + "\n\nв размере: " + profitOfItem);
        profitOfItem = 0;
        for (MRecord row : rows) {
            if ((row.month == 2) && (!row.isExpense)) {
                if (row.quantity*row.sumOfOne > profitOfItem) {
                    profitOfItem = row.quantity * row.sumOfOne;
                    itemName = row.itemName;
                }
            }
        }
        ProfitableItem expItem2 = new ProfitableItem(itemName, profitOfItem);
        System.out.println("В феврале самый большой доход принесла статья: \n\n" + itemName + "\n\nв размере: " + profitOfItem);
        profitOfItem = 0;
        for (MRecord row : rows) {
            if ((row.month == 3) && (!row.isExpense)) {
                if (row.quantity*row.sumOfOne > profitOfItem) {
                    profitOfItem = row.quantity * row.sumOfOne;
                    itemName = row.itemName;
                }
            }
        }
        ProfitableItem expItem3 = new ProfitableItem(itemName, profitOfItem);
        System.out.println("В марте самый большой доход принесла статья: \n\n" + itemName + "\n\nв размере: " + profitOfItem);
    }



    public class ProfitableItem {
        String itemName;
        int cost;

        public ProfitableItem(String itemName, int cost) {
            this.itemName = itemName;
            this.cost = cost;
        }
    }

    }