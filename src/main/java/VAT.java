public enum VAT {
    SIX(6),
    TWELVE(12),
    TWENTYFIVE(25);

    private int percentage;

    VAT(int percentage) {
        this.percentage = percentage;
    }

    public int getVAT() {
        return percentage;
    }

}
