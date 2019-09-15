import java.sql.*;

public class Loader {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "root";
        String pass = "rootroot";

        try {

            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT name AS course_name, " +
                            "students_count / (SELECT Month(max(subscription_date)) - Month(min(subscription_date)) FROM Subscriptions) " +
                            "AS average_purchases_per_month FROM Courses"
            );

            while (resultSet.next()) {
                System.out.println(
                        resultSet.getString("course_name") + " - " +
                                resultSet.getDouble("average_purchases_per_month")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        resultSet.close();
        statement.close();
        connection.close();
    }
}
