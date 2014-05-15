package View;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.w3c.dom.CDATASection;

import Controller.GerenciaAeronave;
import Model.Aeronave;

public class TelaAeronave extends JFrame implements ActionListener {
	private JTextField tfCodigo, tfNome, tfQntAssentos;
	private JButton btnCadastrar, btnConsultar, btnAlterar, btnExcluir;
	private JComboBox cbTipoAeronave;
	private ResourceBundle oBn;
	private Aeronave aero;
	boolean consultou = false;

	public TelaAeronave(ResourceBundle bn) {
		super(bn.getString("FrmCadastrarAeronave.titulo"));

		this.oBn = bn;

		Container tela = getContentPane();
		setLayout(new BorderLayout(4, 4));

		JPanel painelLabel = new JPanel(new GridLayout(4, 1, 4, 4));

		JLabel lCodigo = new JLabel(bn.getString("rotulo.codigo"));
		painelLabel.add(lCodigo);

		JLabel lNome = new JLabel(bn.getString("rotulo.nome"));
		painelLabel.add(lNome);

		JLabel lQntAssentos = new JLabel(bn.getString("rotulo.qntAssentos"));
		painelLabel.add(lQntAssentos);

		JLabel lTipoAeronave = new JLabel(bn.getString("rotulo.tipoAeronave"));
		painelLabel.add(lTipoAeronave);

		JPanel painelDados = new JPanel(new GridLayout(4, 1, 4, 4));

		tfCodigo = new JTextField(12);
		painelDados.add(tfCodigo);

		tfNome = new JTextField(12);
		painelDados.add(tfNome);

		tfQntAssentos = new JTextField(12);
		painelDados.add(tfQntAssentos);

		String sTipoAeronave[] = { oBn.getString("tipoAeronave.comercial"),
				oBn.getString("tipoAeronave.luxo") };

		// Necessário implementar Bundle
		cbTipoAeronave = new JComboBox(sTipoAeronave);
		painelDados.add(cbTipoAeronave);

		JPanel painelBotoes = new JPanel(new GridLayout(1, 4, 4, 4));

		btnCadastrar = new JButton(bn.getString("cadastrar"));
		btnCadastrar.setMnemonic(bn.getString("cadastrar.mnemonic").charAt(0));
		btnCadastrar.addActionListener(this);
		painelBotoes.add(btnCadastrar);

		btnConsultar = new JButton(bn.getString("consultar"));
		btnConsultar.setMnemonic(bn.getString("consultar.mnemonic").charAt(0));
		btnConsultar.addActionListener(this);
		painelBotoes.add(btnConsultar);

		btnAlterar = new JButton(bn.getString("alterar"));
		btnAlterar.setMnemonic(bn.getString("alterar.mnemonic").charAt(0));
		btnAlterar.addActionListener(this);
		painelBotoes.add(btnAlterar);

		btnExcluir = new JButton(bn.getString("excluir"));
		btnExcluir.setMnemonic(bn.getString("excluir.mnemonic").charAt(0));
		btnExcluir.addActionListener(this);
		painelBotoes.add(btnExcluir);

		tela.add(painelLabel, BorderLayout.WEST);
		tela.add(painelDados, BorderLayout.CENTER);
		tela.add(painelBotoes, BorderLayout.SOUTH);

	}

	public void actionPerformed(ActionEvent evento) {
		Interfaces oInterface = new Interfaces();
		Validacoes oValida = new Validacoes();
		String sCod, sNome, sQntAssentos;
		if (evento.getSource() == btnCadastrar) {
			sCod = tfCodigo.getText();
			sNome = tfNome.getText();
			sQntAssentos = tfQntAssentos.getText();
			try {
				if (!(oValida.camposEmBranco(sCod, sNome, sQntAssentos))) {
					GerenciaAeronave oGA = new GerenciaAeronave();
					oGA.incluirAeronave(Integer.parseInt(tfCodigo
							.getText()), cbTipoAeronave.getSelectedIndex(),
							Integer.parseInt(tfQntAssentos.getText()),
							tfNome.getText());
					
					oInterface.mensagemInformacao(
							oBn.getString("mensagem.cadastrar.exito"),
							oBn.getString("cadastrar"));
					limparTextFields();
				} else {
					oInterface.mensagemAviso(
							oBn.getString("mensage.campos.branco"),
							oBn.getString("FrmMensagem.titulo.atencao"));
				}
			} catch (NumberFormatException e) {
				// JOptionPane.showMessageDialog(null, e.getStackTrace());
				oInterface.mensagemErro(
						oBn.getString("mensagem.cadastrar.erro"),
						oBn.getString("FrmMensagem.titulo.atencao"));
			} catch (SQLException e) {
				// JOptionPane.showMessageDialog(null, e.getStackTrace());
				oInterface.mensagemErro(
						oBn.getString("mensagem.cadastrar.erro"),
						oBn.getString("FrmMensagem.titulo.atencao"));
			}
		} else if (evento.getSource() == btnConsultar) {
			try {
				sCod = tfCodigo.getText();
				if (!(oValida.camposEmBranco(sCod))) {
					GerenciaAeronave oGA = new GerenciaAeronave();
					//if (!oGA.consultarAeronave(Integer.parseInt(tfCodigo.getText()))) {
					aero = oGA.consultarAeronave(Integer.parseInt(tfCodigo.getText()));	
					recarregarCampos();
						consultou = true;
					//} else {
						oInterface
								.mensagemInformacao(
										oBn.getString("mensagem.consulta.dado.nao.encontrado"),
										oBn.getString("FrmMensagem.titulo.atencao"));
					//}
				} else {
					oInterface.mensagemAviso(
							oBn.getString("mensage.campos.branco"),
							oBn.getString("FrmMensagem.titulo.atencao"));
				}
			} catch (NumberFormatException e) {
				// JOptionPane.showMessageDialog(null,e.getStackTrace());
				e.printStackTrace();
			} catch (SQLException e) {
				// JOptionPane.showMessageDialog(null,e.getStackTrace());
				e.printStackTrace();
			}
		} else if (consultou) {
			if (evento.getSource() == btnAlterar) {
				try {
					sCod = tfCodigo.getText();
					sNome = tfNome.getText();
					sQntAssentos = tfQntAssentos.getText();
					if (!(oValida.camposEmBranco(sCod, sNome, sQntAssentos))) {
						GerenciaAeronave oGA = new GerenciaAeronave();
						oGA.alterarAeronave(Integer.parseInt(tfCodigo.getText()),
								cbTipoAeronave.getSelectedIndex(),
								Integer.parseInt(tfQntAssentos.getText()),
								tfNome.getText());
						
						oInterface.mensagemInformacao(
								oBn.getString("mensagem.alterar.exito"),
								oBn.getString("alterar"));
						btnConsultar.doClick();
					} else {
						oInterface.mensagemAviso(
								oBn.getString("mensage.campos.branco"),
								oBn.getString("FrmMensagem.titulo.atencao"));
					}
				} catch (NumberFormatException e) {
					// JOptionPane.showMessageDialog(null, e.getStackTrace());
					oInterface.mensagemErro(
							oBn.getString("mensagem.alterar.erro"),
							oBn.getString("FrmMensagem.titulo.atencao"));
				} catch (SQLException e) {
					// JOptionPane.showMessageDialog(null, e.getStackTrace());
					oInterface.mensagemErro(
							oBn.getString("mensagem.alterar.erro"),
							oBn.getString("FrmMensagem.titulo.atencao"));
				}
			}
			if (evento.getSource() == btnExcluir) {
				try {
					GerenciaAeronave oGA = new GerenciaAeronave();
					oGA.excluirAeronave(Integer.parseInt(tfCodigo.getText()),
							cbTipoAeronave.getSelectedIndex(),
							Integer.parseInt(tfQntAssentos.getText()),
							tfNome.getText());
					
					oInterface.mensagemInformacao(
							oBn.getString("mensagem.excluir.exito"),
							oBn.getString("excluir"));
					limparTextFields();
				} catch (NumberFormatException e) {
					// JOptionPane.showMessageDialog(null, e.getStackTrace());
					oInterface.mensagemErro(
							oBn.getString("mensagem.excluir.erro"),
							oBn.getString("FrmMensagem.titulo.atencao"));
				} catch (SQLException e) {
					// JOptionPane.showMessageDialog(null, e.getStackTrace());
					oInterface.mensagemErro(
							oBn.getString("mensagem.excluir.erro"),
							oBn.getString("FrmMensagem.titulo.atencao"));
				}
			}
		} else {
			oInterface.mensagemErro(oBn.getString("mensagem.efetuar.consulta"),
					oBn.getString("FrmMensagem.titulo.atencao"));
		}

	}

	public void recarregarCampos() {
		tfCodigo.setText("" + aero.getCodigoAeronave());
		tfNome.setText("" + aero.getNome());
		tfQntAssentos.setText("" + aero.getQtdAssentos());
		cbTipoAeronave.setSelectedIndex(aero.getTipoAeronave());
	}

	public void bloquearBotoes(String itemEscolhido) {
		if (itemEscolhido.equals("Cadastrar")) {
			btnConsultar.setEnabled(false);
			btnAlterar.setEnabled(false);
			btnExcluir.setEnabled(false);
		} else if (itemEscolhido.equals("Consultar")) {
			btnCadastrar.setEnabled(false);
			btnAlterar.setEnabled(false);
			btnExcluir.setEnabled(false);
			tfNome.enable(false);
			tfQntAssentos.enable(false);
			cbTipoAeronave.enable(false);
		} else if (itemEscolhido.equals("Alterar")) {
			btnCadastrar.setEnabled(false);
			btnExcluir.setEnabled(false);
		} else {
			btnCadastrar.setEnabled(false);
			btnAlterar.setEnabled(false);
		}
	}

	public void limparTextFields() {
		tfCodigo.setText("");
		tfNome.setText("");
		tfQntAssentos.setText("");
	}

}
