package core.service;

import core.dao.ClienteDAO;
import core.utils.Utils;
import entity.Cliente;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.util.List;

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
    public void delete(Connection con) {

    }

    @Override
    public void update(Connection con) {

    }

    @Override
    public List<Cliente> listAll(Connection con) {
        return null;
    }

    @Override
    public Cliente getById(Connection con, Integer id) throws Exception {
        Cliente cliente= null;
        Utils.validateGenericInput(id);
        cliente = ClienteDAO.findById(id, con);

        return cliente;
    }

}
