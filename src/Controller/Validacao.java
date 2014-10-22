package Controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Validacao {
	public Validacao() {

	}

	public boolean camposEmBranco(java.util.Date dData) {
		if (dData == null) {
			return true;
		}
		return false;
	}

	public boolean camposEmBranco(String campo1) {
		if (campo1.equals("")) {
			return true;
		}
		return false;
	}

	public boolean camposEmBranco(Object campo1) {

		if (campo1 == null) {
			return true;
		}
		return false;
	}

	public boolean camposEmBranco(String campo1, String campo2) {
		if (campo1.equals("") || campo2.equals("")) {
			return true;
		}
		return false;
	}

	public boolean camposEmBranco(String campo1, String campo2, String campo3) {
		if (campo1.equals("") || campo2.equals("") || campo3.equals("")) {
			return true;
		}
		return false;
	}

	public boolean camposEmBranco(String campo1, String campo2, String campo3,
			String campo4) {
		if (campo1.equals("") || campo2.equals("") || campo3.equals("")
				|| campo4.equals("")) {
			return true;
		}
		return false;
	}

	public boolean camposEmBranco(String campo1, String campo2, String campo3,
			String campo4, String campo5) {
		if (campo1.equals("") || campo2.equals("") || campo3.equals("")
				|| campo4.equals("") || campo5.equals("")) {
			return true;
		}
		return false;
	}

	public boolean operacaoCadastro(String operacao){
		if(operacao.equals("Cadastrar") || operacao.equals("Register") || operacao.equals("Registro")){
			return true;
		}
		return false;
	}
	
	public boolean operacaoConsultar(String operacao){
		if(operacao.equals("Consultar") || operacao.equals("Consult") || operacao.equals("Consultar")){
			return true;
		}
		return false;
	}
	
	public boolean operacaoAlterar(String operacao){
		if(operacao.equals("Alterar") || operacao.equals("Change") || operacao.equals("Alterar")){
			return true;
		}
		return false;
	}
	
	public boolean operacaoExcluir(String operacao){
		if(operacao.equals("Excluir") || operacao.equals("Delete") || operacao.equals("Excluir")){
			return true;
		}
		return false;
	}
	
	// Método para verificar o tempo restante para o voo e realizar o cálculo do valor a ser devolvido.
		public double calculaDevolucao(int iNumPassagem) {
			double totalValorDevolucao = -1.0;

			//Passagem oPassagem = new Passagem(iNumPassagem);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");

			String sDiaVerificar = (sdf.format(new Date())).substring(0, 10);
			String sHoraVerificar = (sdf.format(new Date())).substring(11, 19);
			
			long diaAntesVoo = verificarDiasRestante(sDiaVerificar);
			int horaAntesVoo = verificarHorasRestante(sHoraVerificar);

			if (diaAntesVoo >= 1 || horaAntesVoo >= 2) {

				// substituir os 1001 pelo valor vinculado a passagem
				// pesquisada!
				if (diaAntesVoo >= 1) {
					totalValorDevolucao = 1001;
				} else {
					if (horaAntesVoo >= 12) {
						totalValorDevolucao = (1001 * 0.4);
					} else if (horaAntesVoo >= 6) {
						totalValorDevolucao = (1001 * 0.2);
					}
				}
			}
			return totalValorDevolucao;
		}

		public int verificarHorasRestante(String sHoraVerificar) {

			SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
			String sHoraAtual = (sdf.format(new Date()));

			String horas = "00";
			String minutos = "00";
			String segundos = "00";

			int sub = 0;
			int subHoras = 0;
			int subMinutos = 0;

			int segundos1 = (Integer.parseInt(sHoraVerificar.substring(0, 2)) * 3600)
					+ (Integer.parseInt(sHoraVerificar.substring(3, 5)) * 60)
					+ Integer.parseInt(sHoraVerificar.substring(6, 8));
			int segundos2 = (Integer.parseInt(sHoraAtual.substring(0, 2)) * 3600)
					+ (Integer.parseInt(sHoraAtual.substring(3, 5)) * 60)
					+ Integer.parseInt(sHoraAtual.substring(6, 8));

			if (segundos1 > segundos2) {
				sub = segundos1 - segundos2;
			} else if (segundos2 > segundos1) {
				sub = segundos2 - segundos1;
			} else {
				sub = 0;
			}

			if (sub >= 3600) {
				subHoras = (sub - (sub % 3600)) / 3600;
				sub = sub - (subHoras * 3600);
				if (subHoras < 10) {
					horas = "0" + Integer.toString(subHoras);
				} else {
					horas = Integer.toString(subHoras);
				}
			}

			if (sub >= 60) {
				subMinutos = (sub - (sub % 60)) / 60;
				sub = sub - (subMinutos * 60);
				if (subMinutos < 10) {
					minutos = "0" + Integer.toString(subMinutos);
				} else {
					minutos = Integer.toString(subMinutos);
				}
			}

			if (sub < 10) {
				segundos = "0" + Integer.toString(sub);
			} else {
				segundos = Integer.toString(sub);
			}

			 System.out.println("HH:MM" + horas + ":" + minutos + ":" + segundos);
			 
			return Integer.parseInt(horas);
		}

		public long verificarDiasRestante(String sDiaVerificar) {
			// Data inicial
			Calendar dataInicio = Calendar.getInstance();
			// Atribui a data de 10/FEV/2008
			dataInicio.set(2013, Calendar.OCTOBER, 17);
			/*
			 * dataInicio.set(Integer.parseInt(sDiaVerificar.substring(0, 4)),
			 * Integer.parseInt(sDiaVerificar.substring(5, 7)),
			 * Integer.parseInt(sDiaVerificar.substring(8, 10)));
			 */
			// Data de hoje
			Calendar dataFinal = Calendar.getInstance();
			// Calcula a diferença entre hoje e da data de inicio
			long diferenca = dataFinal.getTimeInMillis()
					- dataInicio.getTimeInMillis();
			// Quantidade de milissegundos em um dia
			int tempoDia = 1000 * 60 * 60 * 24;
			long diasDiferenca = diferenca / tempoDia;
			System.out.println("Entre a data inicial e final são " + diasDiferenca
					+ " dias de diferença.");

			return diasDiferenca;
		}

}
