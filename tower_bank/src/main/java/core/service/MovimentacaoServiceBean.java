package core.service;

import core.dao.ContaDAO;
import core.dao.MovimentacaoDAO;
import core.exception.CampoInvalidoExceptions;
import core.exception.ContaNotFoundException;
import core.exception.SaldoInvalidoException;
import entity.Conta;
import entity.Movimentacao;
import entity.OperacaoCaixaEletronico;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.Objects;


public class MovimentacaoServiceBean implements MovimentacaoService{

    static ContaService contaService = new ContaServiceBean();

    @Override
    public void pagamento(Connection con, Conta conta) throws Exception{
        System.out.println("Digite o código do boleto\n Utilize o site: https://devtools.com.br/gerador-boleto/ [para"+
                "para gerar seu boleto]");
    }

    @Override
    public void deposito(Connection con, Conta conta)  throws Exception{

        Double saldo = conta.getSaldo();
        Double valor = Double.valueOf(JOptionPane.showInputDialog("Digite o valor que gostaria de depositar: "));

        if(valor < 0){
            JOptionPane.showMessageDialog(null, "Não é possível depositar valores negativos");
            throw new CampoInvalidoExceptions("Não é possível depositar valores negativos");
        }

        conta.setSaldo(saldo+valor);
        ContaDAO.updateSaldo(con, conta);
        //Registrar transação
        String descricao = "Deposito de R$ " + valor + " foi realizado";
        Movimentacao movimentacao = new Movimentacao(conta.getId(), OperacaoCaixaEletronico.DEPOSITO.getCod(),
                conta.getId(), conta.getAgencia(), new Date(System.currentTimeMillis()), valor, descricao);
        MovimentacaoDAO.insert(con, movimentacao);

    }

    @Override
    public void saca(Connection con, Conta conta) throws Exception {

        Double saldo = conta.getSaldo();
        Double valor = Double.valueOf(JOptionPane.showInputDialog("Digite o valor que gostaria de sacar: "));

        validaValor(conta.getSaldo(), valor);

        if(valor < 0){
            JOptionPane.showMessageDialog(null, "Possível fraude detectada. Faça login novamente!");
            throw new CampoInvalidoExceptions("Não tente burlar nosso algoritmo, estamos de olho");
        }

        conta.setSaldo(saldo-valor);
        ContaDAO.updateSaldo(con, conta);

        //Registrar transação
        String descricao = "Saque de R$ " + valor + " foi realizado";
        Movimentacao movimentacao = new Movimentacao(conta.getId(), OperacaoCaixaEletronico.SAQUE.getCod(),
                null, conta.getAgencia(), new Date(System.currentTimeMillis()), valor, descricao);
        MovimentacaoDAO.insert(con, movimentacao);
    }

    @Override
    public void transferencia(Connection con, Conta conta) throws Exception{
        Double valor = Double.valueOf(JOptionPane.showInputDialog("Qual valor deseja transferir? "));
        validaValor(conta.getSaldo(), valor);

        Integer numberContaDestino = Integer.valueOf(JOptionPane.showInputDialog("Informe o destinatário (número da conta"));
        Conta contaDestino = contaService.getByNumber(con, numberContaDestino);

        if(Objects.isNull(contaDestino)){
            JOptionPane.showMessageDialog(null, "Conta informada não existe");
            throw new ContaNotFoundException("Conta não encontrada com numero: " + numberContaDestino);
        }

        conta.setSaldo(conta.getSaldo()-valor);
        contaDestino.setSaldo(contaDestino.getSaldo()+valor);
        ContaDAO.updateSaldo(con, conta);
        ContaDAO.updateSaldo(con, contaDestino);

        String descricao = "Transferência de R$ " + valor + " foi realizada";
        Movimentacao movimentacao = new Movimentacao(conta.getId(), OperacaoCaixaEletronico.TRANSFERENCIA.getCod(),
                contaDestino.getId(), conta.getAgencia(), new Date(System.currentTimeMillis()), valor, descricao);
        MovimentacaoDAO.insert(con, movimentacao);
    }

    @Override
    public List<Movimentacao> extrato(Connection con, Conta conta) throws Exception{
        return null;
    }

    @Override
    public Movimentacao comprovamte(Connection con, Conta conta) throws Exception{
        return null;
    }

    private void validaValor(Double saldo, Double valor) throws SaldoInvalidoException {
        if (saldo < valor){
            JOptionPane.showMessageDialog(null, "Saldo Insuficiente");
            throw new SaldoInvalidoException("Saldo insufieinte");
        }
    }
}
