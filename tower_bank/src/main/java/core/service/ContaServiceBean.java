package core.service;

import core.dao.ContaDAO;
import core.exception.CampoInvalidoExceptions;
import core.exception.ClientNotFoundException;
import core.utils.Utils;
import entity.Cliente;
import entity.Conta;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class ContaServiceBean implements ContaService {

    static ClienteService clienteService = new ClienteServiceBean();
    static AgenciaService agenciaService = new AgenciaServiceBean();
    Scanner input = new Scanner(System.in);

    @Override
    public void create(Connection con) throws Exception {

        JOptionPane.showMessageDialog(null, "VOCÊ ESTÁ PRESTE A CRIAR UMA CONTA PARA UM CLIENTE\n" +
                "RECOLHA OS SEGUINTES DADOS COM ATENÇÃO");

        Cliente cliente = null;

        System.out.println("Para qual cliente você quer abrir uma conta?\n (Informe o ID)");
        JOptionPane.showMessageDialog(null, Utils.showAllClienteFormated(con));
        Integer id = input.nextInt();

        cliente = clienteService.getById(con, id);

        if (Objects.isNull(cliente)) {
            throw new ClientNotFoundException();
        }

        Integer tipo = Integer.valueOf(JOptionPane.showInputDialog("Olá " + cliente.getNome() + " Informe qual o tipo de conta que tu quer abrir\n" +
                "\n (105) - corrente \n (106) - poupança\n Digite o número:"));
        Integer numeroConta = generateCountNumber();

        System.out.println("Digite o código da agência que deseja criar a conta");
        JOptionPane.showMessageDialog(null, Utils.showAllAgenciaFormated(con));
        Integer agencia = input.nextInt();

        Double saldo = Double.valueOf(JOptionPane.showInputDialog("Caso há, digite o valor do depósito incial (Ex: 100.00)"));
        if (Objects.isNull(saldo)){
            saldo = 0.00;
        }

        Integer senha = Integer.valueOf(JOptionPane.showInputDialog("Defina uma senha numérica"));

        if (Objects.isNull(agenciaService.getById(con, agencia))) {
            throw new CampoInvalidoExceptions("Agencia com código informado não encontrada");
        }
        Conta newConta = new Conta(cliente.getId(), agencia, tipo, saldo, numeroConta, senha);

        ContaDAO.create(con, newConta);
        JOptionPane.showMessageDialog(null, "Conta criada com sucesso, o número dela é:\n " + numeroConta + "" +
                "\n\n *Anote ele - será necessário informar para consultar as ações de sua conta");
    }

    @Override
    public void delete(Connection con) throws SQLException, CampoInvalidoExceptions {
        JOptionPane.showMessageDialog(null, "AQUI VOCÊ PODERÁ DELETAR UMA CONTA");

        System.out.println("Informe o número da conta que você deseja deletar");
        Integer numero = input.nextInt();
        Integer senha = Integer.valueOf(JOptionPane.showInputDialog("Digite a senha da conta, para poder deletar"));

        Utils.validateGenericInput(numero);
        Utils.validateGenericInput(senha);

        ContaDAO.delete(con, numero, senha);
    }

    @Override
    public List<Conta> listAll(Connection con) {
        return null;
    }

    @Override
    public Conta getById(Connection con, Integer id) {
        return null;
    }

    @Override
    public Conta findByPasswordAndNumber(Connection con, Integer number, Integer password) throws SQLException {
        return ContaDAO.getByNumberAndPassoword(con, number, password);
    }

    @Override
    public Conta getByNumber(Connection con, Integer number) throws SQLException {
        return ContaDAO.findByNumber(con, number);
    }

    private Integer generateCountNumber() {
        Random aleatorio = new Random();
        Integer valor = Math.abs(aleatorio.nextInt()) % Integer.MAX_VALUE;
        return valor;
    }
}
