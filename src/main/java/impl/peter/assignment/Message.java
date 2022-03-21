package impl.peter.assignment;

public class Message {
    private static String message;

    public Message () {
        message = "00000100110100100000101110111000000000101000111110001110011111110110000001010001010000111010000000010011011001110010101110110000";
    }

    public String getPaddedMessage() {
        StringBuilder newMessage = new StringBuilder();
        newMessage.append(message);
        newMessage.append("1");
        while(newMessage.length() % 16 != 0) newMessage.append("0");
        return newMessage.toString();
    }

    public String getMessage() {
        return message;
    }
}
