package core.dao;

import entity.Cidade;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Values;
import org.neo4j.driver.types.Node;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CidadeDAO {

    public static void create(Driver connection, Cidade cidade) throws SQLException {
        try (Session session = connection.session()) {
            session.run("CREATE (cidade:Cidade {nome: $nome, uf: $uf})",
                    Values.parameters(
                            "nome", cidade.getNome(),
                            "uf", cidade.getUF()
                    ));
        }
    }

    public static Cidade findById(Integer id, Driver connection) throws SQLException {
        try (Session session = connection.session()) {
            Result result = session.run("MATCH (cidade:Cidade) WHERE id(cidade) = $id RETURN cidade",
                    Values.parameters("id", id));

            if (result.hasNext()) {
                Node cidade = result.next().get("cidade").asNode();
                return new Cidade(Integer.parseInt(cidade.elementId()), cidade.get("nome").asString(), cidade.get("uf").asString());
            } else {
                return null;
            }
        }
    }

    public static List<Cidade> listAll(Driver con) throws SQLException {
        List<Cidade> cidades = new ArrayList<>();

        try (Session session = con.session()) {
            Result result = session.run("MATCH (cidade:Cidade) RETURN cidade ");
            while (result.hasNext()) {
                Node cidadeNode = result.next().get("cidade").asNode();
                Cidade cidadeResult = new Cidade(
                        Integer.parseInt(cidadeNode.elementId()),
                        cidadeNode.get("nome").asString(),
                        cidadeNode.get("uf").asString()
                );
                cidades.add(cidadeResult);
            }
        }
        return cidades;
    }

    public static boolean deleteById(Driver con, Integer id) throws SQLException {
        try (Session session = con.session()) {
            session.run("MATCH (cidade:Cidade) WHERE id(cidade) = $id DELETE cidade",
                    Values.parameters("id", id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}


