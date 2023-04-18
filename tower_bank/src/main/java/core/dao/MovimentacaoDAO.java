package core.dao;

import entity.Movimentacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MovimentacaoDAO {

    public static void insert(Connection connection, Movimentacao movimentacao) throws SQLException {
        PreparedStatement statement;
        Timestamp timestamp = new Timestamp(movimentacao.getData().getTime());
        statement = connection.prepareStatement("INSERT INTO trabalho.movimentacao(id_conta_autor, operacao, id_conta_op," +
                " id_agencia_op, data, valor, descricao) VALUES (?, ?, ?, ?, ?, ?, ?)");
        statement.setInt(1, movimentacao.getAutor());
        statement.setInt(2, movimentacao.getOperacao());
        statement.setObject(3, movimentacao.getContaDestino());
        statement.setInt(4, movimentacao.getAgencia());
        statement.setTimestamp(5, timestamp);
        statement.setDouble(6, movimentacao.getValor());
        statement.setString(7, movimentacao.getDescricao());
        statement.execute();
        statement.close();
    }

    public static List<Movimentacao> extrato(Connection connection, java.util.Date initialDate, java.util.Date finalDate, Integer numeroConta) throws SQLException {
        List<Movimentacao> movimentacoess = new ArrayList<>();
        Timestamp timestampInicial = new Timestamp(initialDate.getTime());
        Timestamp timestampFinal = new Timestamp(finalDate.getTime());

        PreparedStatement statement;
        statement = connection.prepareStatement("select data, descricao, valor from trabalho.movimentacao where " +
                "(id_conta_op = ? or id_conta_autor = ?) and data between ? and ?");

        statement.setInt(1, numeroConta);
        statement.setInt(2, numeroConta);
        statement.setTimestamp(3, timestampInicial);
        statement.setTimestamp(4, timestampFinal);

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Date data = resultSet.getTimestamp("data");
            Double valor = resultSet.getDouble("valor");
            String descricao = resultSet.getString("descricao");

            movimentacoess.add(new Movimentacao(data, valor, descricao));
        }

        statement.close();
        return movimentacoess;
    }
}
