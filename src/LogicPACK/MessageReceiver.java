package LogicPACK;

public interface MessageReceiver {
    void receiveMessage(Message message);
    void startReceiving();
    void stopReceiving();

}
