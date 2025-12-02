public class Inheritance {
    public static void main(String args[]) {
        Message textMessage = new TextMessage("user1", "user2", "Hello");
        textMessage.send();
        
        Message imageMessage = new ImageMessage("user1", "user2", 128, "myPhoto.jpg");
        imageMessage.send();
        
        EncryptableTextMessage encryptableTextMessage = new EncryptableTextMessage("user1", "user2", "Hello");
        encryptableTextMessage.encrypt();
        encryptableTextMessage.send();
    }
}

//Hierarchial Inheritance - both text message and media message inherited from message
abstract class Message {
    String sender, receiver;
    long timeStamp;
    
    Message(String sender, String receiver) {
        this.sender = sender;
        this.receiver = receiver;
        this.timeStamp = System.currentTimeMillis();
    }
    
    abstract void send();
}

//Single Inheritance - Message -> TextMessage
class TextMessage extends Message {
    String text;

    TextMessage(String sender, String receiver, String text) {
        super(sender, receiver);
        this.text = text;
    }
    
    @Override
    void send() {
        System.out.println(text + " is sending.");
    }
}

abstract class MediaMessage extends Message {
    float size;

    MediaMessage(String sender, String receiver, float size) {
        super(sender, receiver);
        this.size = size;
    }
}

//Multilevel Inheritance - Message -> Media Message -> Image Message
class ImageMessage extends MediaMessage {
    String imageURL;
    
    ImageMessage(String sender, String receiver, float size, String imageURL) {
        super(sender, receiver, size);
        this.imageURL = imageURL;
    }

    @Override
    void send() {
        System.out.println("File " + imageURL + " of Size " + size + " is sending.");
    }
}

interface Encryptable {
    void encrypt();
}

//Multiple inheritance - EncryptedTextMessage inherits both TextMessage and Encryptable.
class EncryptableTextMessage extends TextMessage implements Encryptable {

    public EncryptableTextMessage(String sender, String receiver, String text) {
        super(sender, receiver, text);
    }

    @Override
    public void encrypt() {
        System.out.println("Message is encrypted.");
    }
    
}