package Controller;

import core.service.AgenciaService;
import core.service.AgenciaServiceBean;
import core.service.CidadeService;
import core.service.CidadeServiceBean;
import core.service.ClienteService;
import core.service.ClienteServiceBean;
import core.service.ContaService;
import core.service.ContaServiceBean;
import core.service.MovimentacaoService;
import core.service.MovimentacaoServiceBean;
import entity.Conta;
import entity.TipoConta;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.util.Objects;
import java.util.Scanner;


public class Controller {
    static ClienteService clienteService = new ClienteServiceBean();
    static ContaService contaService  = new ContaServiceBean();
    static CidadeService cidadeService  = new CidadeServiceBean();
    static AgenciaService agenciaService  = new AgenciaServiceBean();
    static MovimentacaoService movimentacaoService  = new MovimentacaoServiceBean();

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

    private static void autenticacao(Connection connection) throws Exception {
        System.out.println("Digite o número de sua conta");
        Integer numero = input.nextInt();
        System.out.println("Digite a senha de sua conta");
        Integer senha = input.nextInt();
        Conta conta = contaService.findByPasswordAndNumber(connection, numero, senha);
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

    private static void menuCliente(Connection connection, Conta conta) throws Exception {
        int op = 0;

        do {

            TipoConta tipoConta = null;
            for (TipoConta tipo : TipoConta.values()) {
                if (tipo.getCod() == conta.getTipo()) {
                    tipoConta = tipo;
                    break;
                }
            }
            System.out.println("Olá " + clienteService.getById(connection, conta.getCliente()).getNome() + "\n" +
                    "NÚMERO DA CONTA: " + conta.getNumero() + " | SALDO: " + conta.getSaldo() + " | TIPO: " + tipoConta.getDescricao());
            System.out.println("_______________________________ \n O que deseja fazer?\n\n" +
                    "01 - Sacar\n" +
                    "02 - Depositar\n" +
                    "03 - Realizar pagamento de boletos\n" + //toDO COMO LER CÓDIGO BOLETO
                    "04 - Transferências\n" +
                    "05 - Consultar extrato\n" +
                    "06 - Atualizar seus dados\n" + //toDO
                    "\n\nDigite qualquer outro valor para sair\nSua opção: \n");
            op = input.nextInt();
            switch (op){
                case 1: movimentacaoService.saca(connection, conta);
                    break;
                case 2: movimentacaoService.deposito(connection, conta);
                    break;
                case 3: movimentacaoService.pagamento(connection, conta);
                    break;
                case 4: movimentacaoService.transferencia(connection, conta);
                    break;
                case 5: movimentacaoService.extrato(connection, conta);
                    break;
                case 6: clienteService.update(connection);
                    break;
            }
        } while(op>0 && op<7);

    }

    private static void menuAdmin(Connection connection) throws Exception {
        JOptionPane.showMessageDialog(null,"ACESSO PERMITIDO!!");
        int op = 0;

        do {
            System.out.println("O que deseja fazer?\n\n" +
                            "01 - cadastrar cliente\n" +
                            "02 - criar conta\n" +
                            "03 - apagar conta\n" +
                            "04 - abrir agencia\n" +
                            "05 - cadastrar cidade\n" +
                            "06 - deletetar cidade\n" +
                            "07 - deletar agência\n" +
                            "08 - Remover cliente\n" +
                            "09 - Atualizar dados agência\n" +
                            "10 - Relatório: Listar agência por cidade" +
                            "\n\nDigite qualquer outro valor para sair\nSua opção: \n");
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
                case 6: cidadeService.delete(connection);
                    break;
                case 7: agenciaService.delete(connection);
                    break;
                case 8: clienteService.delete(connection);
                    break;
                case 9: agenciaService.update(connection);
                    break;
                case 10: agenciaService.listByCidade(connection);
                    break;
            }
        } while(op>0 && op<11);
    }

}
