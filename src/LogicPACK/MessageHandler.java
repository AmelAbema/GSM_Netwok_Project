package LogicPACK;

public interface MessageHandler {
    void handleMessage(Message message);
    void handleException(Exception exception);
}
