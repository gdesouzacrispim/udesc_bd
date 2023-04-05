package core.dao;

import entity.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public static Cliente findById(Integer id, Connection connection) throws SQLException {
        PreparedStatement statement;
        statement = connection.prepareStatement("SELECT * FROM trabalho.cliente WHERE id = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        try {
            if (resultSet.next()) {
                Integer idCliente = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String telefone = resultSet.getString("telefone");
                String email = resultSet.getString("email");
                String cpf = resultSet.getString("cpf");
                String endereco = resultSet.getString("endereco");

                return new Cliente(idCliente, nome, telefone, email, cpf, endereco);
            } else {
                return null;
            }
        } finally {
            statement.close();
        }
    }
}
