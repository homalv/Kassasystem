public enum VAT {
    LOW(1.06),
    REDUCED(1.12),
    STANDARD(1.25);

    private double percentage;

    VAT(double percentage) {
        this.percentage = percentage;
    }

    public long getVAT(long price) {
        return (long) (price - price / this.percentage);

    }
}
