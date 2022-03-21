package impl.kevin;

import symmetric.CryptoSystem;

import java.util.Random;

import static impl.kevin.Utils.*;

public class RCTR {
    Random random = new Random();
    int maxBound = 65535; //16 Bit max
    CryptoSystem cryptoSystem;

    public RCTR(CryptoSystem cryptoSystem) {
        this.cryptoSystem = cryptoSystem;
    }

    int getRandomY() {
        return random.nextInt(maxBound);
    }

    byte[][] encrypt(byte[][] message) {

        int y = getRandomY();
        byte[][] encrypted = new byte[message.length + 1][message[0].length];
        encrypted[0] = intToBytes(y, message[0].length, 4);
        for (int i = 0; i < message.length; i++) {
            byte[] xi = message[i];
            int yi = (y + i) % maxBound;
            byte[] yiBytes = intToBytes(yi, xi.length, 4);
            yiBytes = cryptoSystem.encrypt(yiBytes);
            yiBytes = xorByteArrays(yiBytes, xi);
            encrypted[i + 1] = yiBytes;
        }

        return encrypted;
    }

    byte[][] decrypt(byte[][] message) {

        int y = bytesToInt(message[0], 4);
        byte[][] decrypted = new byte[message.length][message[0].length];

        for (int i = 0; i < message.length - 1; i++) {
            System.out.println("Pair y: " + i + " y+1: " + (i + 1));
            byte[] yiBytes = message[i];
//            int yi = bytesToInt(yiBytes, 4);
            int yi = (y + i) % maxBound;
            yiBytes = intToBytes(yi, yiBytes.length, 4);
            yiBytes = cryptoSystem.encrypt(yiBytes);
            byte[] xi = xorByteArrays(yiBytes, message[i + 1]);
            decrypted[i] = xi;
        }

        return decrypted;
    }

}
