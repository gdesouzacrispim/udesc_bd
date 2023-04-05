package core.service;

import core.dao.AgenciaDAO;
import core.exception.CampoInvalidoExceptions;
import core.utils.Utils;
import entity.Agencia;
import entity.Cidade;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class AgenciaServiceBean implements AgenciaService {
    static CidadeService cidadeService = new CidadeServiceBean();
    Scanner input = new Scanner(System.in);

    @Override
    public void create(Connection con) throws Exception {

        List<Cidade> cidades = cidadeService.listAll(con);
        StringBuilder stringBuilder = new StringBuilder();
        if (Objects.nonNull(cidades)) {
            for (Cidade cidade : cidades) {
                stringBuilder.append("\n ID: " + cidade.getId()
                        + " | NOME: " + cidade.getNome() +
                        " | UF: " + cidade.getUF() + "\n");
            }
        }

        String nome = JOptionPane.showInputDialog("Digite o nome da agencia");
        String endereco = JOptionPane.showInputDialog("Digite o endereco da agência: (ex: Rua Paraná - 45)");
        String cnjp = JOptionPane.showInputDialog("Digite o CNPJ da agência");
        System.out.println("Digite em qual cidade ficará a agência: (Digite o ID)\n " + stringBuilder);
        Integer cidadeId = input.nextInt();

        Utils.validateGenericInput(nome);
        Utils.validateGenericInput(endereco);
        Utils.validateGenericInput(cnjp);

        if (Objects.isNull(cidadeService.getById(con, cidadeId))) {
            throw new CampoInvalidoExceptions("Cidade não encontra com id: " + cidadeId);
        }

        Agencia agencia = new Agencia(nome, endereco, cnjp, cidadeId);

        AgenciaDAO.create(con, agencia);
    }

    @Override
    public void delete(Connection con) {

    }

    @Override
    public void update(Connection con) {

    }

    @Override
    public List<Agencia> listAll(Connection con) {
        return null;
    }

    @Override
    public Agencia getById(Connection con, Integer id) throws CampoInvalidoExceptions, SQLException {
        Utils.validateGenericInput(id);
        return AgenciaDAO.findById(con, id);
    }
}
