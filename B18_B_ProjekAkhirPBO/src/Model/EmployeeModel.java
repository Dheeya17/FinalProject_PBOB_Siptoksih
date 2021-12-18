package Model;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeModel extends BaseModel{
    public EmployeeModel() throws SQLException{
        super();
    }

    public ResultSet getEmployee() throws SQLException{
        String query = "SELECT * from employee";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        return preparedStatement.executeQuery();
    }


}
