/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dbmanager;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
/**
 *
 * @author Timbu
 */
public class DbManager implements Serializable {
  private Connection connection = null;
  private Statement statement = null;
  private PreparedStatement preparedStatement = null;
  private ResultSet resultSet = null;

   

   DbManager(String dburl) throws Exception {
    try {
      // This will load the MySQL driver, each DB has its own driver
      Class.forName("com.mysql.jdbc.Driver");
      // Setup the connection with the DB
      connection = DriverManager
          .getConnection("jdbc:mysql://localhost/secondoprogettoweb?user=root&password=password");

    } catch (Exception e) {
      throw e;
    } 

  }
   public void test() throws SQLException{
       PreparedStatement stm = connection.prepareStatement("SELECT * FROM secondoprogettoweb.comments");
       ResultSet rs = stm.executeQuery();
       while(rs.next()){
           String id = rs.getString("id");
           System.out.println(id);
       }
       stm.close();
   }

  // You need to close the resultSet
   void close() {
    try {
      if (resultSet != null) {
        resultSet.close();
      }

      if (statement != null) {
        statement.close();
      }

      if (connection != null) {
        connection.close();
        DriverManager.getConnection("jdbc:mysql:;shutdown=true");
      }
    } catch (Exception e) {

    }
  }

}
