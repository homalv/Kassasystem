public enum Category {
    LITERATURE("Literature"),
    FOOD("Food"),
    OFFICE_SUPPLIES("Office Supplies");

    private String category;

    private Category(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}
