package core.dao;

import connection.Con;
import entity.Conta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public static Conta getByNumberAndPassoword(Connection con, Integer num, Integer senha) throws SQLException {
        Conta conta = null;
        PreparedStatement st;
        st = con.prepareStatement("SELECT * FROM trabalho.conta WHERE numero = ? and senha = ?");
        st.setInt(1, num);
        st.setInt(2, senha);
        ResultSet resultSet = st.executeQuery();
        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            Integer id_cliente = resultSet.getInt("id_cliente");
            Integer id_agencia = resultSet.getInt("id_agencia");
            Integer tipo = resultSet.getInt("tipo");
            Integer numero = resultSet.getInt("numero");
            Double saldo = resultSet.getDouble("saldo");

            conta = new Conta(id, id_cliente, id_agencia, tipo, saldo, numero);
        }

        st.close();
        return conta;
    }

    public static void updateSaldo(Connection con, Conta conta) throws SQLException {
        PreparedStatement statement;
        statement = con.prepareStatement("UPDATE trabalho.conta SET SALDO = ? WHERE id = ?");
        statement.setDouble(1, conta.getSaldo());
        statement.setInt(2, conta.getId());
        statement.execute();
        statement.close();
    }

    public static Conta findByNumber(Connection con, Integer number) throws SQLException {
        Conta conta = null;
        PreparedStatement st;
        st = con.prepareStatement("SELECT * FROM trabalho.conta WHERE number = ?");
        st.setInt(1, number);
        ResultSet resultSet = st.executeQuery();
        while (resultSet.next()){
            Integer id = resultSet.getInt("id");
            Integer id_cliente = resultSet.getInt("id_cliente");
            Integer id_agencia = resultSet.getInt("id_agencia");
            Integer tipo = resultSet.getInt("tipo");
            Integer numero = resultSet.getInt("numero");
            Double saldo = resultSet.getDouble("saldo");

            conta = new Conta(id, id_cliente, id_agencia, tipo, saldo, numero);
        }
        st.close();
        return conta;
    }
}
