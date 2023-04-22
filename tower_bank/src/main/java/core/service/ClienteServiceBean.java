package core.service;

import core.dao.ClienteDAO;
import core.exception.CampoInvalidoExceptions;
import core.utils.Utils;
import dto.ClienteCidadeDTO;
import entity.Cliente;
import entity.Conta;

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

public class ClienteServiceBean implements ClienteService{

    @Override
    public void create(Connection con) throws Exception {
        JOptionPane.showMessageDialog(null, "PREENCHA AS INFORMAÇÕES QUE IREMOS SOLICITAR " +
                "COM ATENÇÃO");

        String nome = JOptionPane.showInputDialog("Informe seu nome completo: ");
        String telefone = JOptionPane.showInputDialog("Informe um telefone para contato: ");
        String email = JOptionPane.showInputDialog("Informe seu email: ");
        String cpf = JOptionPane.showInputDialog("Informe seu cpf: ");
        String endereco = JOptionPane.showInputDialog("Informe seu endereço: ");

        Utils.validateGenericInput(nome);
        Utils.validateGenericInput(telefone);
        Utils.validateGenericInput(email);
        Utils.validateGenericInput(cpf);
        Utils.validateGenericInput(endereco);

        Cliente cliente = new Cliente(nome, telefone, email, cpf, endereco);
        ClienteDAO.create(cliente, con);
    }

    @Override
    public void delete(Connection con) throws SQLException {
        Integer clienteId = Integer.valueOf(JOptionPane.showInputDialog("Digiete o ID do cliente que deseja excluir\n" +
                Utils.showAllClienteFormated(con)));

        ClienteDAO.deleteById(con, clienteId);
    }

    @Override
    public void update(Connection con, Conta conta) throws Exception {
        Cliente cliente = getById(con, conta.getCliente());

        JOptionPane.showMessageDialog(null, "A SEGUIR INFORME OS NOVOS DADOS, CASO NÃO HAJA MODIFICAÇÕES" +
                "APENAS TECLE ENTER");

        String telefone = JOptionPane.showInputDialog("Novo telefone: ");
        String email = JOptionPane.showInputDialog("Novo e-mail: ");
        String endereco = JOptionPane.showInputDialog("Novo endereco: ");

        if (Objects.isNull(telefone) || telefone.isEmpty()){
            telefone = cliente.getTelefone();
        }

        if (Objects.isNull(email) || email.isEmpty()){
            email = cliente.getEmail();
        }

        if (Objects.isNull(endereco) || endereco.isEmpty()){
            endereco = cliente.getEndereco();
        }

        Cliente novosDados = new Cliente(cliente.getId(), cliente.getNome(), telefone, email, cliente.getCpf(), endereco);

        ClienteDAO.update(con, novosDados);
    }

    @Override
    public List<Cliente> listAll(Connection con) throws SQLException {
        return ClienteDAO.listaAllClientes(con);
    }

    @Override
    public Cliente getById(Connection con, Integer id) throws Exception {
        Cliente cliente= null;
        Utils.validateGenericInput(id);
        cliente = ClienteDAO.findById(id, con);

        return cliente;
    }

    @Override
    public void listByCidade(Connection connection) throws SQLException, IOException, CampoInvalidoExceptions {

        Integer idCidade = Integer.valueOf(JOptionPane.showInputDialog("Você quer visualizar os clientes de qual cidade - digite o ID\n" +
                Utils.showAllCitysFormated(connection)));

        List<ClienteCidadeDTO> clienteCidadesDTOs = ClienteDAO.listByCidade(connection, idCidade);

        File file = new File("src/main/resources/relatórios/relatorioClienteCidade.txt");
        Utils.validateGenericInput(file);

        OutputStream fis = new FileOutputStream(file);
        Writer writer = new OutputStreamWriter(fis);
        BufferedWriter bufferedReader = new BufferedWriter(writer);

        bufferedReader.write(String.format("%-30s%-15s%-15s%s\n", "NOME CLIENTE ", "NUMERO CONTA", "TIPO CONTA", "NOME CIDADE\n__________________________________________________________________________________________"));

        for (ClienteCidadeDTO clienteCidadeDTO : clienteCidadesDTOs) {
            bufferedReader.write(clienteCidadeDTO.toString());
        }

        bufferedReader.close();
        Desktop.getDesktop().open(file);
    }

}
