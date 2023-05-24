package Logic;

public class Cipher {
    public static byte[] intToBytes(final int data) {
        return new byte[] {
                (byte)((data >> 24) & 0xff),
                (byte)((data >> 16) & 0xff),
                (byte)((data >> 8) & 0xff),
                (byte)((data) & 0xff),
        };
    }
    public static int convertByteArrayToInt(final byte[] data) {
        if (data == null || data.length != 4) return 0x0;
        // ----------
        // NOTE: type cast not necessary for int
        return (0xff & data[0]) << 24  |
                (0xff & data[1]) << 16  |
                (0xff & data[2]) << 8   |
                (0xff & data[3]);
    }
}
