package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ProductModel extends BaseModel{
    Scanner sc = new Scanner(System.in);
    public ProductModel() throws SQLException {
        super();
    }

    public int addProduct(String name, String category, int price) throws SQLException {
        String query = "INSERT INTO product" + " (namabarang, kategori, harga) " + "VALUES (?, ?, ?)";

        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, category);
        preparedStatement.setInt(3, price);

        return preparedStatement.executeUpdate();

    }

    public int upProduct(String name, String category, int price, int id) throws SQLException{

        String query = "UPDATE product " +
                "SET namabarang = ?, kategori=?, harga=? where idProduct=?";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, category);
        preparedStatement.setInt(3, price);
        preparedStatement.setInt(4, id);

        return preparedStatement.executeUpdate();

    }

    public ResultSet getProduct() throws SQLException {
        String query = "SELECT * from product";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        return preparedStatement.executeQuery();
    }
    public void delProduct() throws SQLException{
        System.out.print("Nomor produk yang akan dihapus: ");
        int id = sc.nextInt(); //Skip
        String query = "DELETE from product where idProduct=?";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, id);

        preparedStatement.executeUpdate();

    }


}
