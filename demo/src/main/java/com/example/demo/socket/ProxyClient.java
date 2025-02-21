//import java.io.IOException;
//import java.io.InputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.StandardOpenOption;
//import java.util.concurrent.TimeUnit;
//
//public class ElevenLabsExample {
//    private static final String ELEVENLABS_API_KEY = System.getenv("ELEVENLABS_API_KEY");
//    private static final String ARTICLE_HTML = String.join("\n",
//            "<html>",
//            "<body>",
//            "<div>",
//            "    <p>",
//            "    ElevenL",
//            "    </p>",
//            "    <p>",
//            "    Our re",
//            "    </p>",
//            "    <p>",
//            "    Ou",
//            "    </p>",
//            "    <p>",
//            "    We develop our ",
//            "    </p>",
//            "</div>",
//            "</body>",
//            "</html>");
//    private static final int MAX_POLLING_RETRIES = 60;
//
//    public static void main(String[] args) throws IOException, InterruptedException {
//        String projectId = createPodcast();
//        waitForPodcastCreation(projectId);
//        triggerPodcastAudioGeneration(projectId);
//        waitForPodcastAudioGeneration(projectId);
//        downloadPodcastAudio(projectId);
//    }
//
//    private static String createPodcast() throws IOException {
//        String url = "https://api.elevenlabs.io/v1/projects/podcast/";
//        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
//        connection.setRequestMethod("POST");
//        connection.setRequestProperty("xi-api-key", ELEVENLABS_API_KEY);
//        connection.setDoOutput(true);
//
//        String requestBody = "{" +
//                "\"host_voice_id\": \"iP95p4xoKVk53GoZ742B\"," +
//                "\"guest_voice_id\": \"cgSgspJ2msm6clMCkdW9\"," +
//                "\"default_model_id\": \"eleven_multilingual_v2\"," +
//                "\"quality_preset\": \"standard\"" +
//                "}";
//        byte[] requestBytes = requestBody.getBytes();
//        connection.setRequestProperty("Content-Length", String.valueOf(requestBytes.length));
//        connection.getOutputStream().write(requestBytes);
//
//        int responseCode = connection.getResponseCode();
//        if (responseCode != 200) {
//            throw new IOException("Error creating podcast, status: " + responseCode + ", content: " + readResponseBody(connection));
//        }
//
//        String projectId = parseProjectId(readResponseBody(connection));
//        System.out.println("CREATING PODCAST");
//        System.out.println(readResponseBody(connection));
//        return projectId;
//    }
//
//    private static void waitForPodcastCreation(String projectId) throws IOException, InterruptedException {
//        int retry = 0;
//        while (retry < MAX_POLLING_RETRIES) {
//            String url = "https://api.elevenlabs.io/v1/projects/" + projectId;
//            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
//            connection.setRequestMethod("GET");
//            connection.setRequestProperty("xi-api-key", ELEVENLABS_API_KEY);
//
//            int responseCode = connection.getResponseCode();
//            if (responseCode != 200) {
//                throw new IOException("Error checking podcast status, status: " + responseCode + ", content: " + readResponseBody(connection));
//            }
//
//            String state = parseState(readResponseBody(connection));
//            if (state.equals("default")) {
//                break;
//            }
//
//            System.out.println("WAITING FOR PODCAST TO BE CREATED " + retry + " progress=" + parseCreationProgress(readResponseBody(connection)) + "%");
//            retry++;
//            TimeUnit.SECONDS.sleep(10);
//        }
//        if (retry >= MAX_POLLING_RETRIES) {
//            throw new TimeoutException("Podcast is still not created");
//        }
//    }
//
//    private static void triggerPodcastAudioGeneration(String projectId) throws IOException {
//        String url = "https://api.elevenlabs.io/v1/projects/" + projectId + "/convert";
//        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
//        connection.setRequestMethod("POST");
//        connection.setRequestProperty("xi-api-key", ELEVENLABS_API_KEY);
//
//        int responseCode = connection.getResponseCode();
//        if (responseCode != 200) {
//            throw new IOException("Error triggering podcast audio generation, status: " + responseCode + ", content: " + readResponseBody(connection));
//        }
//
//        System.out.println("CREATED PODCAST, TRIGGERING PODCAST AUDIO GENERATION");
//    }
//
//    private static void waitForPodcastAudioGeneration(String projectId) throws IOException, InterruptedException {
//        int retry = 0;
//        while (retry < MAX_POLLING_RETRIES) {
//            String url = "https://api.elevenlabs.io/v1/projects/" + projectId;
//            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
//            connection.setRequestMethod("GET");
//            connection.setRequestProperty("xi-api-key", ELEVENLABS_API_KEY);
//
//            int responseCode = connection.getResponseCode();
//            if (responseCode != 200) {
//                throw new IOException("Error checking podcast audio generation status, status: " + responseCode + ", content: " + readResponseBody(connection));
//            }
//
//            String state = parseState(readResponseBody(connection));
//            if (state.equals("default")) {
//                break;
//            }
//
//            System.out.println("WAITING FOR PODCAST AUDIO TO BE GENERATED " + retry + "");
//            retry++;
//            TimeUnit.SECONDS.sleep(10);
//        }
//        if (retry >= MAX_POLLING_RETRIES) {
//            throw new TimeoutException("Podcast audio is still not generated");
//        }
//    }
//
//    private static void downloadPodcastAudio(String projectId) throws IOException {
//        String url = "https://api.elevenlabs.io/v1/projects/" + projectId + "/snapshots";
//        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
//        connection.setRequestMethod("GET");
//        connection.setRequestProperty("xi-api-key", ELEVENLABS_API_KEY);
//
//        int responseCode = connection.getResponseCode();
//        if (responseCode != 200) {
//            throw new IOException("Error getting podcast snapshots, status: " + responseCode + ", content: " + readResponseBody(connection));
//        }
//
//        String snapshotId = parseSnapshotId(readResponseBody(connection));
//
//        url = "https://api.elevenlabs.io/v1/projects/" + projectId + "/snapshots/" + snapshotId + "/stream";
//        connection = (HttpURLConnection) new URL(url).openConnection();
//        connection.setRequestMethod("POST");
//        connection.setRequestProperty("xi-api-key", ELEVENLABS_API_KEY);
//
//        responseCode = connection.getResponseCode();
//        if (responseCode != 200) {
//            throw new IOException("Error downloading podcast audio, status: " + responseCode + ", content: " + readResponseBody(connection));
//        }
//
//        Path path = Paths.get("podcast.mp3");
//        try (InputStream inputStream = connection.getInputStream()) {
//            Files.write(path, inputStream.readAllBytes(), StandardOpenOption.CREATE);
//        }
//
//        System.out.println("PODCAST AUDIO DOWNLOADED " + path);
//    }
//
//    private static String parseProjectId(String responseBody) {
//        // Parse the project ID from the response body
//        return ""; // Implement the parsing logic
//    }
//
//    private static String parseState(String responseBody) {
//        // Parse the state from the response body
//        return ""; // Implement the parsing logic
//    }
//
//    private static double parseCreationProgress(String responseBody) {
//        // Parse the creation progress from the response body
//        return 0.0; // Implement the parsing logic
//    }
//
//    private static String parseSnapshotId(String responseBody) {
//        // Parse the snapshot ID from the response body
//        return ""; // Implement the parsing logic
//    }
//
//    private static String readResponseBody(HttpURLConnection connection) throws IOException {
//        try (InputStream inputStream = connection.getInputStream()) {
//            return new String(inputStream.readAllBytes());
//        }
//    }
//
//    private static class TimeoutException extends Exception {
//        TimeoutException(String message) {
//            super(message);
//        }
//    }
//}