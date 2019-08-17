import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.file.*;

public class Loader {

    public static void main(String[] args) throws IOException {
        boolean exit = false;
        while (!exit) {
            System.out.println("Введите адрес папки, которую надо скопировать: ");
            BufferedReader line = new BufferedReader(new InputStreamReader(System.in));
            String folderFrom = line.readLine();
            Path pathFolderFrom = Paths.get(folderFrom);

            if (folderFrom.equals("exit")) {
                exit = true;
            }

            if (Files.notExists(pathFolderFrom)) {
                System.out.println("Неверный адрес папки ");
            } else if (!Files.isDirectory(pathFolderFrom)) {
                System.out.println("Введите адрес папки, а не файла");
            } else {
                System.out.println("Введите адрес папки куда копировать: ");
                String folderTo = line.readLine();
                Path pathFolderTo = Paths.get(folderTo);
                if (!Files.exists(pathFolderTo)) {
                    Files.createDirectory(pathFolderTo);
                }

                File folderFromCopy = new File(folderFrom);
                File folderToCopy = new File(folderTo);
                FileUtils.copyDirectory(folderFromCopy, folderToCopy);

                System.out.println("Папка удачно скопирована.");
                System.out.println();
            }
        }
    }
}
