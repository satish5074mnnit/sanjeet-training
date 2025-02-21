//import com.google.gson.Gson;
//import com.google.gson.JsonObject;
//import org.java_websocket.server.WebSocketServer;
//import org.java_websocket.WebSocket;
//import org.java_websocket.handshake.ClientHandshake;
//
//import java.net.InetSocketAddress;
//import java.util.concurrent.CompletableFuture;
//
//public class WebSocketProxyServer extends WebSocketServer {
//
//    private static final String HOST = "us-central1-aiplatform.googleapis.com";
//    private static final String SERVICE_URL = "wss://" + HOST + "/ws/google.cloud.aiplatform.v1beta1.LlmBidiService/BidiGenerateContent";
//    private static final Gson gson = new Gson();
//    private static boolean DEBUG = false;
//
//    @Override
//    public void onOpen(WebSocket conn, ClientHandshake handshake) {
//        System.out.println("New connection...");
//        conn.setAttachment(new CompletableFuture<>());
//    }
//
//    @Override
//    public void onMessage(WebSocket conn, String message) {
//        JsonObject authData = gson.fromJson(message, JsonObject.class);
//        if (authData.has("bearer_token")) {
//            String bearerToken = authData.get("bearer_token").getAsString();
//            createProxy(conn, bearerToken);
//        } else {
//            System.out.println("Error: Bearer token not found in the first message.");
//            conn.close(1008, "Bearer token missing");
//        }
//    }
//
//    private void createProxy(WebSocket clientWebSocket, String bearerToken) {
//        // Implement the logic to connect to the server and forward messages
//        // This is a placeholder for the actual WebSocket connection to the server
//        // You would need a library that supports WebSocket client connections in Java
//    }
//
//    @Override
//    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
//        // Handle closing of the connection
//    }
//
//    @Override
//    public void onError(WebSocket conn, Exception ex) {
//        System.out.println("Error: " + ex.getMessage());
//    }
//
//    public static void main(String[] args) {
//        WebSocketProxyServer server = new WebSocketProxyServer();
//        server.start(new InetSocketAddress("localhost", 8080));
//        System.out.println("Running websocket server localhost:8080...");
//    }
//}
//
