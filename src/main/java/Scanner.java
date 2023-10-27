public interface Scanner {
    String getEAN();

    boolean initialize();

    boolean isConnected();
}
