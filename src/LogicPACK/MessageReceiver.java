package LogicPACK;

public interface MessageReceiver {
    void receiveMessage(Message message);

    boolean stationIs(Station station);
}
