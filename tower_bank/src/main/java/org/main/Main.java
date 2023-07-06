package org.main;

import Controller.Controller;
import connection.Con;
import org.neo4j.driver.Driver;


/**
 *
 * @author crispim
 */
public class Main {
    public static void main(String[] args) throws Exception {

        Con con = new Con();
        Driver connectionGraph = con.getConnectionGraph();
        Controller controller = null;
        controller.menu(connectionGraph);
        con.closeConnectionGraph();
    }
}