import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import feign.Headers;
import feign.form.FormProperty;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@FeignClient(name = "elevenLabsClient", url = "https://api.elevenlabs.io/v1")
public interface ElevenLabsClient {

                                  @PostMapping(value = "/projects/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
                                  @Headers({
    "xi-api-key: sk_214be2913e5edf8ca1f3efc962bd5c338c48519f55a9d155",
    "Content-Type: multipart/form-data"
})
String addProject(
       @RequestPart("name") String name,
@RequestPart("default_title_voice_id") String defaultTitleVoiceId,
@RequestPart("default_paragraph_voice_id") String defaultParagraphVoiceId,
@RequestPart("default_model_id") String defaultModelId,
@RequestPart("genres") String genres,
@RequestPart("from_document") MultipartFile fromDocument
);

private static List<String> createArticleHtml(String customText) {
    List<String> articleHtml = new ArrayList<>();
articleHtml.add("<html>");
articleHtml.add("<body>");
articleHtml.add("<div>");
articleHtml.add("<p>ElevenLabs is an AI audio research and deployment company. Our mission is to make content universally accessible in any language and in any voice.</p>");
articleHtml.add("<p>Our research team develops AI audio models that generate realistic, versatile and contextually-aware speech, voices, and sound effects across 32 languages. Our product team tailors these models to the needs of everyday users, prosumers, and businesses.</p>");
articleHtml.add("<p>Our technology is used to voice audiobooks and news articles, animate video game characters, help in film pre-production, localize media in entertainment, create dynamic audio content for social media and advertising, and train medical professionals. It has also given back voices to those who have lost them and helped individuals with accessibility needs in their daily lives.</p>");
if (customText != null && !customText.isEmpty()) {
    articleHtml.add("<p>" + customText + "</p>");
}
articleHtml.add("<p>We develop our tools mindful of their impact. AI voices offer a preview into the future of digital interaction and making them safe is our priority. Our goal is to ensure that our products are developed, deployed and used safely, while continuing to drive positive and creative applications.</p>");
articleHtml.add("</div>");
articleHtml.add("</body>");
articleHtml.add("</html>");
return articleHtml;
}

private static final List<String> ARTICLE_HTML = createArticleHtml("");

static MultipartFile convertHtmlToMultipartFile(List<String> htmlContent) throws IOException {
File tempFile = File.createTempFile("article", ".html");
try (FileOutputStream fos = new FileOutputStream(tempFile)) {
for (String line : htmlContent) {
    fos.write(line.getBytes());
fos.write(System.lineSeparator().getBytes());
}
}
return new MockMultipartFile(
    tempFile.getName(),
    tempFile.getName(),
    "text/html",
    Files.readAllBytes(tempFile.toPath())
);
}
}
