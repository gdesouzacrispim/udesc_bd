package Controller;

import core.service.AgenciaService;
import core.service.AgenciaServiceBean;
import core.service.CidadeService;
import core.service.CidadeServiceBean;
import core.service.ClienteService;
import core.service.ClienteServiceBean;
import core.service.ContaService;
import core.service.ContaServiceBean;
import entity.Conta;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.util.Objects;
import java.util.Scanner;


public class Controller {
    static ClienteService clienteService = new ClienteServiceBean();
    static ContaService contaService  = new ContaServiceBean();
    static CidadeService cidadeService  = new CidadeServiceBean();
    static AgenciaService agenciaService  = new AgenciaServiceBean();

    static Scanner input = new Scanner(System.in);


    public static void menu(Connection connection) throws Exception {
        int op = 0;

        do {
            System.out.println("BEM-VINDO AO TOWER BANK\n\nO que deseja fazer?\n" +
                    "1 - Acessar conta\n2 - Administrativo\n\nDigite qualquer outro valor para sair\nSua opção: \n");
            op = input.nextInt();
            switch (op){
                case 1: autenticacao(connection);
                break;
                case 2: admin(connection);
            }
        } while(op>0 && op<3);
    }

    private static void autenticacao(Connection connection){
        System.out.println("Digite o número de sua conta");
        String numero = input.next();
        System.out.println("Digite a senha de sua conta");
        String senha = input.next();
        //toDO - buscar conta com essas credenciais
        Conta conta = null;
        menuCliente(connection, conta);
    }
    
    private static void admin(Connection connection) throws Exception {
        System.out.println("Digite a senha de administrador: ");
        String senhaAdmin = input.next();
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
            System.out.println("O que deseja fazer?\n\n" +
                            "1 - cadastrar cliente\n2 - criar conta\n3 - apagar conta\n4 - abrir agencia\n5 - cadastrar municípip" +
                            "\n6 - buscar agência por cidade\n\nDigite outro valor para sair");
            op = input.nextInt();
            switch (op){
                case 1: clienteService.create(connection);
                    break;
                case 2: contaService.create(connection);
                    break;
                case 3: contaService.delete(connection);
                    break;
                case 4: agenciaService.create(connection);
                    break;
                case 5: cidadeService.cadastrar(connection);
                    break;
            }
        } while(op>0 && op<7);
    }

}
