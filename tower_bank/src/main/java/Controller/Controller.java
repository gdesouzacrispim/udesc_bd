package Controller;

import core.service.ClienteService;
import core.service.ClienteServiceBean;
import entity.Conta;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.util.Objects;


public class Controller {
    static ClienteService clienteService = new ClienteServiceBean();

    public static void menu(Connection connection) throws Exception {
        int op = 0;

        do {
            op = Integer.parseInt(JOptionPane.showInputDialog("BEM-VINDO AO TOWER BANK\n\nO que deseja fazer?\n" +
                    "1 - Acessar conta\n2 - Administrativo\n\nDigite qualquer outro valor para sair\nSua opção: \n"));
            switch (op){
                case 1: autenticacao(connection);
                break;
                case 2: admin(connection);
            }
        } while(op>0 && op<3);
    }

    private static void autenticacao(Connection connection){
        String numero = JOptionPane.showInputDialog("Digite o número de sua conta");
        String senha = JOptionPane.showInputDialog("Digite a senha de sua conta");
        //toDO - buscar conta com essas credenciais
        Conta conta = null;
        menuCliente(connection, conta);
    }
    
    private static void admin(Connection connection) throws Exception {
        String senhaAdmin = JOptionPane.showInputDialog("Digite a senha de administrador: ");
        if (Objects.equals(senhaAdmin, "pudim")){
            menuAdmin(connection);
        } else {
            JOptionPane.showMessageDialog(null,"ACESSO NEGADO!!");
            menu(connection);
        }
    }

    private static void menuCliente(Connection connection, Conta conta){}

    private static void menuAdmin(Connection connection) throws Exception {
        JOptionPane.showMessageDialog(null,"ACESSO PERMITIDO!!");
        int op = 0;

        do {
            op = Integer.parseInt(JOptionPane.showInputDialog("O que deseja fazer?\n\n" +
                    "1 - cadastrar cliente\n2 - criar conta\n3 - apagar conta\n4 - abrir agencia\n5 - cadastrar municípip" +
                    "\n6 - buscar agência por cidade\n"));
            switch (op){
                case 1: clienteService.create(connection);
                    break;
                case 2: break;
            }
        } while(op>0 && op<7);
    }

}
