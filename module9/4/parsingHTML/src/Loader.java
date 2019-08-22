import org.jsoup.Jsoup;
        import org.jsoup.nodes.Document;
        import org.jsoup.select.Elements;

        import java.io.File;
        import java.io.IOException;
        import java.net.URL;
        import java.nio.file.Files;
        import java.nio.file.Path;
        import java.nio.file.Paths;
        import java.nio.file.StandardCopyOption;

public class Loader {

    public static void main(String[] args) {
        String folderName = "/Users/orlov_s/Desktop/2/";
        try {
            Document doc = Jsoup.connect("https://lenta.ru/").get();
            Elements photoLink = doc.select("img[src]");
            photoLink
                    .forEach(element -> {
                        try {
                            download(element.attr("src"), folderName);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void download(String sourceURL, String folderName) throws IOException {
        if (Files.notExists(Paths.get(folderName))) {
            Files.createDirectories(Paths.get(folderName));
        }
        URL url = new URL(sourceURL);
        String fileName = sourceURL.substring(sourceURL.lastIndexOf('/') + 1);
        Path folderPath = new File(folderName + File.separator + fileName).toPath();
        if (Files.notExists(Paths.get(folderName))) {

        }
        try {
            Files.copy(url.openStream(), folderPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
