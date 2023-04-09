package core.dao;

import entity.Movimentacao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MovimentacaoDAO {

    public static void insert(Connection connection, Movimentacao movimentacao) throws SQLException {
        PreparedStatement statement;
        Date date = new Date(movimentacao.getData().getTime());
        statement = connection.prepareStatement("INSERT INTO trabalho.movimentacao(id_conta_autor, operacao, id_conta_op," +
                " id_agencia_op, data, valor, descricao) VALUES (?, ?, ?, ?, ?, ?, ?)");
        statement.setInt(1, movimentacao.getAutor());
        statement.setInt(2, movimentacao.getOperacao());
        statement.setObject(3, movimentacao.getContaDestino());
        statement.setInt(4, movimentacao.getAgencia());
        statement.setDate(5, date);
        statement.setDouble(6, movimentacao.getValor());
        statement.setString(7, movimentacao.getDescricao());
        statement.execute();
        statement.close();
    }
}
