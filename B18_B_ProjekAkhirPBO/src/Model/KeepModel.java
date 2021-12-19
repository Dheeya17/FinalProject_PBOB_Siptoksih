package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class KeepModel extends BaseModel{
    Scanner sc = new Scanner(System.in);
    public KeepModel() throws SQLException{
        super();
    }
    public int addKeep(int idKeep, int idProduct, int jumlah) throws SQLException {
        String query = "INSERT INTO keep" + " (idkeep, idproduct, jumlah) " + "VALUES (?, ?, ?)";

        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, idKeep);
        preparedStatement.setInt(2, idProduct);
        preparedStatement.setInt(3, jumlah);

        return preparedStatement.executeUpdate();
    }

    public ResultSet getKeep() throws SQLException {
        String query = "SELECT * from detailkeep";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        return preparedStatement.executeQuery();
    }
    public void delKeep() throws SQLException{
        System.out.print("Nomor barang yang akan dihapus: ");
        int id = sc.nextInt(); //Skip
        String query = "DELETE from keep where idKeep=?";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, id);

        preparedStatement.executeUpdate();

    }
    public ResultSet getTotal() throws SQLException {
        String query = "SELECT * from detailkeep";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        return preparedStatement.executeQuery();
    }




}
