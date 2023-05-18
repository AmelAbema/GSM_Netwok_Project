package LogicPACK;

public interface MessageHandler {
    void handleMessage(String message);
    void handleException(Exception exception);
}
