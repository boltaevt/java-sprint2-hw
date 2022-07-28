import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class YearlyReport {
    int year;
    ArrayList<YRecord> rows = new ArrayList<> ();

    public YearlyReport(int year, String path) {
        this.year = year;
        String content = readFileContentsOrNull(path);
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

    public int maxExpense() {
        int max = 0;
        for (YRecord row : rows) {
            if (row.isExpense) {
                if (row.amount > max) {
                    max = row.amount;
                }
            }
        }
        return max;
    }

    public int profitJan() {
        int profitJan = 0;
        for (YRecord row : rows) {
            if (row.month == 1) {
                if (!row.isExpense) {
                    profitJan = row.amount;
                }
            }
        }
        return profitJan;
    }

    public int profitFeb() {
        int profitFeb = 0;
        for (YRecord row : rows) {
            if (row.month == 2) {
                if (!row.isExpense) {
                    profitFeb = row.amount;
                }
            }
        }
        return profitFeb;
    }

    public int profitMar() {
        int profitMar = 0;
        for (YRecord row : rows) {
            if (row.month == 3) {
                if (!row.isExpense) {
                    profitMar = row.amount;
                }
            }
        }
        return profitMar;
    }


    public int expenditureJan() {
        int expenditureJan = 0;
        for (YRecord row : rows) {
            if (row.month == 1) {
                if (row.isExpense) {
                    expenditureJan = row.amount;
                }
            }
        }
        return expenditureJan;
    }

    public int expenditureFeb() {
        int expenditureFeb = 0;
        for (YRecord row : rows) {
            if (row.month == 2) {
                if (row.isExpense) {
                    expenditureFeb = row.amount;
                }
            }
        }
        return expenditureFeb;
    }

    public int expenditureMar() {
        int expenditureMar = 0;
        for (YRecord row : rows) {
            if (row.month == 3) {
                if (row.isExpense) {
                    expenditureMar = row.amount;
                }
            }
        }
        return expenditureMar;
    }


    public void printYearlyReport() {
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



    private String readFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл. Возможно, вы указали некорректный путь к файлу.");
            return null;
        }
    }

    public void readYReport() {

        System.out.println("\nВы успешно считали годовой отчёт, если не видите ошибки и программа выполняется\n\n:))\n");
    }
    //понимаю, что метод readFileContentsOrNull отвечает за считывание отчёта.
    //но отчёт создается и считывается в момент получения конструктором вводных.
    //не понимаю как обратиться именно к методу по считыванию при соответствующей команде в консоли.
    //Или просто включить создание конструктора в соответствующую команду в Main?
}


