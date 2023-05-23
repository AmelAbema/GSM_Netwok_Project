package Threads;

import LogicPACK.Message;
import LogicPACK.MessageReceiver;
import LogicPACK.MessageTransmitter;
import LogicPACK.Station;

public class BTS extends Thread implements MessageTransmitter, MessageReceiver {
    Station station = Station.BTS;
    Message message;
    public BTS() {
        start();
    }

    @Override
    public void run() {

    }

    @Override
    public void receiveMessage(Message message) {

    }

    @Override
    public boolean stationIs(Station station) {
            return this.station == station;
    }

    @Override
    public void transmitMessage() {

    }
}
