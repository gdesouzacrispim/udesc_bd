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
import org.neo4j.driver.Driver;

import javax.swing.JOptionPane;
import java.util.Objects;
import java.util.Scanner;


public class Controller {
    static ClienteService clienteService = new ClienteServiceBean();
    static ContaService contaService  = new ContaServiceBean();
    static CidadeService cidadeService  = new CidadeServiceBean();
    static AgenciaService agenciaService  = new AgenciaServiceBean();
    static MovimentacaoService movimentacaoService  = new MovimentacaoServiceBean();

    static Scanner input = new Scanner(System.in);


    public static void menu(org.neo4j.driver.Driver connectionGraph) throws Exception {
        int op = 0;

        do {
            System.out.println("BEM-VINDO AO TOWER BANK\n\nO que deseja fazer?\n" +
                    "1 - Acessar conta\n2 - Administrativo\n\nDigite qualquer outro valor para sair\nSua opção: \n");
            op = input.nextInt();
            switch (op){
                case 1: autenticacao(connectionGraph);
                break;
                case 2: admin(connectionGraph);
            }
        } while(op>0 && op<3);
    }

    private static void autenticacao(org.neo4j.driver.Driver connectionGraph) throws Exception {
        System.out.println("Digite o número de sua conta");
        Integer numero = input.nextInt();
        System.out.println("Digite a senha de sua conta");
        Integer senha = input.nextInt();
        Conta conta = contaService.findByPasswordAndNumber(connectionGraph, numero, senha);
        menuCliente(connectionGraph, conta);
    }
    
    private static void admin(org.neo4j.driver.Driver connectionGraph) throws Exception {
       String senhaAdmin = JOptionPane.showInputDialog("Digite a senha de administrador: ");
        if (Objects.equals(senhaAdmin, "pudim")){
            menuAdmin(connectionGraph);
        } else {
            JOptionPane.showMessageDialog(null,"ACESSO NEGADO!!");
            menu(connectionGraph);
        }
    }

    private static void menuCliente(org.neo4j.driver.Driver connectionGraph, Conta conta) throws Exception {
        int op = 0;

        do {

            TipoConta tipoConta = null;
            for (TipoConta tipo : TipoConta.values()) {
                if (tipo.getCod() == conta.getTipo()) {
                    tipoConta = tipo;
                    break;
                }
            }
            System.out.println("Olá " + clienteService.getById(connectionGraph, conta.getCliente()).getNome() + "\n" +
                    "NÚMERO DA CONTA: " + conta.getNumero() + " | SALDO: " + conta.getSaldo() + " | TIPO: " + tipoConta.getDescricao());
            System.out.println("_______________________________ \n O que deseja fazer?\n\n" +
                    "01 - Sacar\n" +
                    "02 - Depositar\n" +
                    "03 - Transferências\n" +
                    "04 - Consultar extrato\n" +
                    "05 - Atualizar seus dados\n" +
                    "\n\nDigite qualquer outro valor para sair\nSua opção: \n");
            op = input.nextInt();
            switch (op){
                case 1: movimentacaoService.saca(connectionGraph, conta);
                    break;
                case 2: movimentacaoService.deposito(connectionGraph, conta);
                    break;
                case 3: movimentacaoService.transferencia(connectionGraph, conta);
                    break;
                case 4: movimentacaoService.extrato(connectionGraph, conta);
                    break;
                case 5: clienteService.update(connectionGraph, conta);
                    break;
            }
        } while(op>0 && op<6);

    }

    private static void menuAdmin(Driver connectionGraph) throws Exception {
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
                            "10 - Relatório: Listar agência por cidade\n" +
                            "11 - Relatório: Cliente por cidade" +
                            "\n\nDigite qualquer outro valor para sair\nSua opção: \n");
            op = input.nextInt();
            switch (op){
                case 1: clienteService.create(connectionGraph);
                    break;
                case 2: contaService.create(connectionGraph);
                    break;
                case 3: contaService.delete(connectionGraph);
                    break;
                case 4: agenciaService.create(connectionGraph);
                    break;
                case 5: cidadeService.cadastrar(connectionGraph);
                    break;
                case 6: cidadeService.delete(connectionGraph);
                    break;
                case 7: agenciaService.delete(connectionGraph);
                    break;
                case 8: clienteService.delete(connectionGraph);
                    break;
                case 9: agenciaService.update(connectionGraph);
                    break;
                case 10: agenciaService.listByCidade(connectionGraph);
                    break;
                case 11: clienteService.listByCidade(connectionGraph);
                    break;
            }
        } while(op>0 && op<12);
    }

}
