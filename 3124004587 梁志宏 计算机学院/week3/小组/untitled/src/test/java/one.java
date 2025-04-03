import com.csmht.JDBC;

import java.sql.ResultSet;
import java.sql.SQLException;

public class one {
    public static void main(String[] args) throws SQLException {

       ResultSet rs = JDBC.find("user","id","student1");
       while (rs.next()) {
           System.out.println(rs.getString("mima"));
       }
    }
}
