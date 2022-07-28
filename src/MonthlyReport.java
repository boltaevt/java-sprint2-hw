import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MonthlyReport {
    int month;
    ArrayList<MRecord> rows = new ArrayList<> ();

    public MonthlyReport(int month, String path) {
        this.month = month;
        String content = readFileContentsOrNull(path);
        String[] lines = content.split("\n");
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            String[] parts = line.split(",");
            String itemName = (parts[0]);
            boolean isExpense = Boolean.parseBoolean(parts[1]);
            int quantity = Integer.parseInt(parts[2]);
            int sumOfOne = Integer.parseInt(parts[3]);
            MRecord record = new MRecord(itemName, isExpense, quantity, sumOfOne);
            rows.add(record);
        }
    }

    public void printMonthName() {
        if (month == 1) {
            System.out.println("Январь");
        } else if (month == 2) {
            System.out.println("Февраль");
        } else if (month == 3) {
            System.out.println("Март");
        } else {
            System.out.println("Информация об иных месяцах в данный момент отсутствует");
        }
    }

    public void maxExpense() {
        int max = 0;
        String maxItemName = " ";
        for (MRecord row : rows) {
            if (row.isExpense) {
                if (row.quantity * row.sumOfOne > max) {
                    max = row.quantity * row.sumOfOne;
                    maxItemName = row.itemName;
                }
            }
        }
        System.out.println("\nСамая большая простая трата: " + max + "\nНазвание товара: " + maxItemName);
    }

    public void maxProfit() {
        int max = 0;
        String maxProfitItemName = " ";
        for (MRecord row : rows) {
            if (!row.isExpense) {
                if (row.quantity * row.sumOfOne > max) {
                    max = row.quantity * row.sumOfOne;
                    maxProfitItemName = row.itemName;
                }
            }
        }
        System.out.println("\nСамый большой доход: " + max + "\nНазвание товара: " + maxProfitItemName);
    }
    public int profit() {
        int profit = 0;
        for (MRecord row : rows) {
            if (!row.isExpense) {
                profit += row.quantity * row.sumOfOne;
            }
        }
        return profit;
    }

    public int expenditure() {
        int expenditure = 0;
        for (MRecord row : rows) {
            if (row.isExpense) {
                expenditure += row.quantity * row.sumOfOne;
            }
        }
        return expenditure;
    }

    /*
        public void printMonthlyReport() {
            System.out.println("\nГодовой отчёт за " + year + " год.");
            System.out.println("\nПрибыль по каждому месяцу составила:");
            int averExpend = 0;
            int averProfit = 0;
            int netProfit1 = 0;
            int netProfit2 = 0;
            int netProfit3 = 0;

            for (YRecord row : rows) {
                if (!row.isExpense) {
                    row.amount = -row.amount;
                    averExpend -= row.amount;

                    if (row.month == 1) {
                        netProfit1 += row.amount;
                    } else if (row.month == 2) {
                        netProfit2 += row.amount;
                    } else {
                        netProfit3 += row.amount;
                    }
                    //System.out.println(row.amount);
                } else {
                    averProfit += row.amount;

                    if (row.month == 1) {
                        netProfit1 += row.amount;
                    } else if (row.month == 2) {
                        netProfit2 += row.amount;
                    } else {
                        netProfit3 += row.amount;
                    }
                }
            }
            averProfit = averProfit / 3;
            averExpend = averExpend / 3;
            System.out.println("\nПрибыль за январь составила: " + netProfit1);
            System.out.println("\nПрибыль за февраль составила: " + netProfit2);
            System.out.println("\nПрибыль за март составила: " + netProfit3);

            // Понимаю, что реализация метода в части подсчета прибыли за месяц неоптимальна.
            // Был бы признателен комментарию как лучше реализовать.
            // Например, думал добавлять, если true, вычитать, если false, при условии,
            // что месяцы совпадают, но не понимаю как это сделать.

            System.out.println("\nСредний расход за все месяцы в году составил: " + averExpend + " канадских долларов.");
            System.out.println("\nСредний доход за все месяцы в году составил: " + averProfit + " канадских долларов.\n");
        }


    */
    private String readFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл. Возможно, вы указали некорректный путь к файлу.");
            return null;
        }
    }

    //public void readYReport() {
    //  System.out.println("\nВы успешно считали годовой отчёт, если не видите ошибки и программа выполняется\n\n:))\n");
    //}
    //понимаю, что метод readFileContentsOrNull отвечает за считывание отчёта.
    //но отчёт создается и считывается в момент получения конструктором вводных.
    //не понимаю как обратиться именно к методу по считыванию при соответствующей команде в консоли.
    //Или просто включить создание конструктора в соответствующую команду в Main?

}


