public enum VAT {
    LITERATURE(1.06),
    FOOD(1.12),
    OFFICE_SUPPLIES(1.25);

    private double percentage;

    VAT(double percentage) {
        this.percentage = percentage;
    }

    public long getVAT(long price) {
        return (long) (price - price / this.percentage);

    }
}
