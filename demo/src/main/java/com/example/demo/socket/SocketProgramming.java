//```java
//import okhttp3.MediaType;
//import okhttp3.MultipartBody;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.RequestBody;
//import okhttp3.Response;
//
//import java.io.FileOutputStream;
//import java.io.IOException;
//
//public class Main {
//    private static final String ELEVENLABS_API_KEY = System.getenv("ELEVENLABS_API_KEY");
//    private static final String[] ARTICLE_HTML = {
//            "<html>",
//            "<body>",
//            "<div>",
//            "<p>",
//            "ElevenLabs is an AI audio research and deployment company. ",
//            "Our mission is to make content universally accessible in any language and in any voice.",
//            "</p>",
//            "<p>",
//            "Our research team develops AI audio models that generate realistic, versatile ",
//            "and contextually-aware speech, voices, and sound effects across 32 languages. ",
//            "Our product team tailors these models to the needs of everyday users, prosumers, and businesses.",
//            "</p>",
//            "<p>",
//            "Our technology is used to voice audiobooks and news articles, ",
//            "animate video game characters, help in film pre-production, ",
//            "localize media in entertainment, create dynamic audio content for social ",
//            "media and advertising, and train medical professionals.",
//            "It has also given back voices to those who have lost them and helped ",
//            "individuals with accessibility needs in their daily lives.",
//            "</p>",
//            "<p>",
//            "We develop our tools mindful of their impact. AI voices offer a preview ",
//            "into the future of digital interaction and making them safe is our priority.",
//            "Our goal is to ensure that our products are developed, deployed and ",
//            "used safely, while continuing to drive positive and creative applications.",
//            "</p>",
//            "</div>",
//            "</body>",
//            "</html>"
//    };
//    private static final int MAX_POLLING_RETRIES = 60;
//
//    public static void main(String[] args) {
//        OkHttpClient client = new OkHttpClient();
//
//        RequestBody requestBody = new MultipartBody.Builder()
//                .setType(MultipartBody.FORM)
//                .addFormDataPart("from_document", "article.html", RequestBody.create(MediaType.get("text/html"), String.join("\n", ARTICLE_HTML)))
//                .build();
//
//        Request request = new Request.Builder()
//                .url("https://api.elevenlabs.io/v1/projects/podcast/")
//                .header("xi-api-key", ELEVENLABS_API_KEY)
//                .post(requestBody)
//                .build();
//
//        try (Response response = client.newCall(request).execute()) {
//            if (!response.isSuccessful()) {
//                System.out.println("tus: " + response.code() + ", content: " + response.body().string());
//            }
//
//            String responseBody = response.body().string();
//            System.out.println("CAST");
//            System.out.println(responseBody);
//
//            String projectId = new JSONObject(responseBody).getString("project_id");
//
//            int retry = 0;
//            while (retry < MAX_POLLING_RETRIES) {
//                request = new Request.Builder()
//                        .url("https://api.elevenlabs.io/v1/projects/" + projectId)
//                        .header("xi-api-key", ELEVENLABS_API_KEY)
//                        .build();
//
//                response = client.newCall(request).execute();
//                if (!response.isSuccessful()) {
//                    System.out.println("tus: " + response.code() + ", content: " + response.body().string());
//                }
//
//                responseBody = response.body().string();
//                JSONObject jsonObject = new JSONObject(responseBody);
//                String state = jsonObject.getString("state");
//                if (state.equals("default")) {
//                    break;
//                }
//
//                System.out.println("WAITING FOR PODCAST TO BE CREATED retry=" + retry + " progress=" + jsonObject.getJSONObject("creation_meta").getDouble("creation_progress") * 100 + "%");
//                retry++;
//                Thread.sleep(10000);
//
//
//
//                Search Tutorials
//                StatCounter - Free Web Tracker and Counter
//
//
//                Other Online tools
//                Online JWT Generator
//                Online JWT Decoder
//                Online Bcrypt Generator and Validator
//                Online tool to generate and check MD5 hashed passwords
//                Online Hex Encoder and Decoder Tool
//                Online HTML Encoder Tool
//                Online HTML Decoder Tool
//                Online RSA Encryption, Decryption And Key Generator Tool
//                Online AES Encryption and Decryption Tool
//                Online PGP Encryption, Decryption And Key Generator Tool
//                Online Triple DES Encryption and Decryption Tool
//                Online HMAC Generator Tool
//                Online tool to generate and decrypt/check Jasypt encrypted passwords
//                Online Grok Pattern Generator Tool
//                Online JSONPath Evaluator Tool
//                Online Tool To Convert XML To JSON And JSON To XML
//                Java Decompiler Online
//                Online JSON to Java POJO Class Converter
//                Online Text(String) Size Calculator Tool (In Bytes)
//                JSON to NDJSON Online Converter Tool
//                Cron Expression Generator Tool
//                JSON to YAML Converter Tool
//                YAML to JSON Converter Tool
//                YAML to POJO Converter Tool
//                XML to POJO Converter Tool
//                Online Regex Generator Tool
//                Online Regex Tester and Debugger Tool
//                Online Bash Shell Scripts to Windows Batch Files Converter Tool
//                Online JSON to Typescript Converter Tool
//                Online tool to convert Properties File to YAML format
//                Online tool to convert Kubernetes YAML to Terraform HCL format
//                Online tool to convert SQL to Mongo format
//                Online tool to convert JSON to Kotlin format
//                Online tool to convert JavaScript to Python format
//                Online tool to convert Python to JavaScript format
//                Online tool to convert Python to C++ format
//                Online tool to convert Java to Python format
//                Online tool to convert Javascript to Typescript format
//                Online tool to convert Java to Javascript format
//                Online tool to convert Java to Golang format
//                Online tool to convert Kotlin to Java format
//                Online tool to convert Java to Kotlin format
//                Online tool to convert Java to C# format
//                Online tool to find IP address of a website
//                Find Character or Line Position Online
//                Online UUID Version 1 (v1) Generator
//                Online UUID Version 3 (v3) Generator
//                Online UUID Version 4 (v4) Generator
//                Online UUID Version 5 (v5) Generator
//                Online UUID Version Validator
//                Online UUID Version Checker
//                Online Tool to Convert Java to C++
//                Online Tool to Convert C Code to Python
//                Online Tool to Convert C Code to C++
//                        Online Tool to Convert Python Code to R
//                Online Tool to Convert JavaScript Code to JQuery
//                Online Tool to Convert Scala Code to Java
//                Online Tool to Convert Java Code to Scala
//                Online Tool to Convert C# Code to Java
//                Online Tool to Convert PHP Code to Python
//                Online Tool to Convert C# Code to Python
//                Online Tool to Convert C++ Code to Java
//                Online Tool to Convert Python Code to Java
//                Online Tool to Convert Python Code to CSharp
//                Online Tool to check SQL Syntax
//                Online Tool to test Python Code
//                Online Tool to format React Code
//                Online Tool to format Rust Code
//                Online Tool to format Golang Code
//                Online Tool to format Pearl Code
//                Online Tool to format Kotlin Code
//                Online Tool to optimize SQL Query
//                Online Tool to convert cURL to Java Client
//                Online Tool to convert cURL to Python Client
//                Online Tool to convert cURL to Spring Boot Client
//                Online Tool to convert cURL to ASP.NET Client
//                Online Tool to convert cURL to Angular Client
//                Online Tool to convert cURL to React Client
//                Online Tool to convert JSON request to gRPC Protobuf format
//                Online Tool to convert gRPC Protobuf request to JSON format
//
//
//
//
//
//
//✖This site uses cookies to deliver our services and to show you relevant ads. By continuing to visit this site you agree to our use of cookies. Learn more
