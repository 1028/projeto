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

public class TelaEfetuarCheckin extends JFrame implements ActionListener {
	private JTextField tfNumPass;
	private JLabel lDadoFormaTrat, lDadoNome, lDadoSobrenome, lDadoDataNasc,
			lDadoTipoPass;
	private JButton btnConfirmar, btnAssento;
	private ResourceBundle bnInterno;

	public TelaEfetuarCheckin(ResourceBundle bn) {
		super(bn.getString("FrmEfetuarCheckin.titulo"));
		Container tela = getContentPane();
		setLayout(new BorderLayout(4, 4));

		bnInterno = bn;

		// Instancia JPanel que abriga o label e text field do Número da
		// passagem.
		JPanel painelPassagem = new JPanel(new GridLayout(1, 2, 4, 4));
		JLabel lNumPassagem = new JLabel(bn.getString("rotulo.num.passagem"));
		painelPassagem.add(lNumPassagem);

		tfNumPass = new JTextField();
		painelPassagem.add(tfNumPass);

		// Instância JPanel que abriga os label sobre o passageiro.
		JPanel painelLabel = new JPanel(new GridLayout(5, 1, 4, 4));

		JLabel lFormaTrat = new JLabel(bn.getString("rotulo.forma.trat"));
		painelLabel.add(lFormaTrat);

		JLabel lNome = new JLabel(bn.getString("rotulo.nome"));
		painelLabel.add(lNome);

		JLabel lSobrenome = new JLabel(bn.getString("rotulo.sobrenome"));
		painelLabel.add(lSobrenome);

		JLabel lDataNasc = new JLabel(bn.getString("rotulo.data.nasc"));
		painelLabel.add(lDataNasc);

		JLabel lTipoPass = new JLabel(bn.getString("rotulo.tipo.pass"));
		painelLabel.add(lTipoPass);

		// Instância JPanel que abriga os label que mostrar os dados sobre o
		// passageiro.
		JPanel painelLabelDados = new JPanel(new GridLayout(5, 1, 4, 4));

		lDadoFormaTrat = new JLabel("");
		painelLabelDados.add(lDadoFormaTrat);

		lDadoNome = new JLabel("");
		painelLabelDados.add(lDadoNome);

		lDadoSobrenome = new JLabel("");
		painelLabelDados.add(lDadoSobrenome);

		lDadoDataNasc = new JLabel("");
		painelLabelDados.add(lDadoDataNasc);

		lDadoTipoPass = new JLabel("");
		painelLabelDados.add(lDadoTipoPass);

		// Instância o JButton para exibição da tela dos assentos
		btnAssento = new JButton(bn.getString("botao.exibe.assento"));
		btnAssento.addActionListener(this);

		// Instância JButton de confirmação para a realização de Check-in.
		btnConfirmar = new JButton(bn.getString("botao.confirmar"));
		btnConfirmar.setMnemonic(bn.getString("botao.confirmar.mnemonic")
				.charAt(0));
		btnConfirmar.addActionListener(this);

		// Painel dos botões
		JPanel btnPanel = new JPanel(new GridLayout(2, 1, 4, 4));
		btnPanel.add(btnAssento);
		btnPanel.add(btnConfirmar);

		// Adiciona os objetos no Container.
		tela.add(painelPassagem, BorderLayout.NORTH);
		tela.add(painelLabel, BorderLayout.WEST);
		tela.add(painelLabelDados, BorderLayout.CENTER);
		tela.add(btnPanel, BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent evento) {
		if (evento.getSource() == btnConfirmar) {
			String sNumPassagem = tfNumPass.getText();
			Validacoes oValida = new Validacoes();
			if (!(oValida.camposEmBranco(sNumPassagem))) {

			} else {

			}
		}
		if (evento.getSource() == btnAssento) {
			TelaDialogoAssentos dia = new TelaDialogoAssentos(1, this);

		}
	}
}
