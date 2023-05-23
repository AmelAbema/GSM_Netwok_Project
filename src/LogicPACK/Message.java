package LogicPACK;

public record Message(MessageTransmitter sender, MessageReceiver recipient, String content){}