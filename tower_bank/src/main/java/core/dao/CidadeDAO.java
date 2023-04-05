package core.dao;

import entity.Cidade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CidadeDAO {

     public static void create(Connection connection, Cidade cidade) throws SQLException {
        PreparedStatement statement;
        statement = connection.prepareStatement("INSERT INTO trabalho.cidade(nome, uf) VALUES (?, ?)");
        statement.setString(1, cidade.getNome());
        statement.setString(2, cidade.getUF());
        statement.execute();
        statement.close();
    }

     public static Cidade findById(Integer id, Connection connection) throws SQLException {
        PreparedStatement statement;
        statement = connection.prepareStatement("SELECT * FROM trabalho.cidade WHERE id = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        try {
            if (resultSet.next()) {
                Integer idCidade = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String uf = resultSet.getString("uf");

                return new Cidade(idCidade, nome, uf);
            } else {
                return null;
            }
        } finally {
            statement.close();
        }
    }

    public static List<Cidade> listAll(Connection con) throws SQLException {
        Statement st;
        List<Cidade> cidades = new ArrayList<>();
        st = con.createStatement();
        String sql = "SELECT id, nome, uf FROM trabalho.cidade";
        ResultSet result = st.executeQuery(sql);
        while(result.next()) {
            cidades.add(new Cidade(result.getInt(1), result.getString(2), result.getString(3)));
        }
        return cidades;
    }
}
