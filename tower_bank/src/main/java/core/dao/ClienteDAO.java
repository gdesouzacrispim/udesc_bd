package core.dao;

import entity.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClienteDAO {

    public static void create(Cliente cliente, Connection connection) throws SQLException {

        PreparedStatement statement;
        statement = connection.prepareStatement("INSERT INTO trabalho.cliente (nome, telefone, email, cpf, endereco) VALUES" +
                " (?, ?, ?, ?, ?)");
        statement.setString(1, cliente.getNome());
        statement.setString(2, cliente.getTelefone());
        statement.setString(3, cliente.getEmail());
        statement.setString(4, cliente.getCpf());
        statement.setString(5, cliente.getEndereco());
        statement.execute();
        statement.close();
    }
}
