package core.dao;

import entity.Conta;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Values;
import org.neo4j.driver.types.Node;

import java.sql.SQLException;

public class ContaDAO {

    public static void create(Driver driver, Conta conta) {
        try (Session session = driver.session()){
            session.run(
                    "MATCH (cliente:Cliente) WHERE id(cliente) = $id_cliente " +
                            "MATCH (agencia:Agencia) WHERE id(agencia) = $id_agencia " +
                            "CREATE (cliente)-[:POSSUI]->(conta:Conta {tipo: $tipo, saldo: $saldo, numero: $numero, senha: $senha})-[:VINCULADA_A]->(agencia)",
                    Values.parameters(
                            "id_cliente", conta.getCliente(),
                    "id_agencia", conta.getAgencia(),
                    "tipo", conta.getTipo(),
                    "saldo", conta.getSaldo(),
                    "numero", conta.getNumero(),
                    "senha", conta.getSenha()
            ));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static boolean delete(Driver connection, Integer numero, Integer senha) {
        try (Session session = connection.session()) {
            session.run("MATCH (cliente:Cliente)-[b:POSSUI]->(conta:Conta)-[r:VINCULADA_A]->(agencia:Agencia) " +
                            "WHERE conta.numero = $numero AND conta.senha = $senha " +
                            "DELETE b, r, conta",
                    Values.parameters("numero", numero,
                            "senha", senha));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public static Conta getByNumberAndPassoword(Driver con, Integer num, Integer senha) {
        try (Session session = con.session()) {
            Result result = session.run("MATCH (conta:Conta)<-[:POSSUI]-(cliente:Cliente) " +
                            "MATCH (conta)-[:VINCULADA_A]-> (agencia:Agencia) WHERE conta.numero = $numero AND conta.senha = $senha RETURN conta," +
                            " id(cliente) AS clienteId, id(agencia) AS agenciaId",
                    Values.parameters(
                            "numero", num,
                            "senha", senha
                    ));
            if (result.hasNext()) {
                Record record = result.next();
                Node nodeCliente = record.get("conta").asNode();
                Conta conta = new Conta(
                        Integer.parseInt(nodeCliente.elementId()),
                        record.get("clienteId").asInt(),
                        record.get("agenciaId").asInt(),
                        nodeCliente.get("tipo").asInt(),
                        nodeCliente.get("saldo").asDouble(),
                        nodeCliente.get("numero").asInt()
                );
                return conta;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void updateSaldo(Driver con, Conta conta) throws SQLException {
        try (Session session = con.session()) {
            session.run("MATCH (conta:Conta) WHERE id(conta) = $id " +
                            "SET conta.saldo = $saldo",
                    Values.parameters("id", conta.getId(), "saldo", conta.getSaldo()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Conta findByNumber(Driver con, Integer number){
        try (Session session = con.session()) {
            Result result = session.run("MATCH (conta:Conta)<-[:POSSUI]-(cliente:Cliente) MATCH (conta)-[:VINCULADA_A]-> (agencia:Agencia) WHERE conta.numero = $numero " +
                            "RETURN conta, id(cliente) AS clienteId, id(agencia) AS agenciaId",
                    Values.parameters(
                            "numero", number));
            if (result.hasNext()) {
                Record record = result.next();
                Node nodeCliente = record.get("conta").asNode();
                Conta conta = new Conta(
                        Integer.parseInt(nodeCliente.elementId()),
                        record.get("clienteId").asInt(),
                        record.get("agenciaId").asInt(),
                        nodeCliente.get("tipo").asInt(),
                        nodeCliente.get("saldo").asDouble(),
                        nodeCliente.get("numero").asInt()
                );
                return conta;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
