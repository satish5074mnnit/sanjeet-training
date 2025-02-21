//import javax.websocket.*;
//import java.io.*;
//import java.net.URI;
//import java.nio.ByteBuffer;
//
//@ClientEndpoint
//public class MediaStreamClient {
//
//    private Session session;
//
//    @OnOpen
//    public void onOpen(Session session) {
//        this.session = session;
//        System.out.println("Connected to WebSocket server.");
//    }
//
//    @OnMessage
//    public void onMessage(ByteBuffer message) {
//        // Determine the type of message (audio/video/text)
//        byte[] data = new byte[message.remaining()];
//        message.get(data);
//
//        String messageType = new String(data, 0, 5);  // Assuming the first 5 bytes contain the type
//
//        if ("audio".equals(messageType) || "video".equals(messageType)) {
//            // Handle Audio/Video
//            processMedia(message);
//        } else if ("text".equals(messageType)) {
//            // Handle Text (chat messages, metadata, etc.)
//            processText(message);
//        }
//    }
//
//    private void processMedia(ByteBuffer mediaData) {
//        // If audio or video, decode and play/display accordingly
//        System.out.println("Received media data.");
//        byte[] media = new byte[mediaData.remaining()];
//        mediaData.get(media);
//
//        // Example: If it's audio, you can play it using Java Sound API or external libraries.
//        // If it's video, you might need a media decoder (e.g., JavaFX, VLCJ, etc.)
//
//        // Example for audio playback:
//        // playAudio(media);
//
//        // Example for video playback:
//        // renderVideo(media);
//    }
//
//    private void processText(ByteBuffer textData) {
//        // Handle text messages (e.g., chat messages)
//        String textMessage = new String(textData.array(), textData.position(), textData.remaining());
//        System.out.println("Received text message: " + textMessage);
//    }
//
//    // Method to send audio/video/text data
//    public void sendAudioVideoText(String messageType, byte[] data) {
//        try {
//            ByteBuffer buffer = ByteBuffer.allocate(data.length + 5);  // +5 for message type identifier
//            buffer.put(messageType.getBytes());  // First 5 bytes as the type identifier (audio/video/text)
//            buffer.put(data);  // Actual data (audio/video/text)
//            buffer.flip();
//            session.getBasicRemote().sendBinary(buffer);  // Send the data as binary
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) {
//        MediaStreamClient client = new MediaStreamClient();
//        client.start();
//
//        // Send example data
//        String text = "Hello, this is a text message!";
//        client.sendAudioVideoText("text", text.getBytes());
//
//        // For audio or video, you would read from a file or live stream
//        // Example: Send a mock audio/video data
//        byte[] mockData = new byte[1024];  // Example byte array
//        client.sendAudioVideoText("audio", mockData);  // Replace with actual audio/video data
//    }
//}
