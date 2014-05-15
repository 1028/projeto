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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Controller.LerArquivo;

public class TelaLogin extends JFrame implements ActionListener {
	private JButton bntConfirmar;
	private JLabel lLogin, lSenha;
	private JTextField tfLogin;
	private JPasswordField pfSenha;
	private ResourceBundle bn;

	public TelaLogin(ResourceBundle bn) {
		super(bn.getString("FrmLogin.titulo"));
		Container tela = getContentPane();
		setLayout(new BorderLayout(4, 4));
		this.bn = bn;

		lLogin = new JLabel(bn.getString("FrmLogin.rotulo.login"));
		lSenha = new JLabel(bn.getString("FrmLogin.rotulo.senha"));

		tfLogin = new JTextField(12);
		pfSenha = new JPasswordField(12);

		bntConfirmar = new JButton(bn.getString("botao.confirmar"));
		bntConfirmar.setMnemonic(bn.getString("botao.confirmar.mnemonic")
				.charAt(0));
		bntConfirmar.addActionListener(this);

		JPanel paineLabel = new JPanel(new GridLayout(2, 1));
		paineLabel.add(lLogin);
		paineLabel.add(lSenha);

		// GridLayout campos = new GridLayout(2, 2);
		JPanel painelTextField = new JPanel(new GridLayout(2, 1, 0, 4));
		painelTextField.add(tfLogin);
		painelTextField.add(pfSenha);

		tela.add(paineLabel, BorderLayout.WEST);
		tela.add(painelTextField, BorderLayout.CENTER);
		tela.add(bntConfirmar, BorderLayout.SOUTH);

	}

	public void actionPerformed(ActionEvent evento) {
		if (evento.getSource() == bntConfirmar) {
			Validacoes oValida = new Validacoes();
			Interfaces oInterface = new Interfaces();
			String sLogin = "" + tfLogin.getText();
			String sSenha = "" + pfSenha.getText();

			if (!(oValida.camposEmBranco(sLogin, sSenha))) {
				LerArquivo oLer = new LerArquivo();
				try {
					int tipoUsuario = oLer.verificaArquivo(sLogin, sSenha);
					if (tipoUsuario >= 0) {
						oInterface.mensagemInformacao(
								bn.getString("FrmLogin.mensagem.validado"),
								bn.getString("FrmMensagem.titulo.atencao"));
						TelaSistemaPassagensAereas oSpa = new TelaSistemaPassagensAereas(
								bn, tipoUsuario);
						oSpa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						oSpa.setSize(500, 500);
						oSpa.setVisible(true);
						oSpa.setResizable(false);
						oSpa.setLocationRelativeTo(null);
						dispose();
					} else {
						oInterface
								.mensagemErro(
										bn.getString("mensagem.usuario.nao.encontrado"),
										bn.getString("FrmMensagem.titulo.atencao"));
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null,
						bn.getString("mensage.campos.branco"),
						bn.getString("FrmMensagem.titulo.atencao"),
						JOptionPane.WARNING_MESSAGE);
			}

		}
	}
}
