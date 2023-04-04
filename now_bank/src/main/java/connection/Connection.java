package connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connection {

    private Connection connection;

    public Connection(){
        String driver = "org.postgresql.Driver";
        String user = "postgres";
        String password = "root";
        String url = "jdbc:postgresql://0.0.0.0:5432/postgres";

        try{
            Class.forName(driver);
            this.connection = (Connection) DriverManager.getConnection(url, user, password);
            System.out.println("Conexão realizada com sucesso!");
        } catch (ClassNotFoundException ex){
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            System.exit(1);
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            System.exit(1);        }
    }

    public Connection getConnection() {
        return connection;
    }
}
