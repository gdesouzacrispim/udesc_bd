package core.dao;

import entity.Agencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AgenciaDAO {

    public static void create(Connection connection, Agencia agencia) throws SQLException {
        PreparedStatement statement;
        statement = connection.prepareStatement("INSERT INTO trabalho.agencia(nome, endereco, cnpj, cidade) VALUES (?, ?, ?, ?)");
        statement.setString(1, agencia.getNome());
        statement.setString(2, agencia.getEndereco());
        statement.setString(3, agencia.getCnpj());
        statement.setInt(4, agencia.getCodigoCidade());
        statement.execute();
        statement.close();
    }

    public static Agencia findById(Connection connection, Integer id) throws SQLException {
        PreparedStatement statement;
        statement = connection.prepareStatement("SELECT * FROM trabalho.agencia WHERE id = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        try {
            if (resultSet.next()) {
                Integer idConta = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String endereco = resultSet.getString("endereco");
                String cnpj = resultSet.getString("cnpj");
                Integer cidade = resultSet.getInt("cidade");

                return new Agencia(idConta, nome, endereco, cnpj, cidade);
            } else {
                return null;
            }
        } finally {
            statement.close();
        }
    }
}
