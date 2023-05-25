package Utils;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

public class PduEncoderDecoder {

    public static byte[] encoder(String phoneNumber, String message) {
        ByteArrayOutputStream pduStream = new ByteArrayOutputStream();

        // Add SMSC information (length of 0, as we assume it's already set on the device)
        pduStream.write(0x00);

        // Add PDU type (0x01 for SMS-SUBMIT)
        pduStream.write(0x01);

        // Determine the type of address
        boolean isInternational = phoneNumber.startsWith("+");

        // Add phone number length
        int phoneNumberLength = phoneNumber.length();
        pduStream.write(phoneNumberLength);

        // Add type of address
        pduStream.write(isInternational ? 0x91 : 0x81);

        // Add phone number
        for (int i = 0; i < phoneNumberLength; i++) {
            char c = phoneNumber.charAt(i);
            pduStream.write((byte) c);
        }

        // Add protocol identifier (0x00 for default)
        pduStream.write(0x00);

        // Add data coding scheme (0x00 for default)
        pduStream.write(0x00);

        // Add message length in octets
        int messageLength = message.getBytes(StandardCharsets.UTF_8).length;
        pduStream.write(messageLength);

        // Add message
        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
        pduStream.write(messageBytes, 0, messageBytes.length);

        return pduStream.toByteArray();
    }



    public static String[] decoder(byte[] pdu) {
        StringBuilder phoneNumberBuilder = new StringBuilder();
        StringBuilder messageBuilder = new StringBuilder();

        // Skip the SMSC information (first byte)
        int currentIndex = 1;

        // Skip the PDU type (next byte)
        currentIndex++;

        // Get the phone number length
        int phoneNumberLength = pdu[currentIndex];
        currentIndex++;

        // Get the type of address
        String addressType = (pdu[currentIndex] == (byte) 0x91) ? "+" : "";
        currentIndex++;

        // Get the phone number
        for (int i = 0; i < phoneNumberLength; i++) {
            char c = (char) (pdu[currentIndex] & 0xFF);
            phoneNumberBuilder.append(c);
            currentIndex++;
        }

        // If the address type is not included in the phone number, add it
        String phoneNumber = phoneNumberBuilder.toString();
        if (!phoneNumber.startsWith("+")) {
            phoneNumber = addressType + phoneNumber;
        }

        // Skip the protocol identifier and data coding scheme (2 bytes)
        currentIndex += 2;

        // Get the message length in octets
        int messageLength = pdu[currentIndex];
        currentIndex++;

        // Get the message
        for (int i = 0; i < messageLength; i++) {
            byte b = pdu[currentIndex];
            messageBuilder.append((char) b);
            currentIndex++;
        }

        String message = messageBuilder.toString();

        return new String[]{phoneNumber, message};
    }

}

