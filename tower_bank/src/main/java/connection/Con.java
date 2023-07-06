package connection;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Con {

    private Connection connection;
    private Driver driver;

    public Con() {
        //relacional
        String user = "postgres";
        String password = "root";
        String url = "jdbc:postgresql://0.0.0.0:5432/postgres";

        //graph
        String userGraph = "neo4j";
        String passwordGraph = "root";
        String urlGraph = "bolt://localhost:7687";



        try {
            //this.connection = DriverManager.getConnection(url, user, password);
            this.driver = GraphDatabase.driver(urlGraph, AuthTokens.basic(userGraph, passwordGraph));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    public Connection getConnection() {
        return connection;
    }

    public Driver getConnectionGraph() {
        return driver;
    }

    public void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Con.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            System.exit(1);
        }
    }

    public void closeConnectionGraph(){
        driver.close();
    }
}
