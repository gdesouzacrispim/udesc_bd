package core.dao;

import dto.AgenciaCidadeDTO;
import entity.Agencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

    public static boolean deleteById(Connection con, Integer id) throws SQLException {
        try {
            PreparedStatement st;
            st = con.prepareStatement("DELETE FROM trabalho.agencia WHERE id = ?");
            st.setInt(1, id);
            boolean execute = st.execute();
            st.close();
            return execute;
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao executar a query: " + e.getMessage());
            return false;
        }
    }

    public static List<Agencia> listAll(Connection con) throws SQLException {
        Statement st;
        List<Agencia> agencias = new ArrayList<>();
        st = con.createStatement();
        String sql = "SELECT id, nome, cnpj FROM trabalho.agencia";
        ResultSet result = st.executeQuery(sql);
        while(result.next()) {
            agencias.add(new Agencia(result.getInt(1), result.getString(2), result.getString(3)));
        }
        st.close();
        return agencias;
    }

    public static void update(Connection con, Agencia novaAgencia, Integer id) throws SQLException {
        PreparedStatement st;
        st = con.prepareStatement("UPDATE trabalho.agencia SET nome = ?, endereco = ?, cnpj = ?, cidade = ?" +
                " WHERE id = ?");
        st.setString(1, novaAgencia.getNome());
        st.setString(2, novaAgencia.getEndereco());
        st.setString(3, novaAgencia.getCnpj());
        st.setInt(4, novaAgencia.getCodigoCidade());
        st.setInt(5, id);
        st.execute();
        st.close();
    }

    public static List<AgenciaCidadeDTO> listByCidade (Connection con, Integer idCidade) throws SQLException {
        List<AgenciaCidadeDTO> agencias = new ArrayList<>();
        PreparedStatement st;
        st = con.prepareStatement("select a.nome, a.endereco, a.cnpj, cid.nome from trabalho.agencia a join trabalho.cidade cid on cid.id = a.cidade where cid.id = ?");
        st.setInt(1, idCidade);
        ResultSet resultSet = st.executeQuery();

        while (resultSet.next()){
            agencias.add(new AgenciaCidadeDTO(resultSet.getString(1), resultSet.getString(2),
                    resultSet.getString(3), resultSet.getString(4)));
        }
        st.close();
        return agencias;
    }
}
