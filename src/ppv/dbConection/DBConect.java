
package ppv.dbConection;
/**
 *
 * @author it pro
 */   
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DBConect {
      
    public Connection GetConnection()
    {
        Connection conexion=null;
      
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            String servidor = "jdbc:mysql://localhost:3305/offpostest";
            //for romeo machine
            //String usuarioDB="user";
            //for develop machine
            String usuarioDB="root";
            String passwordDB="admin123";
            conexion= DriverManager.getConnection(servidor,usuarioDB,passwordDB);
        }
        catch(ClassNotFoundException ex)
        {
            JOptionPane.showMessageDialog(null, ex, "Error1 en la Conexión con la BD "+ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            conexion=null;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, ex, "Error2 en la Conexión con la BD "+ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            conexion=null;
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex, "Error3 en la Conexión con la BD "+ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            conexion=null;
            ex.printStackTrace();
        }
        finally
        {
            return conexion;
        }
    }
}
