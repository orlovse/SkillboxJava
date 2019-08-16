import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.FileVisitResult.CONTINUE;


public class Main {
    static long totalSize = 0;
    private static final long K = 1024;
    private static final long M = K * K;
    private static final long G = M * K;
    private static final long T = G * K;

    public static void main(String[] args) throws IOException {
        boolean exit = false;
        while (!exit) {
            System.out.println("Введите путь папки: ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String directory = br.readLine();
            Path directoryPath = Paths.get(directory);

            if (directory.equals("exit")) {
                break;
            }

            if (Files.notExists(directoryPath)) {
                System.out.println("Файл не существует");
            } else if (!Files.isDirectory(directoryPath)) {
                System.out.println(directoryPath + " - не папка.");
            } else {

                Files.walkFileTree(directoryPath, new Visitior());
                System.out.println("Общий размер - " + formatSize(totalSize));
            }
            totalSize = 0;
        }
    }

    private static class Visitior extends SimpleFileVisitor<Path> {

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            totalSize = totalSize + attrs.size();
            return CONTINUE;
        }
    }

    private static String formatSize(long size) {
        if (size < K) {
            return size + " b";
        } else if (size < M) {
            float newSize = ((float) size) / K;
            return newSize + " Kb";
        } else if (size < G) {
            float newSize = ((float) size) / M;
            return newSize + " Mb";
        } else if (size < T) {
            float newSize = ((float) size) / G;
            return newSize + " Gb";
        } else {
            float newSize = ((float) size) / T;
            return newSize + " Tb";
        }
    }
}
