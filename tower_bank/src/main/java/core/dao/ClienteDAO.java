package core.dao;

import dto.ClienteCidadeDTO;
import entity.Cliente;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Values;
import org.neo4j.driver.types.Node;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {


    public static void create(Driver driver, Cliente cliente) {
        try (Session session = driver.session()) {
            session.run("CREATE (cliente:Cliente {nome: $nome, telefone: $telefone, email: $email, " +
                            "cpf: $cpf, endereco: $endereco})",
                    Values.parameters(
                            "nome", cliente.getNome(),
                            "telefone", cliente.getTelefone(),
                            "email", cliente.getEmail(),
                            "cpf", cliente.getCpf(),
                            "endereco", cliente.getEndereco()
                    ));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static Cliente findById(Integer id, Driver driver) {
        try (Session session = driver.session()) {
            Result result = session.run("MATCH (cliente:Cliente) WHERE id(cliente) = $id RETURN cliente",
                    Values.parameters(
                            "id", id
                    ));
            if (result.hasNext()) {
                Node nodeCliente = result.next().get("cliente").asNode();
                Cliente cliente = new Cliente();
                cliente.setId(Integer.valueOf(nodeCliente.elementId()));
                cliente.setNome(nodeCliente.get("nome").asString());
                cliente.setTelefone(nodeCliente.get("telefone").asString());
                cliente.setEmail(nodeCliente.get("email").asString());
                cliente.setCpf(nodeCliente.get("cpf").asString());
                cliente.setEndereco(nodeCliente.get("endereco").asString());
                return cliente;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Cliente> listaAllClientes(Driver con) throws SQLException {
        List<Cliente> clientes = new ArrayList<>();

        try (Session session = con.session()) {
            Result result = session.run("MATCH (cliente:Cliente) return cliente");
            while (result.hasNext()){
                Node nodeCliente = result.next().get("cliente").asNode();
                Cliente cliente = new Cliente();
                cliente.setId(Integer.valueOf(nodeCliente.elementId()));
                cliente.setNome(nodeCliente.get("nome").asString());
                cliente.setTelefone(nodeCliente.get("telefone").asString());
                cliente.setEmail(nodeCliente.get("email").asString());
                cliente.setCpf(nodeCliente.get("cpf").asString());
                cliente.setEndereco(nodeCliente.get("endereco").asString());

                clientes.add(cliente);
            }
        }

        return clientes;
    }

    public static void deleteById(Driver con, Integer id) throws SQLException {
        try (Session session = con.session()) {
            session.run("MATCH (cliente:Cliente)-[r:POSSUI]->(conta:Conta)" +
                            "WHERE id(cliente) = $id DELETE r, cliente",
                    Values.parameters("id", id));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void update(Driver con, Cliente cliente) throws SQLException {
        try (Session session = con.session()) {
            session.run("MATCH (cliente:Cliente) WHERE id(cliente) = $id " +
                            "SET cliente.telefone = $telefone, cliente.email = $email, cliente.endereco = $endereco",
                    Values.parameters("id", cliente.getId(), "telefone", cliente.getTelefone(), "email",
                            cliente.getEmail(), "endereco", cliente.getEndereco()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<ClienteCidadeDTO> listByCidade(Driver con, Integer idCidade) {
        List<ClienteCidadeDTO> clienteCidadeDTOS = new ArrayList<>();

        try (Session session = con.session()) {
            Result result = session.run("MATCH (cliente:Cliente)-[:POSSUI]->(conta:Conta)-[:VINCULADA_A]->(:Agencia)-[:LOCALIZADA_EM]->(cidade:Cidade) " +
                            "WHERE id(cidade) = $idCidade " +
                            "RETURN cliente.nome AS nome, conta.numero AS numero, conta.tipo AS tipo, cidade.nome AS cidade",
                    Values.parameters("idCidade", idCidade));

            while (result.hasNext()) {
                Record record = result.next();
                String nome = record.get("nome").asString();
                Integer numero = record.get("numero").asInt();
                Integer tipo = record.get("tipo").asInt();
                String cidade = record.get("cidade").asString();

                clienteCidadeDTOS.add(new ClienteCidadeDTO(nome, numero, tipo, cidade));
            }
        }

        return clienteCidadeDTOS;
    }

}
