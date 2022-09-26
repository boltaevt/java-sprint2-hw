public class MRecord {
    // MRecord Class is created to structure information related to monthly reports.
    String itemName;
    boolean isExpense;
    int quantity;
    int sumOfOne;
    int month;

    public MRecord (String itemName, boolean isExpense, int quantity, int sumOfOne, int month) {
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.sumOfOne = sumOfOne;
        this.month = month;
    }
}



