package core.utils;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import com.toedter.calendar.JCalendar;

public class ExemploJCalendar extends JDialog implements ActionListener {
    private JPanel painel;
    private JCalendar calendario;
    private JButton btnSelecionarData;
    private Date dataSelecionada;

    public ExemploJCalendar(String title) {
        setTitle(title);
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        painel = new JPanel(new BorderLayout());
        calendario = new JCalendar();
        btnSelecionarData = new JButton("Selecionar data");
        btnSelecionarData.addActionListener(this);

        painel.add(calendario, BorderLayout.CENTER);
        painel.add(btnSelecionarData, BorderLayout.SOUTH);
        setContentPane(painel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btnSelecionarData) {
            dataSelecionada = calendario.getDate();
            dispose();
        }
    }

    public Date getDateSelecionada() {
        return dataSelecionada;
    }
}
