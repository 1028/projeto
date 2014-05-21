package View;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.GerenciaPassageiro;
import Controller.GerenciaPassagem;

import com.toedter.calendar.JDateChooser;

public class TelaCadastrarPassageiro extends JFrame implements ActionListener {
	// private String vetFormaTrat[], vetTipoPass[];
	private JComboBox cbFormaTrat, cbTipoPass;
	private JTextField tfNome, tfSobrenome, tfEmail, tfTelefone;
	private JDateChooser dcDataNasc;
	private JButton btnCadastrar;
	private ResourceBundle bn;
	private ArrayList<Perfil> arrPerfil;
	private ArrayList<FormaTratamento> arrFormaTrata;

	public TelaCadastrarPassageiro(ResourceBundle bn) throws SQLException {
		super(bn.getString("FrmCadastrarPassageiro.titulo"));
		Container tela = getContentPane();
		setLayout(new BorderLayout(4, 4));
		this.bn = bn;
		String vetFormaTrat[] = { "Sr.", "Sra." };
		String vetTipoPass[] = { "Adulto", "Criança" };

		JPanel painelLabel = new JPanel(new GridLayout(7, 1, 4, 4));

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

		JLabel lEmail = new JLabel(bn.getString("rotulo.email"));
		painelLabel.add(lEmail);

		JLabel lTelefone = new JLabel(bn.getString("rotulo.telefone"));
		painelLabel.add(lTelefone);

		JPanel painelDados = new JPanel(new GridLayout(7, 1, 4, 4));

		FormaTratamento oTratamento = new FormaTratamento();

		ArrayList auxFormaTrata = oTratamento.consultarTodos();
		arrFormaTrata = new ArrayList<FormaTratamento>();

		for (int i = 0; i < auxFormaTrata.size(); i += 2) {
			// ìndices são invertidos pois na instanciação
			String nome = "forma.trat." + (String) auxFormaTrata.get(i + 1);
			oTratamento = new FormaTratamento(bn.getString(nome),
					(Integer) auxFormaTrata.get(i));

			arrFormaTrata.add(oTratamento);
		}

		DefaultComboBoxModel modeloFormaTrata = new DefaultComboBoxModel(
				arrFormaTrata.toArray());

		cbFormaTrat = new JComboBox(modeloFormaTrata);
		painelDados.add(cbFormaTrat);

		tfNome = new JTextField(12);
		painelDados.add(tfNome);

		tfSobrenome = new JTextField(12);
		painelDados.add(tfSobrenome);

		dcDataNasc = new JDateChooser();
		painelDados.add(dcDataNasc);

		Perfil oPerfil = new Perfil();

		ArrayList auxPerfil = oTratamento.consultarTodos();
		arrPerfil = new ArrayList<Perfil>();

		for (int i = 0; i < auxPerfil.size(); i += 2) {
			// ìndices são invertidos pois na instanciação
			String nome = "tipo.pass." + (String) auxPerfil.get(i + 1);
			oPerfil = new Perfil(bn.getString(nome), (Integer) auxPerfil.get(i));

			arrPerfil.add(oPerfil);
		}

		DefaultComboBoxModel modeloPerfil = new DefaultComboBoxModel(
				arrPerfil.toArray());

		cbTipoPass = new JComboBox(modeloPerfil);
		painelDados.add(cbTipoPass);

		tfEmail = new JTextField(12);
		painelDados.add(tfEmail);

		tfTelefone = new JTextField(12);
		painelDados.add(tfTelefone);

		btnCadastrar = new JButton(bn.getString("cadastrar"));
		btnCadastrar.setMnemonic(bn.getString("cadastrar.mnemonic").charAt(0));
		btnCadastrar.addActionListener(this);

		tela.add(painelLabel, BorderLayout.WEST);
		tela.add(painelDados, BorderLayout.CENTER);
		tela.add(btnCadastrar, BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent evento) {
		Interfaces oInterface = new Interfaces();
		if (evento.getSource() == btnCadastrar) {
			Validacoes oValida = new Validacoes();
			String sNome, sSobrenome, sEmail, sTelefone;
			sNome = tfNome.getText();
			sSobrenome = tfSobrenome.getText();
			sEmail = tfEmail.getText();
			sTelefone = tfTelefone.getText();
			Date dData = dcDataNasc.getDate();
			// System.out.print(dData);

			if (!(oValida.camposEmBranco(sNome, sSobrenome, sEmail, sTelefone) || oValida
					.camposEmBranco(dData))) {

				TelaComprarPassagem oComPass = new TelaComprarPassagem(bn);
				oComPass.setSize(420, 260);
				oComPass.setVisible(true);
				oComPass.setResizable(false);
				oComPass.setLocationRelativeTo(null);

				DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

				GerenciaPassageiro oGPassageiro = new GerenciaPassageiro();

				oGPassageiro.cadastrarPassageiro(sNome, sSobrenome, sTelefone,
						sEmail, format.format(dData),
						(cbFormaTrat.getSelectedIndex() + 1),
						(cbTipoPass.getSelectedIndex() + 1));

				oInterface.mensagemInformacao(
						bn.getString("mensagem.cadastrar.exito"),
						bn.getString("FrmMensagem.titulo.atencao"));
				limpar();
			} else {
				oInterface.mensagemAviso(bn.getString("mensage.campos.branco"),
						bn.getString("FrmMensagem.titulo.atencao"));
			}

		}
	}

	private void limpar() {
		tfNome.setText("");
		tfSobrenome.setText("");
		tfEmail.setText("");
		tfTelefone.setText("");
		dcDataNasc.setDate(null);
		cbFormaTrat.setSelectedIndex(0);
		cbTipoPass.setSelectedIndex(0);
	}
}
