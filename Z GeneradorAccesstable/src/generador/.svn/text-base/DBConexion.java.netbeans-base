package generador;
import java.sql.Connection;
import java.sql.DriverManager;
public class DBConexion  {
    private static Connection con = null;
    private DBConexion(){
    }
    public static Connection getDBConexion(){
        try{
            if ( con == null ) {
                //crear la conexion
                Class.forName ("org.postgresql.Driver");
                con = DriverManager.getConnection(Constante.conexion, Constante.usuario , "postgres");
               // con.setAutoCommit(false);
            }
            return con;
        }
        catch ( Exception e ) {
            System.out.println(e);
            return null;
        }
    }
}
