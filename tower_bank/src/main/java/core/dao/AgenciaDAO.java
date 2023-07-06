package core.dao;

import dto.AgenciaCidadeDTO;
import entity.Agencia;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Values;
import org.neo4j.driver.types.Node;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AgenciaDAO {

    public static void create(Driver connection, Agencia agencia) throws SQLException {
        try (Session session = connection.session()) {
            session.run("MATCH (cidade:Cidade) WHERE id(cidade) = $id " +
                            "CREATE (agencia:Agencia {nome: $nome, endereco: $endereco, cnpj: $cnpj})-[:LOCALIZADA_EM]->(cidade)",
                    Values.parameters("nome", agencia.getNome(),
                            "endereco", agencia.getEndereco(),
                            "cnpj", agencia.getCnpj(),
                            "id", agencia.getCodigoCidade()));
        }
    }

    public static Agencia findById(Driver connection, Integer id) throws SQLException {
        try (Session session = connection.session()){
            Result result = session.run("MATCH (agencia:Agencia)-[:LOCALIZADA_EM]->(cidade:Cidade) WHERE id(agencia) = $id RETURN agencia, id(cidade) AS codigoCidade",
                    Values.parameters("id", id));
            if (result.hasNext()){
                Record next = result.next();
                Node agenciaNode = next.get("agencia").asNode();
                Integer codigoCidade = next.get("codigoCidade").asInt();

                return new Agencia(Integer.parseInt(agenciaNode.elementId()), agenciaNode.get("nome").asString(),
                        agenciaNode.get("endereco").asString(), agenciaNode.get("cnpj").asString(), codigoCidade);
            } else {
                return null;
            }
        }
    }

    public static boolean deleteById(Driver con, Integer id) throws SQLException {

        try (Session session = con.session()) {
            session.run("MATCH (agencia:Agencia)-[r:LOCALIZADA_EM]->(cidade:Cidade) WHERE id(agencia) = $id DELETE r, agencia",
                    Values.parameters("id", id));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Agencia> listAll(Driver con) throws SQLException {
        List<Agencia> agencias = new ArrayList<>();

        try (Session session = con.session()) {
            Result result = session.run("MATCH (agencia:Agencia)-[:LOCALIZADA_EM]->(cidade:Cidade) RETURN agencia, id(cidade) AS codigoCidade");
            while (result.hasNext()) {
                Record next = result.next();
                Node agenciaNode = next.get("agencia").asNode();
                Integer codigoCidade = next.get("codigoCidade").asInt();

                Agencia agenciaResult = new Agencia(Integer.parseInt(agenciaNode.elementId()), agenciaNode.get("nome").asString(),
                        agenciaNode.get("endereco").asString(), agenciaNode.get("cnpj").asString(), codigoCidade);
                agencias.add(agenciaResult);
            }
        }
        return agencias;
    }


    public static void update(Driver con, Agencia novaAgencia, Integer id) throws SQLException {
        try (Session session = con.session()) {
            session.run("MATCH (agencia:Agencia) WHERE id(agencia) = $id SET agencia.nome = $nome," +
                            " agencia.endereco = $endereco, agencia.cnpj = $cnpj",
                    Values.parameters("id", id,
                            "nome", novaAgencia.getNome(),
                            "endereco", novaAgencia.getEndereco(),
                            "cnpj", novaAgencia.getCnpj()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<AgenciaCidadeDTO> listByCidade(Driver con, Integer idCidade) throws SQLException {
        try (Session session = con.session()) {
            Result result = session.run("MATCH (cidade:Cidade)<-[:LOCALIZADA_EM]-(agencia:Agencia) " +
                            "WHERE id(cidade) = $idCidade RETURN agencia, cidade.nome AS nomeCidade",
                    Values.parameters("idCidade", idCidade));

            List<AgenciaCidadeDTO> agencias = new ArrayList<>();
            while (result.hasNext()) {
                Record record = result.next();
                Node agenciaNode = record.get("agencia").asNode();
                String nomeCidade = record.get("nomeCidade").asString();

                AgenciaCidadeDTO agencia = new AgenciaCidadeDTO(agenciaNode.get("nome").asString(),
                        agenciaNode.get("endereco").asString(), agenciaNode.get("cnpj").asString(), nomeCidade);
                agencias.add(agencia);
            }
            return agencias;
        }
    }

}
