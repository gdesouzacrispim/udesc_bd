package core.service;

import core.dao.AgenciaDAO;
import core.exception.CampoInvalidoExceptions;
import core.utils.Utils;
import dto.AgenciaCidadeDTO;
import entity.Agencia;
import entity.Cidade;

import javax.swing.JOptionPane;
import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
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

        String nome = JOptionPane.showInputDialog("Digite o nome da agencia");
        String endereco = JOptionPane.showInputDialog("Digite o endereco da agência: (ex: Rua Paraná - 45)");
        String cnjp = JOptionPane.showInputDialog("Digite o CNPJ da agência");
        System.out.println("Digite em qual cidade ficará a agência: (Digite o ID)\n " + Utils.showAllCitysFormated(con));
        Integer cidadeId = input.nextInt();

        Utils.validateGenericInput(nome);
        Utils.validateGenericInput(endereco);
        Utils.validateGenericInput(cnjp);

        if (Objects.isNull(cidadeService.getById(con, cidadeId))) {
            JOptionPane.showMessageDialog(null, "Cidade inválida");
            throw new CampoInvalidoExceptions("Cidade não encontraDA com id: " + cidadeId);
        }

        Agencia agencia = new Agencia(nome, endereco, cnjp, cidadeId);

        AgenciaDAO.create(con, agencia);
    }

    @Override
    public void delete(Connection con) throws SQLException, CampoInvalidoExceptions {
        Integer idCidade = Integer.valueOf(JOptionPane.showInputDialog("Infome o ID da agência que deseja apagar\n " +
                Utils.showAllAgenciaFormated(con)));

        Utils.validateGenericInput(idCidade);

        AgenciaDAO.deleteById(con, idCidade);
    }


    @Override
    public void update(Connection con) throws Exception {
        Agencia novaAgencia = new Agencia();

        Integer agenciaId = Integer.valueOf(JOptionPane.showInputDialog("Qual ID da agência que você deseja atualizar?\n"
                + Utils.showAllAgenciaFormated(con)));

        Utils.validateGenericInput(agenciaId);
        Agencia agencia = getById(con, agenciaId);
        if (Objects.isNull(agencia)) {
            JOptionPane.showMessageDialog(null, "Agência não existe");
            throw new CampoInvalidoExceptions("Agencia não existe - operação encerrada");
        }

        JOptionPane.showMessageDialog(null, "A SEGUIR INFORME OS NOVOS DADOS, CASO NÃO HAJA MODIFICAÇÕES" +
                "APENAS TECLE ENTER");

        String nome = JOptionPane.showInputDialog("Novo nome: ");
        String endereco = JOptionPane.showInputDialog("Novo endereno: ");
        String cnpj = JOptionPane.showInputDialog("Novo CNPJ");
        Integer idNovaCidade = Integer.valueOf(JOptionPane.showInputDialog("novo Id da cidade \n " + Utils.showAllCitysFormated(con)));

        if (Objects.nonNull(idNovaCidade)) {
            Cidade cidade = cidadeService.getById(con, idNovaCidade);
            while (Objects.isNull(cidade)) {
                idNovaCidade = Integer.valueOf(JOptionPane.showInputDialog("cidade inválida, tente novamente"));
                cidade = cidadeService.getById(con, idNovaCidade);
            }
            novaAgencia.setCodigoCidade(idNovaCidade);
        } else {
            novaAgencia.setCodigoCidade(agencia.getCodigoCidade());
        }

        if (Objects.nonNull(nome) && !nome.isEmpty()) {
            novaAgencia.setNome(nome);
        } else {
            novaAgencia.setNome(agencia.getNome());
        }

        if (Objects.nonNull(endereco) && !endereco.isEmpty()) {
            novaAgencia.setEndereco(endereco);
        } else {
            novaAgencia.setEndereco(agencia.getEndereco());
        }

        if (Objects.nonNull(cnpj) && !cnpj.isEmpty()) {
            novaAgencia.setCnpj(cnpj);
        } else {
            novaAgencia.setCnpj(agencia.getCnpj());
        }

        AgenciaDAO.update(con, novaAgencia, agencia.getId());
    }

    @Override
    public List<Agencia> listAll(Connection con) throws SQLException {
        return AgenciaDAO.listAll(con);
    }

    @Override
    public Agencia getById(Connection con, Integer id) throws CampoInvalidoExceptions, SQLException {
        Utils.validateGenericInput(id);
        return AgenciaDAO.findById(con, id);
    }

    @Override
    public void listByCidade(Connection con) throws SQLException, IOException, CampoInvalidoExceptions {

        Integer idCidade = Integer.valueOf(JOptionPane.showInputDialog("Você quer visualizar as agências de qual cidade - digite o ID\n" +
                Utils.showAllCitysFormated(con)));

        List<AgenciaCidadeDTO> agenciaCidadeDTOS = AgenciaDAO.listByCidade(con, idCidade);

        File file = new File("src/main/resources/relatórios/relatorioAgenciaCidade.txt");
        Utils.validateGenericInput(file);

        OutputStream fis = new FileOutputStream(file);
        Writer writer = new OutputStreamWriter(fis);
        BufferedWriter bufferedReader = new BufferedWriter(writer);


        for (AgenciaCidadeDTO agencia : agenciaCidadeDTOS) {
            bufferedReader.write(agencia.toString());
        }

        bufferedReader.close();
        Desktop.getDesktop().open(file);
    }
}
