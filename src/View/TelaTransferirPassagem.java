package View;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.GerenciaPassagem;

import com.toedter.calendar.JDateChooser;

public class TelaTransferirPassagem extends JFrame implements ActionListener {
	private JTextField tfNumPass;
	private JDateChooser dcData;
	private JButton btnVerifica, btnTransferir;
	private ResourceBundle bn;

	public TelaTransferirPassagem(ResourceBundle bn) {
		super(bn.getString("FrmTransferirPassagem.titulo"));
		Container tela = getContentPane();
		setLayout(new BorderLayout(4, 4));
		this.bn = bn;

		JPanel painelLabel = new JPanel(new GridLayout(2, 1, 4, 4));
		JLabel lNumPass = new JLabel(bn.getString("rotulo.num.passagem"));
		painelLabel.add(lNumPass);

		JLabel lData = new JLabel(bn.getString("rotulo.data"));
		painelLabel.add(lData);

		JPanel painelDados = new JPanel(new GridLayout(2, 1, 4, 4));

		tfNumPass = new JTextField(12);
		painelDados.add(tfNumPass);

		dcData = new JDateChooser();
		painelDados.add(dcData);

		JPanel painelBotoes = new JPanel(new GridLayout(1, 2, 4, 4));

		btnVerifica = new JButton(bn.getString("verificar.passagem"));
		btnVerifica.setMnemonic(bn.getString("verificar.passagem.mnemonic")
				.charAt(0));
		btnVerifica.addActionListener(this);
		painelBotoes.add(btnVerifica);

		btnTransferir = new JButton(bn.getString("transferir"));
		btnTransferir
				.setMnemonic(bn.getString("transferir.mnemonic").charAt(0));
		btnTransferir.addActionListener(this);
		painelBotoes.add(btnTransferir);

		tela.add(painelLabel, BorderLayout.WEST);
		tela.add(painelDados, BorderLayout.CENTER);
		tela.add(painelBotoes, BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent evento) {
		Interfaces oInterface = new Interfaces();
		if (evento.getSource() == btnVerifica) {
			Validacoes oValida = new Validacoes();
			String sNumPassagem = tfNumPass.getText();
			if (!(oValida.camposEmBranco(sNumPassagem))) {
				GerenciaPassagem oGPassagem = new GerenciaPassagem();

				oGPassagem.transferirPassagem();
			} else {
				oInterface
						.mensagemAviso(
								bn.getString("mensagem.passagem.transferir.fora.prazo"),
								bn.getString("FrmMensagem.titulo.atencao"));
			}
		} else {
			oInterface.mensagemAviso(bn.getString("mensage.campos.branco"),
					bn.getString("FrmMensagem.titulo.atencao"));
		}

		if (evento.getSource() == btnTransferir) {

		}
	}
}
