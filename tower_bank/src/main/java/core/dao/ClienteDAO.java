package core.dao;

import dto.ClienteCidadeDTO;
import entity.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

    public static List<Cliente> listaAllClientes(Connection con) throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        Statement st;
        st = con.createStatement();
        String sql = "SELECT id, nome, telefone, email, cpf, endereco FROM trabalho.cliente";
        ResultSet resultSet = st.executeQuery(sql);
        while (resultSet.next()){
            clientes.add(new Cliente(resultSet.getInt(1), resultSet.getString(2),
                    resultSet.getString(3), resultSet.getString(4), resultSet.getString(5),
                    resultSet.getString(6)));
        }
        st.close();
        return clientes;
    }

    public static void deleteById(Connection con, Integer id) throws SQLException {
        PreparedStatement st;
        st = con.prepareStatement("DELETE FROM trabalho.cliente WHERE id = ?");
        st.setInt(1, id);
        st.execute();
        st.close();
    }

    public static void update(Connection con, Cliente cliente) throws SQLException {
        PreparedStatement st;
        st = con.prepareStatement("UPDATE  trabalho.cliente SET telefone = ?, email = ?, endereco = ?" +
                " where id = ?");
        st.setString(1, cliente.getTelefone());
        st.setString(2, cliente.getEmail());
        st.setString(3, cliente.getEndereco());
        st.setInt(4, cliente.getId());
        st.execute();
        st.close();
    }

    public static List<ClienteCidadeDTO> listByCidade (Connection con, Integer idCidade) throws SQLException {
        List<ClienteCidadeDTO> clienteCidadeDTOS = new ArrayList<>();
        PreparedStatement st;
        st = con.prepareStatement("select c.nome, c2.numero, c2.tipo, ci.nome from trabalho.cliente c\n" +
                "    join trabalho.conta c2 on c.id = c2.id_cliente\n" +
                "    join trabalho.agencia a on c2.id_agencia = a.id\n" +
                "    join trabalho.cidade ci on a.cidade = ci.id where ci.id = ? ");
        st.setInt(1, idCidade);
        ResultSet resultSet = st.executeQuery();

        while (resultSet.next()){
            clienteCidadeDTOS.add(new ClienteCidadeDTO(resultSet.getString(1), resultSet.getInt(2),
                    resultSet.getInt(3), resultSet.getString(4)));
        }
        st.close();
        return clienteCidadeDTOS;
    }
}
