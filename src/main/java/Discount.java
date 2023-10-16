public enum Discount {
    FIVE(5),
    TEN(10),
    TWENTY(20),
    TWENTYFIVE(25),
    FIFTY(50),
    HUNDRED(100);

    private int percentage;

    Discount(int percentage) {
        this.percentage = percentage;
    }

    public int getDiscount() {
        return percentage;
    }

}
