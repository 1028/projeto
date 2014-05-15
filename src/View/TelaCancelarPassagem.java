package View;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.GerenciaPassagem;

public class TelaCancelarPassagem extends JFrame implements ActionListener {
	private JTextField tfNumPass;
	private JLabel lQntDevolver;
	private JButton btnVerifica, btnCancelar;
	private ResourceBundle bn;

	public TelaCancelarPassagem(ResourceBundle bn) {
		super(bn.getString("FrmCancelarPassagem.titulo"));
		Container tela = getContentPane();
		setLayout(new BorderLayout(4, 4));
		this.bn = bn;

		JPanel painelDevolucao = new JPanel(new GridLayout(2, 2, 4, 4));

		JLabel lNumPassagem = new JLabel(bn.getString("rotulo.num.passagem"));
		painelDevolucao.add(lNumPassagem);

		tfNumPass = new JTextField("tfNumPass");
		painelDevolucao.add(tfNumPass);

		JLabel lValor = new JLabel(
				bn.getString("FrmCancelarPassagem.rotulo.valor"));
		painelDevolucao.add(lValor);

		lQntDevolver = new JLabel("0,00");
		painelDevolucao.add(lQntDevolver);

		JPanel painelBotoes = new JPanel(new GridLayout(1, 2, 4, 4));

		btnVerifica = new JButton(bn.getString("verificar.passagem"));
		btnVerifica.setMnemonic(bn.getString("verificar.passagem.mnemonic")
				.charAt(0));
		btnVerifica.addActionListener(this);

		btnCancelar = new JButton(
				bn.getString("FrmCancelarPassagem.botao.cancelar"));
		btnCancelar.setMnemonic(bn.getString(
				"FrmCancelarPassagem.botao.cancelar.mnemonic").charAt(0));
		btnCancelar.addActionListener(this);

		painelBotoes.add(btnVerifica);
		painelBotoes.add(btnCancelar);

		tela.add(painelDevolucao, BorderLayout.CENTER);
		tela.add(painelBotoes, BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent evento) {
		Validacoes oValida = new Validacoes();
		Interfaces oInterface = new Interfaces();
		DecimalFormat formataDecimal = new DecimalFormat("0.00");
		if (evento.getSource() == btnVerifica) {
			String sNumPassagem = tfNumPass.getText();
			if (!(oValida.camposEmBranco(sNumPassagem))) {
				int iNumPassagem = Integer.parseInt(sNumPassagem);
				Date dDataPassagem;

				GerenciaPassagem oGpassagem = new GerenciaPassagem();

				double devolução = oGpassagem.calculaDevolucao(iNumPassagem);
				if (devolução >= 0.0) {
					lQntDevolver.setText("" + formataDecimal.format(devolução));
				} else {
					oInterface
							.mensagemAviso(
									bn.getString("mensagem.passagem.cancelar.fora.prazo"),
									bn.getString("FrmMensagem.titulo.atencao"));
				}
			} else {
				oInterface.mensagemAviso(bn.getString("mensage.campos.branco"),
						bn.getString("FrmMensagem.titulo.atencao"));
			}
		}

		if (evento.getSource() == btnCancelar) {

		}
	}
}
