package core.utils;

import core.exception.CampoInvalidoExceptions;
import core.service.AgenciaService;
import core.service.AgenciaServiceBean;
import core.service.CidadeService;
import core.service.CidadeServiceBean;
import core.service.ClienteService;
import core.service.ClienteServiceBean;
import entity.Agencia;
import entity.Cidade;
import entity.Cliente;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class Utils {

    static CidadeService cidadeService = new CidadeServiceBean();
    static AgenciaService agenciaService = new AgenciaServiceBean();
    static ClienteService clienteService = new ClienteServiceBean();

    public static void validateGenericInput(Object object) throws CampoInvalidoExceptions {
        if (Objects.isNull(object) || object.toString().isEmpty()){
            throw new CampoInvalidoExceptions("O campo nome não pode estar vazio ou nulo");
        }
    }

    public static String showAllCitysFormated(Connection con) throws SQLException {
        List<Cidade> cidades = cidadeService.listAll(con);
        StringBuilder stringBuilder = new StringBuilder();
        if (Objects.nonNull(cidades)) {
            for (Cidade cidade : cidades) {
                stringBuilder.append("\n ID: " + cidade.getId()
                        + " | NOME: " + cidade.getNome() +
                        " | UF: " + cidade.getUF() + "\n");
            }
        }
        return  stringBuilder.toString();
    }

    public static String showAllAgenciaFormated(Connection con) throws SQLException {
        List<Agencia> agencias = agenciaService.listAll(con);
        StringBuilder stringBuilder = new StringBuilder();
        if (Objects.nonNull(agencias)) {
            for (Agencia agencia : agencias) {
                stringBuilder.append("\n ID: " + agencia.getId()
                        + " | NOME: " + agencia.getNome() +
                        " | cnpj: " + agencia.getCnpj() + "\n");
            }
        }
        return  stringBuilder.toString();
    }

    public static String showAllClienteFormated(Connection con) throws SQLException {
        List<Cliente> clientes = clienteService.listAll(con);
        StringBuilder stringBuilder = new StringBuilder();
        if (Objects.nonNull(clientes)){
            for (Cliente cliente : clientes){
                stringBuilder.append("\n ID: " + cliente.getId()
                        + " | NOME: " + cliente.getNome()
                        + " | CPF " + cliente.getCpf() + "\n");
            }
        }
        return stringBuilder.toString();
    }
}
