import org.w3c.dom.ls.LSOutput;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MonthlyReport {
    ArrayList<MRecord> rows = new ArrayList<>();

    public void readMonthlyReport() {

        List files = new ArrayList<>();
        List directories = new ArrayList<>();
        String fileExtension = ".csv";

        File folder = new File("/Users/boltaevt/Dev/java-sprint2-hw/resources");
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                if (listOfFiles[i].getName().endsWith(fileExtension) && listOfFiles[i].getName().startsWith("m")) {
                    files.add(listOfFiles[i].getName());
                }
            }
        }

        for (int i = 1; i <= files.size(); i++) {
            String path = ("resources/" + listOfFiles[i].getName());
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
        System.out.println("All monthly reports successfully read!");
    }
    private String readFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Not possible to read file. Please check file pathway!");
            return null;
        }
    }
    public ArrayList<Integer> findMonthlyExpenses() {
        ArrayList<Integer> expenses = new ArrayList<>();
        int expense = 0;

        for (int i = 1; i < 4; i++) {
            for (MRecord row : rows) {
                if ((row.month == i) & (row.isExpense)) {
                    expense += row.sumOfOne * row.quantity;
                }
            }
            expenses.add(expense);
            expense = 0;
        }
        return expenses;
    }

    public ArrayList<Integer> findMonthlyProfits() {
        ArrayList<Integer> profits = new ArrayList<>();
        int profit = 0;
        for (int i = 1; i < 4; i++) {
            for (MRecord row : rows) {
                if ((row.month == i) & (!row.isExpense)) {
                    profit += row.sumOfOne * row.quantity;
                }
            }
            profits.add(profit);
            profit = 0;
        }
        return profits;
    }




    public void printMonth(){
        ProfitableItem prItem = new ProfitableItem();
        ExpensiveItem exItem = new ExpensiveItem();
        prItem.addProfitableItem();
        exItem.addExpensiveItem();
        for (int i = 0; i < 3; i++) {
            String monthName = "";
            switch (i){
                case 0: monthName = "January";
                    System.out.println("The most profitable item in " + monthName + " is "
                    + prItem.profitableItems.get(i).itemName + " with the cost of " + prItem.profitableItems.get(i).cost);
                    System.out.println("The most expensive item in " + monthName + " is "
                            + exItem.expensiveItems.get(i).itemName + " with the cost of " + exItem.expensiveItems.get(i).cost);
                    break;
                case 1: monthName = "February";
                    System.out.println("The most profitable item in " + monthName + " is "
                            + prItem.profitableItems.get(i).itemName + " with the cost of " + prItem.profitableItems.get(i).cost);
                    System.out.println("The most expensive item in " + monthName + " is "
                            + exItem.expensiveItems.get(i).itemName + " with the cost of " + exItem.expensiveItems.get(i).cost);
                    break;
                case 2: monthName = "March";
                    System.out.println("The most profitable item in " + monthName + " is "
                            + prItem.profitableItems.get(i).itemName + " with the cost of " + prItem.profitableItems.get(i).cost);
                    System.out.println("The most expensive item in " + monthName + " is "
                            + exItem.expensiveItems.get(i).itemName + " with the cost of " + exItem.expensiveItems.get(i).cost);
                    break;
                default:
                    System.out.println("There is presently no information on the month in case.");
            }
        }
    }

    public class ProfitableItem {
        String itemName;
        int cost;
        public ProfitableItem(String itemName, int cost) {
            this.itemName = itemName;
            this.cost = cost;
        }
        public ProfitableItem() {
        }
        ArrayList <ProfitableItem> profitableItems = new ArrayList<>();

        public ArrayList<ProfitableItem> addProfitableItem() {
            String itemName = "";
            int profitOfItem = 0;
            for (int i = 1; i < 4; i++) {
                for (MRecord row : rows) {
                    if ((row.month == i) && (!row.isExpense)) {
                        if (row.quantity*row.sumOfOne > profitOfItem) {
                            profitOfItem = row.quantity * row.sumOfOne;
                            itemName = row.itemName;
                            ProfitableItem profItem = new ProfitableItem(itemName, profitOfItem);
                        }
                    }
                }
                ProfitableItem profItem = new ProfitableItem(itemName, profitOfItem);
                profitableItems.add(profItem);
                profitOfItem = 0;
            }
            return profitableItems;
        }
    }
    public class ExpensiveItem {
        String itemName;
        int cost;
        public ExpensiveItem(String itemName, int cost) {
            this.itemName = itemName;
            this.cost = cost;
        }
        public ExpensiveItem() {

        }

        ArrayList<ExpensiveItem> expensiveItems = new ArrayList<>();

        public ArrayList<ExpensiveItem> addExpensiveItem() {
            String itemName = "";
            int costOfItem = 0;
            for (int i = 1; i < 4; i++) {
                for (MRecord row : rows) {
                    if ((row.month == i) && (row.isExpense)) {
                        if (row.quantity * row.sumOfOne > costOfItem) {
                            costOfItem = row.quantity * row.sumOfOne;
                            itemName = row.itemName;
                        }
                    }
                }
                ExpensiveItem expItem = new ExpensiveItem(itemName, costOfItem);
                expensiveItems.add(expItem);
                costOfItem = 0;
            }
            return expensiveItems;
        }
    }


}

