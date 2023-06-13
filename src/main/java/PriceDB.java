import java.sql.*;
import java.util.ArrayList;

public class PriceDB {

    private final Connection connection;

    public PriceDB() {

        String driverClassname = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/Coffee_Shop";
       // String username = "root";
       // String password = "YU123.";

        try {
            connection = DriverManager.getConnection(url, "root", "YU123.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public double findPrice(SizeType size, DrinkType type) {
        double value = 0.0;
        try {

            String query = "SELECT price FROM Coffee_shop WHERE size = ? AND drink_type = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, size.toString());
            statement.setString(2, type.toString());
            ResultSet resultSet = statement.executeQuery();


            if (resultSet.next()) {
                value = resultSet.getDouble("price");
            }


            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return value;
    }
}