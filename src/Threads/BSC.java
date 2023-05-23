package Threads;

import LogicPACK.*;

public class BSC implements MessageTransmitter, MessageReceiver, MessageHandler {

    @Override
    public void handleMessage(Message message) {

    }

    @Override
    public void handleException(Exception exception) {

    }

    @Override
    public void receiveMessage(Message message) {

    }


    @Override
    public boolean stationIs(Station station) {
        return false;
    }

    @Override
    public void transmitMessage() {

    }
}
