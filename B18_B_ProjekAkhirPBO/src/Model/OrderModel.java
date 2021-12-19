package Model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderModel extends BaseModel{
    public OrderModel() throws SQLException {
        super();
    }
    public int addOrder(int idOrder, int idEmployee, String tanggal, String customer, int idProduct, int jumlah) throws SQLException {
        String query = "INSERT INTO orderdetail" + " (idOrder, idemployee, customer, idproduct, jumlah, tanggal) " + "VALUES (?, ?, ? , ?, ?, ?::date)";

        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, idOrder);
        preparedStatement.setInt(2, idEmployee);
        preparedStatement.setString(3, customer);
        preparedStatement.setInt(4, idProduct);
        preparedStatement.setInt(5, jumlah);
        preparedStatement.setDate(6, Date.valueOf(tanggal));

        return preparedStatement.executeUpdate();
    }

    public ResultSet getOrder(int id) throws SQLException {
        String query = "SELECT * from detailorder where idorder=?";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, id);
        return preparedStatement.executeQuery();
    }
    public ResultSet getRiwayat() throws SQLException {
        String query = "SELECT * from ordertotal";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        return preparedStatement.executeQuery();
    }

}
