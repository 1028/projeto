package Controller;

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
}
