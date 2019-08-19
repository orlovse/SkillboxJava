import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class Loader {
    final static int NUMBER_COLUMNS = 11;
    final static int INDEX_INCOME = NUMBER_COLUMNS - 2;
    final static int INDEX_EXPENSE = NUMBER_COLUMNS - 1;
    final static int INDEX_TYPE_INCOME_EXPENSE = NUMBER_COLUMNS - 5;

    public static void main(String[] args) {
        double totalIncomeRUR = 0;
        double totalExpenseRUR = 0;
        ArrayList<String> incomeList = new ArrayList<>();
        ArrayList<String> expenseList = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(Paths.get("Data/movementList.csv"));
            //Пропускаю 0 строку, тк она не содержит данных
            for (int i = 1; i < lines.size(); i++) {

                //Удаляю выражения в скобках, ковычки и лишние пробелы
                String[] fragments = lines.get(i).replaceAll("\"", "")
                        .replaceAll(" \\(.*?\\)", "")
                        .replaceAll("809216", "")
                        .replaceAll("\\s{3,}", ",")
                        .split(",", NUMBER_COLUMNS);

                double income = Double.parseDouble(fragments[INDEX_INCOME]);
                double expense = Double.parseDouble(fragments[INDEX_EXPENSE].replaceAll("\\,", "."));
                totalIncomeRUR += income;
                totalExpenseRUR += expense;

                if (expense > 0.00) {
                    expenseList.add(fragments[INDEX_TYPE_INCOME_EXPENSE] + " : " + expense + " рублей");
                }

                if (income > 0.00) {
                    incomeList.add(fragments[INDEX_TYPE_INCOME_EXPENSE] + " : " + income + " рублей");
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.printf("Общий приход: %.2f рублей\n" +
                "Общий расход: %.2f рублей\n ", totalIncomeRUR, totalExpenseRUR);

        System.out.printf("\nПриходы:\n");
        for (String income : incomeList) {
            System.out.println(income);
        }

        System.out.printf("\nРасходы:\n");
        for (String expense : expenseList) {
            System.out.println(expense);
        }
    }
}
