public class Abstraction {
    public static void main(String args[]) {
        FileUploader uploader = new CloudFileUploader();
        uploader.upload("myPhoto.jpg");
    }
}

interface FileUploader {
    void upload(String path);
}

class CloudFileUploader implements FileUploader{

    @Override
    public void upload(String path) {
        validate(path);
        compress(path);
        encrypt(path);
        connectToServer();
        sendFile(path);
        disconnect();
        System.out.println("File saved in cloud.");
    }
    
    private void validate(String path) {
        // Validates the file by checking its size, type etc
    }

    private void compress(String path) {
        // compression algorithm for compressing the file to desired size
    }

    private void encrypt(String path) {
        // encrypts the file before sending it to server
    }

    private void connectToServer() {
        // creates a connection to the server
    }

    private void sendFile(String path) {
        // sends file to the server
    }

    private void disconnect() {
        // closes the connection created with the server
    }
}