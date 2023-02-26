package assingment.assingment.src.Database;


import java.sql.*;

public class DbConnection {
    // creating instance variables/objects of database related classes
    public Connection connection;
    Statement statement;
    ResultSet resultSet;
    int val;
    public DbConnection(){
        try {
            String username = "root";
            String password = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            // establishing connection in the connection object
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/programming",
                    username,
                    password);
            if (connection !=null){
            }else {
                System.out.println("Database Error");
            }
            // creating the statement object
            statement = connection.createStatement();

        }catch (Exception e){
            //handling exception
            System.out.println(e);
            e.printStackTrace();
        }
    }



    public int manipulate(PreparedStatement st) {
        try {
            val = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return val;
    }

    public ResultSet retrieve(PreparedStatement st){
        try {
            resultSet=st.executeQuery();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }


}
