package View;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.MaskFormatter;

import com.toedter.calendar.JDateChooser;

public class TelaVoo extends JFrame implements ActionListener {
	private JTextField tfCodigo;
	private JFormattedTextField tfValor;
	private MaskFormatter formatoValor;
	private JDateChooser dcData;
	private String vetSituacao[], vetAeronave[];
	private JComboBox cbSituacao, cbAeronave, cbLocalidade, cbOrigem,
			cbDestino, cbEscala;
	private JButton btnCadastrar, btnConsultar, btnAlterar, btnExcluir;
	private JTable tblResultado;
	private ResourceBundle bn;
	private Voo oVoo;
	private boolean consultou = false;
	private ArrayList<Aeronave> arrAero;
	private ArrayList<Situacao> arrSit;
	private ArrayList<Voo> arrVoo;
	private ArrayList<Localidade> arrLoca;
	private DefaultComboBoxModel modeloLoca;
	private DefaultComboBoxModel modelo;

	public TelaVoo(ResourceBundle bn) throws ParseException, SQLException {
		super(bn.getString("FrmConsultarVoo.titulo"));
		Container tela = getContentPane();
		setLayout(new BorderLayout(4, 4));
		this.bn = bn;

		arrVoo = new ArrayList<Voo>();

		JPanel painelLabel = new JPanel(new GridLayout(8, 1, 4, 4));

		JLabel lCodigo = new JLabel(bn.getString("rotulo.codigo"));
		painelLabel.add(lCodigo);

		JLabel lOrigem = new JLabel(bn.getString("rotulo.origem"));
		painelLabel.add(lOrigem);

		JLabel lDestino = new JLabel(bn.getString("rotulo.destino"));
		painelLabel.add(lDestino);

		JLabel lEscala = new JLabel(bn.getString("rotulo.escalas"));
		painelLabel.add(lEscala);

		JLabel lValor = new JLabel(bn.getString("rotulo.valor"));
		painelLabel.add(lValor);

		JLabel lDataHora = new JLabel(bn.getString("rotulo.data.hora"));
		painelLabel.add(lDataHora);

		JLabel lTipoAeronave = new JLabel(bn.getString("rotulo.tipo.aeronave"));
		painelLabel.add(lTipoAeronave);

		JLabel lSituacao = new JLabel(bn.getString("rotulo.situacao"));
		painelLabel.add(lSituacao);

		JPanel painelDados = new JPanel(new GridLayout(8, 1, 4, 4));

		tfCodigo = new JTextField(12);
		painelDados.add(tfCodigo);

		// Criando array de Localidade
		Localidade loca = new Localidade();
		arrLoca = new ArrayList<Localidade>();

		ArrayList auxLoca = loca.consultarTodos();

		// Verifica se houve retorno da consulta na base
		if (auxLoca.size() > 0) {
			for (int i = 0; i < auxLoca.size(); i += 3) {
				loca = new Localidade((Integer) auxLoca.get(i),
						(String) auxLoca.get(i + 1),
						(String) auxLoca.get(i + 2));
				arrLoca.add(loca);
			}
		} else {
			JOptionPane.showMessageDialog(this,
					bn.getString("mensagem.consulta.dado.nao.encontrado"));
		}

		modeloLoca = new DefaultComboBoxModel(arrLoca.toArray());

		cbOrigem = new JComboBox(modeloLoca);
		painelDados.add(cbOrigem);

		modeloLoca = new DefaultComboBoxModel(arrLoca.toArray());

		cbDestino = new JComboBox(modeloLoca);
		painelDados.add(cbDestino);

		modeloLoca = new DefaultComboBoxModel(arrLoca.toArray());

		cbEscala = new JComboBox(modeloLoca);
		painelDados.add(cbEscala);

		formatoValor = new MaskFormatter("####.##");
		formatoValor.setPlaceholderCharacter('0');

		tfValor = new JFormattedTextField(formatoValor);
		tfValor.setHorizontalAlignment(JTextField.RIGHT);
		painelDados.add(tfValor);

		dcData = new JDateChooser();
		dcData.setDateFormatString(bn.getString("formato.data"));
		painelDados.add(dcData);

		// Populando o JComboBox de aeronave
		DefaultComboBoxModel modeloAeronave;

		Aeronave aero1 = new Aeronave();
		arrAero = new ArrayList<Aeronave>();

		ArrayList aux = aero1.consultarTodas();

		for (int i = 0; i < aux.size(); i += 4) {
			aero1 = new Aeronave((Integer) aux.get(i),
					(Integer) aux.get(i + 1), (Integer) aux.get(i + 3),
					(String) aux.get(i + 2));
			arrAero.add(aero1);
		}

		modeloAeronave = new DefaultComboBoxModel(arrAero.toArray());

		cbAeronave = new JComboBox(modeloAeronave);
		painelDados.add(cbAeronave);

		Situacao situacao = new Situacao();

		ArrayList auxSit = situacao.consultarTodos();
		arrSit = new ArrayList<Situacao>();

		for (int i = 0; i < auxSit.size(); i += 2) {
			// ìndices são invertidos pois na instanciação
			String nome = "voo.status." + (String) auxSit.get(i + 1);
			situacao = new Situacao(bn.getString(nome), (Integer) auxSit.get(i));

			arrSit.add(situacao);
		}

		// definindo um modelo para o JComboBox
		modelo = new DefaultComboBoxModel(arrSit.toArray());

		cbSituacao = new JComboBox(modelo);
		painelDados.add(cbSituacao);

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

		// cria JTable inicial com uma linha em branco
		
		tblResultado = new JTable();
		

		TableModel model = new DefaultTableModel(new String[] {
				bn.getString("rotulo.codigo"), bn.getString("rotulo.origem"),
				bn.getString("rotulo.destino"), bn.getString("rotulo.escalas"),
				bn.getString("rotulo.valor"), bn.getString("rotulo.data.hora"),
				bn.getString("rotulo.tipo.aeronave"),
				bn.getString("rotulo.situacao") }, // titulo das colunas
				0); // numero de linhas em branco
		tblResultado.setModel(model);
		tblResultado.setName("Resultado");
		JScrollPane resultado = new JScrollPane();
		resultado.setViewportView(tblResultado); // Sem o ViewPort o Grid fica
													// ruim

		tela.add(painelLabel, BorderLayout.WEST);
		tela.add(resultado, BorderLayout.EAST);
		tela.add(painelDados, BorderLayout.CENTER);
		tela.add(painelBotoes, BorderLayout.SOUTH);
	}

	public Localidade transformaLocalidade(String local) {
		Localidade retorno;
		String aux[] = local.split(" - ");

		int codigo = Integer.parseInt(aux[0]);
		String uf = aux[1];
		String nome = aux[2];

		return retorno = new Localidade(codigo, nome, uf);
	}

	public String formataValor(double value) {
		String retorno = "" + value;

		System.out.println("Valor em double " + value);
		System.out.println("Valor em retorno " + retorno);

		String aux = retorno.substring(retorno.length() - 1, retorno.length());

		System.out.println("Valor em aux " + aux);

		if (!(aux.equals("0"))) {
			for (int i = 7 - retorno.length(); i > 0; i--) {
				retorno = String.format("%d" + retorno, 0);
			}
		} else {
			retorno += "0";
		}

		System.out.println(retorno);

		return retorno;
	}
	
	// Método que preeenche o JTable com base no parametro do destino escolhido.
	public void consultarLocal(){
		
	}

	public void actionPerformed(ActionEvent evento) {
		Validacoes oValida = new Validacoes();
		Interfaces oInterface = new Interfaces();
		String sCod, sDestino, sEscala;
		java.util.Date dData;
		if (evento.getSource() == btnCadastrar) {
			sCod = tfCodigo.getText();
			// sDestino = tfDestino.getText();
			// sEscala = tfEscala.getText();
			dData = dcData.getDate();
			Object origem = cbOrigem.getSelectedItem();
			Object destino = cbDestino.getSelectedItem();
			Object escala = cbEscala.getSelectedItem();
			Object aeron = cbAeronave.getSelectedItem();

			if (!(oValida.camposEmBranco(sCod) || oValida.camposEmBranco(dData)
					|| oValida.camposEmBranco(origem)
					|| oValida.camposEmBranco(destino)
					|| oValida.camposEmBranco(escala) || oValida
						.camposEmBranco(aeron))) {

				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				Situacao sit = (Situacao) cbSituacao.getSelectedItem();

				Localidade localOri = (Localidade) cbOrigem.getSelectedItem();
				Localidade localDest = (Localidade) cbDestino.getSelectedItem();
				Localidade localEsc = (Localidade) cbEscala.getSelectedItem();

				oVoo = new Voo(localOri.toString(), localDest.toString(),
						localEsc.toString(), format.format(dcData.getDate()),
						Double.parseDouble(tfValor.getText()), sit.getCodigo());

				// Casting do JComboBox pegando a Aeronave selecionada
				Aeronave aeroSelecionada = (Aeronave) cbAeronave
						.getSelectedItem();

				try {
					oVoo.inserirVoo(1, aeroSelecionada.getCodigoAeronave());
					JOptionPane.showMessageDialog(this,
							bn.getString("mensagem.cadastrar.exito"));
					limparTextFields();
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			} else {
				JOptionPane.showMessageDialog(null,
						bn.getString("mensage.campos.branco"),
						bn.getString("FrmMensagem.titulo.atencao"),
						JOptionPane.WARNING_MESSAGE);
			}
		} else if (evento.getSource() == btnConsultar) {
			// Se for pesquisar por outro campo ou mais so mudar a validação
			sCod = tfCodigo.getText();

			if (!(oValida.camposEmBranco(sCod))) {
				int iCod = Integer.parseInt(sCod);

				oVoo = new Voo(iCod);

				int qtdItens = 0;

				try {
					qtdItens = oVoo.consultarVoo();

				} catch (SQLException e) {
					e.printStackTrace();
				}

				if (qtdItens > 0) {

					// Pega informações da Situação.
					ArrayList lista = oVoo.getRetorno();

					String nome = "voo.status." + (String) lista.get(8);

					Situacao sit = new Situacao(bn.getString(nome),
							(Integer) lista.get(7));

					modelo = (DefaultComboBoxModel) cbSituacao.getModel();
					modelo.setSelectedItem(sit);

					// Pega informações da Aeronave
					// codigo, tipo, qtdAssentos, nome
					Aeronave aero = null;

					try {
						aero = new Aeronave((Integer) lista.get(9),
								Integer.parseInt((String) lista.get(10)),
								(Integer) lista.get(12), (String) lista.get(11));
					} catch (SQLException e) {
						e.printStackTrace();
					}

					// Recarrega Aeronave
					modelo = (DefaultComboBoxModel) cbAeronave.getModel();
					modelo.setSelectedItem(aero);

					// origem
					Localidade aux = transformaLocalidade(oVoo.getOrigem());
					modelo = (DefaultComboBoxModel) cbOrigem.getModel();
					modelo.setSelectedItem(aux);

					// Destino
					aux = transformaLocalidade(oVoo.getDestino());
					modelo = (DefaultComboBoxModel) cbDestino.getModel();
					modelo.setSelectedItem(aux);

					// Escala
					aux = transformaLocalidade(oVoo.getEscala());
					modelo = (DefaultComboBoxModel) cbEscala.getModel();
					modelo.setSelectedItem(aux);

					tfValor.setText(formataValor(oVoo.getValor()));

					DateFormat formatoData = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");

					try {
						Date data = formatoData.parse(oVoo.getDateHora());
						dcData.setDate(data);
					} catch (Exception ex) {
						oInterface.mensagemAviso(ex.getMessage(), "Erro");
					}

					consultou = true;
				} else {
					oInterface
							.mensagemAviso(
									bn.getString("mensagem.consulta.dado.nao.encontrado"),
									bn.getString("FrmMensagem.titulo.atencao"));
					limparTextFields();
				}

			} else {

				oInterface.mensagemAviso(bn.getString("mensage.campos.branco"),
						bn.getString("FrmMensagem.titulo.atencao"));
			}

		} else if (consultou) {
			if (evento.getSource() == btnAlterar) {
				sCod = tfCodigo.getText();
				dData = dcData.getDate();
				Object origem = cbOrigem.getSelectedItem();
				Object destino = cbDestino.getSelectedItem();
				Object escala = cbEscala.getSelectedItem();
				Object aeron = cbAeronave.getSelectedItem();

				if (!(oValida.camposEmBranco(sCod)
						|| oValida.camposEmBranco(dData)
						|| oValida.camposEmBranco(origem)
						|| oValida.camposEmBranco(destino)
						|| oValida.camposEmBranco(escala) || oValida
							.camposEmBranco(aeron))) {

					int escolha = JOptionPane.showConfirmDialog(this,
							bn.getString("mensagem.alterar.confirmacao"),
							bn.getString("FrmMensagem.titulo.atencao"),
							JOptionPane.YES_NO_OPTION);

					if (escolha == JOptionPane.YES_OPTION) {

						System.out.println("Entrou");

						DateFormat format = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");

						Situacao sit = (Situacao) cbSituacao.getSelectedItem();

						Localidade localOri = (Localidade) cbOrigem
								.getSelectedItem();
						Localidade localDest = (Localidade) cbDestino
								.getSelectedItem();
						Localidade localEsc = (Localidade) cbEscala
								.getSelectedItem();

						String valeu = tfValor.getText();
						Double valor = Double.parseDouble(tfValor.getText());

						oVoo = new Voo(Integer.parseInt(tfCodigo.getText()),
								localOri.toString(), localDest.toString(),
								localEsc.toString(), format.format(dcData
										.getDate()), valor, sit.getCodigo());

						// Casting do JComboBox pegando a Aeronave selecionada
						Aeronave aeroSelecionada = (Aeronave) cbAeronave
								.getSelectedItem();

						try {
							oVoo.alterar(aeroSelecionada.getCodigoAeronave(), 1);
						} catch (SQLException e) {
							oInterface.mensagemAviso(
									bn.getString("mensagem.alterar.erro"),
									bn.getString("FrmMensagem.titulo.atencao"));
						}
					}

				} else {
					oInterface.mensagemAviso(
							bn.getString("mensage.campos.branco"),
							bn.getString("FrmMensagem.titulo.atencao"));
				}
			}
			if (evento.getSource() == btnExcluir) {
				int escolha = JOptionPane.showConfirmDialog(this,
						bn.getString("mensagem.excluir.confirmacao"),
						bn.getString("FrmMensagem.titulo.atencao"),
						JOptionPane.YES_NO_OPTION);

				if (escolha == JOptionPane.YES_OPTION) {
					try {
						sCod = tfCodigo.getText();
						oVoo = new Voo((Integer.parseInt(sCod)));
						oVoo.excluir();
						oInterface.mensagemInformacao(
								bn.getString("mensagem.excluir.exito"),
								bn.getString("excluir"));
						limparTextFields();

					} catch (NumberFormatException e) {

						oInterface.mensagemErro(
								bn.getString("mensagem.excluir.erro"),
								bn.getString("FrmMensagem.titulo.atencao"));

					} catch (SQLException e) {

						oInterface.mensagemErro(
								bn.getString("mensagem.excluir.erro"),
								bn.getString("FrmMensagem.titulo.atencao"));
					}
				}
			}
		} else {
			oInterface.mensagemErro(bn.getString("mensagem.efetuar.consulta"),
					bn.getString("FrmMensagem.titulo.atencao"));
		}
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
			cbSituacao.setEnabled(false);
			cbOrigem.setEnabled(false);
			cbDestino.setEnabled(false);
			cbEscala.setEnabled(false);
			cbAeronave.setEnabled(false);
			// tfCodigo.setEnabled(false);
		} else if (itemEscolhido.equals("Alterar")) {
			btnCadastrar.setEnabled(false);
			btnExcluir.setEnabled(false);
		} else {
			btnCadastrar.setEnabled(false);
			btnAlterar.setEnabled(false);
		}
	}

	private void recarregarCampos() {

		tfCodigo.setText("" + oVoo.getCodigoVoo());
		tfValor.setText("" + oVoo.getValor());

		// Situacao
		modelo = (DefaultComboBoxModel) cbSituacao.getModel();
		modelo.removeAllElements();
		modelo.addElement(arrSit.get(0));
		cbSituacao.setModel(modelo);

		System.out.println("modeloca Sit " + arrSit);

		// Origem
		modeloLoca = (DefaultComboBoxModel) cbOrigem.getModel();
		modeloLoca.removeAllElements();
		modeloLoca.addElement(oVoo.getOrigem());

		// Destino
		modeloLoca = (DefaultComboBoxModel) cbDestino.getModel();
		modeloLoca.removeAllElements();
		modeloLoca.addElement(oVoo.getDestino());
		cbDestino.setModel(modeloLoca);

		// Escala
		modeloLoca = (DefaultComboBoxModel) cbEscala.getModel();
		modeloLoca.removeAllElements();
		modeloLoca.addElement(oVoo.getEscala());
		cbEscala.setModel(modeloLoca);

		// Formatando a data
		// DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// dcData.setDate(format.format(oVoo.getDateHora());

		// Aeronave
		modelo = (DefaultComboBoxModel) cbAeronave.getModel();
		modelo.removeAllElements();
		modelo.addElement(arrAero.get(0));
		cbAeronave.setModel(modelo);

		repaint();
	}

	private void limparTextFields() {
		tfCodigo.setText("");
		tfValor.setText("");
		cbOrigem.setSelectedIndex(0);
		cbDestino.setSelectedIndex(0);
		cbEscala.setSelectedIndex(0);
		cbAeronave.setSelectedIndex(0);
		cbSituacao.setSelectedIndex(0);
		dcData.setDate(null);
	}
}
