import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Loader {

    private static double totalIncomeRUR;
    private static double totalConsumptionRUR;
    static ArrayList<String> consumptionList;

    public static void main(String[] args) {

        int numberOfColumns = 11;
        int incomeColumn = numberOfColumns - 2; //Номер столбца, где хранятся доходы
        int consumptionColumn = numberOfColumns - 1;  //Номер стобца, где хранятся расходы
        int typeConsuptionColumn = numberOfColumns - 5;  //Номер столбца, где хранится информация о типе расходов/доходов
        consumptionList = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get("Data/movementList.csv"));
            //Пропускаю 0 строку, тк она не содержит данных
            for (int i = 1; i < lines.size(); i++) {

                //Удаляю выражения в скобках, ковычки и лишние пробелы
                String[] fragments = lines.get(i).replaceAll("\"", "")
                        .replaceAll(" \\(.*?\\)", "")
                        .replaceAll("809216", "")
                        .replaceAll("\\s{3,}", ",")
                        .split(",", numberOfColumns);

                Double income = Double.parseDouble(fragments[incomeColumn]);
                Double consumption = Double.parseDouble(fragments[consumptionColumn].replaceAll("\\,", "."));
                totalIncomeRUR += income;
                totalConsumptionRUR += consumption;

                if (consumption > 0.00) {
                    consumptionList.add(consumption + " рублей - " + fragments[typeConsuptionColumn]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.printf("Общий приход: %.2f рублей\n" +
                "Общий расход: %.2f рублей\n ", totalIncomeRUR, totalConsumptionRUR);

        System.out.printf("\nСписок расходов:\n");
        for (String consuption : consumptionList) {
            System.out.println(consuption);
        }
    }
}
