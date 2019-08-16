import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.file.*;

public class Loader {

    public static void main(String[] args) throws IOException {
        System.out.println("Введите адрес папки, которую надо скопировать: ");
        BufferedReader line0 = new BufferedReader(new InputStreamReader(System.in));
        String folderFrom = line0.readLine();
        Path pathFolderFrom = Paths.get(folderFrom);
        if (Files.notExists(pathFolderFrom)) {
            System.out.println("Введите верный адрес папки ");
        } else if (!Files.isDirectory(pathFolderFrom)) {
            System.out.println("Введите адрес папки, а не файла");
        } else {
            System.out.println("Введите адрес папки куда копировать: ");
            BufferedReader line1 = new BufferedReader(new InputStreamReader(System.in));
            String folderTo = line1.readLine();
            Path pathFolderTo = Paths.get(folderTo);
            if (!Files.exists(pathFolderTo)) {
                Files.createDirectory(pathFolderTo);
            }

            File folder0 = new File(folderFrom);
            File folder1 = new File(folderTo);
            FileUtils.copyDirectory(folder0, folder1);

        }
    }
}
