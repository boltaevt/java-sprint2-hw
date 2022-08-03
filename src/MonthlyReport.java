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
                String line = lines[j];
                String[] parts = line.split(",");
                int month = i;
                //System.out.println(month);
                String itemName = (parts[0]);
                //System.out.println(itemName);
                boolean isExpense = Boolean.parseBoolean(parts[1]);
                //System.out.println(isExpense);
                int quantity = Integer.parseInt(parts[2]);
                //System.out.println(quantity);
                int sumOfOne = Integer.parseInt(parts[3]);
                //System.out.println(sumOfOne);
                MRecord record = new MRecord(month, itemName, isExpense, quantity, sumOfOne);
                //System.out.println(record);
                rows.add(record);
                //System.out.println(rows);
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

    public void printMonthName() {
        String maxExpenseItemName1 = "";
        String maxExpenseItemName2 = "";
        String maxExpenseItemName3 = "";
        String maxProfitItemName1 = "";
        String maxProfitItemName2 = "";
        String maxProfitItemName3 = "";
        int max1 = 0;
        int max2 = 0;
        int max3 = 0;
        int max4 = 0;
        int max5 = 0;
        int max6 = 0;
        for (MRecord row : rows) {
            if ((row.month == 1) & (row.isExpense)) {
                if (row.quantity * row.sumOfOne > max1) {
                    max1 = row.quantity * row.sumOfOne;
                    maxExpenseItemName1 = row.itemName;
                }
            }
        }
        for (MRecord row : rows) {
            if ((row.month == 2) & (row.isExpense)) {
                if (row.quantity * row.sumOfOne > max2) {
                    max2 = row.quantity * row.sumOfOne;
                    maxExpenseItemName2 = row.itemName;
                }
            }
        }
        for (MRecord row : rows) {
            if ((row.month == 3) & (row.isExpense)) {
                if (row.quantity * row.sumOfOne > max3) {
                    max3 = row.quantity * row.sumOfOne;
                    maxExpenseItemName3 = row.itemName;
                }
            }
        }
        for (MRecord row : rows) {
            if ((row.month == 1) & (!row.isExpense)) {
                if (row.quantity * row.sumOfOne > max4) {
                    max4 = row.quantity * row.sumOfOne;
                    maxProfitItemName1 = row.itemName;
                }
            }
        }
        for (MRecord row : rows) {
            if ((row.month == 2) & (!row.isExpense)) {
                if (row.quantity * row.sumOfOne > max5) {
                    max5 = row.quantity * row.sumOfOne;
                    maxProfitItemName2 = row.itemName;
                }
            }
        }
        for (MRecord row : rows) {
            if ((row.month == 3) & (!row.isExpense)) {
                if (row.quantity * row.sumOfOne > max6) {
                    max6 = row.quantity * row.sumOfOne;
                    maxProfitItemName3 = row.itemName;
                }
            }
        }
        System.out.println("В январе самый большой расход прошел по статье: " + maxExpenseItemName1 + " и составил: " + max1 + "\nСамый большой доход принесла статья: " + maxProfitItemName1 + "\nДоход составил: " + max4);
        System.out.println("В феврале самый большой расход прошел по статье: " + maxExpenseItemName2 + " и составил: " + max2 + "\nСамый большой доход принесла статья: " + maxProfitItemName2 + "\nДоход составил: " + max5);
        System.out.println("В марте самый большой расход прошел по статье: " + maxExpenseItemName3 + " и составил: " + max3 + "\nСамый большой доход принесла статья: " + maxProfitItemName3 + "\nДоход составил: " + max6);
    }

    public ArrayList<Integer> profitMonth() {
        ArrayList<Integer> resProfitMonth = new ArrayList<Integer>();
        int profit1 = 0;
        int profit2 = 0;
        int profit3 = 0;
        for (MRecord row : rows) {
            if (!row.isExpense) {
                if (row.month == 1) {
                    profit1 += row.quantity * row.sumOfOne;
                } else if (row.month == 2) {
                    profit2 += row.quantity * row.sumOfOne;
                } else if (row.month == 3) {
                    profit3 += row.quantity * row.sumOfOne;
                }
            }
        }
        resProfitMonth.add(profit1);
        resProfitMonth.add(profit2);
        resProfitMonth.add(profit3);
        System.out.println(resProfitMonth);
        return resProfitMonth;
    }

    public ArrayList<Integer> expenditureByMonths () {
        ArrayList<Integer> expenditureByMonths = new ArrayList<>();
        int expenditure1 = 0;
        int expenditure2 = 0;
        int expenditure3 = 0;
        for (MRecord row : rows) {
            if (row.isExpense) {
                if (row.month == 1) {
                    expenditure1 += row.quantity * row.sumOfOne;
                } else if (row.month == 2) {
                    expenditure2 += row.quantity * row.sumOfOne;
                } else if (row.month == 3) {
                    expenditure3 += row.quantity * row.sumOfOne;
                }
            }
        }
        expenditureByMonths.add(expenditure1);
        expenditureByMonths.add(expenditure2);
        expenditureByMonths.add(expenditure3);
        return expenditureByMonths;
    }
}
