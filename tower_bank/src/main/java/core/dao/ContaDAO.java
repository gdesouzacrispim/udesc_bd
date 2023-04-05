package core.dao;

import entity.Conta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ContaDAO {

    public static void create(Connection connection, Conta conta) throws SQLException {
        PreparedStatement statement;
        statement = connection.prepareStatement("INSERT INTO trabalho.conta (id_cliente, id_agencia, tipo, saldo, numero, senha)" +
                "VALUES (?, ?, ?, ?, ?, ?)");
        statement.setInt(1, conta.getCliente());
        statement.setInt(2, conta.getAgencia());
        statement.setInt(3, conta.getTipo());
        statement.setDouble(4, conta.getSaldo());
        statement.setDouble(5, conta.getNumero());
        statement.setInt(6, conta.getSenha());

        statement.execute();
        statement.close();
    }

    public static void delete(Connection connection, Integer numero, Integer senha) throws SQLException {
        PreparedStatement statement;
        statement = connection.prepareStatement("DELETE FROM trabalho.conta WHERE numero = ? and senha = ?");
        statement.setInt(1, numero);
        statement.setInt(2, senha);
        statement.execute();
        statement.close();
    }
}
