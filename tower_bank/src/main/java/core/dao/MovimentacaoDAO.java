package core.dao;

import entity.Movimentacao;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Values;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MovimentacaoDAO {

    public static void insert(Driver connection, Movimentacao movimentacao) {
        Timestamp timestamp = new Timestamp(movimentacao.getData().getTime());
        try (Session session = connection.session()){
            session.run("MATCH (contaBonificada:Conta) WHERE id(contaBonificada) = $idContaBonificada " +
                    "MATCH (contaProvedora:Conta) WHERE id(contaProvedora) = $idContaProvedora " +
                    "MATCH (agencia:Agencia) WHERE id(agencia) = $idAgencia " +
                    "CREATE (contaProvedora) -[:REALIZA_MOV]-> (movimentacao:Movimentacao {data: $data, valor: $valor, " +
                    "operacao: $operacao, descricao: $descricao})-[:BENEFICIA]->(contaBonificada) " +
                    "CREATE (movimentacao)-[:REALIZADA_EM]->(agencia)",
                    Values.parameters(
                            "idContaBonificada", movimentacao.getContaDestino(),
                            "idContaProvedora", movimentacao.getAutor(),
                            "idAgencia", movimentacao.getAgencia(),
                            "data", timestamp.toString(),
                            "valor", movimentacao.getValor(),
                            "operacao", movimentacao.getOperacao(),
                            "descricao", movimentacao.getDescricao()
                    ));
        }
    }


    public static List<Movimentacao> extrato(Driver connection, Date initialDate, Date finalDate, Integer numeroConta) {
        List<Movimentacao> movimentacoes = new ArrayList<>();

        try (Session session = connection.session()) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String initialDateString = formatter.format(initialDate);
            String finalDateString = formatter.format(finalDate);

            Result result = session.run("MATCH (conta:Conta)-[:REALIZA_MOV]->(movimentacao:Movimentacao) " +
                            "WHERE (id(conta) = $numeroConta OR id(movimentacao) = $numeroConta) " +
                            "AND $initialDate <= movimentacao.data <= $finalDate " +
                            "RETURN movimentacao.data AS data, movimentacao.valor AS valor, movimentacao.descricao AS descricao",
                    Values.parameters(
                            "numeroConta", numeroConta,
                            "initialDate", initialDateString,
                            "finalDate", finalDateString
                    ));

            while (result.hasNext()) {
                Record record = result.next();
                Date date = formatter.parse(record.get("data").asString());
                Double valor = record.get("valor").asDouble();
                String descricao = record.get("descricao").asString();
                movimentacoes.add(new Movimentacao(date, valor, descricao));
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return movimentacoes;
    }

}
