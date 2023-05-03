package org.main;

import Controller.Controller;
import connection.Con;

import java.sql.Connection;

/**
 *
 * @author crispim
 */
public class Main {
    public static void main(String[] args) throws Exception {

        Con con = new Con();
        Connection connection = con.getConnection();
        Controller controller = null;
        controller.menu(connection);
        con.closeConnection();
    }
}