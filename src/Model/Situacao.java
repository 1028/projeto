//Classe de Domínio referente a tabela SITUACAO da base.

package Model;

import java.sql.SQLException;
import java.util.ArrayList;


public class Situacao {
	
	SituacaoTO dadosSituacao;
	
	public Situacao(SituacaoTO dadosSituacao) {
		this.dadosSituacao = dadosSituacao;
	}
	
	public String toString() {
		return String.format(" %d - %s", getCodigo(), getNome());
	}
	

	
	public void consultar() throws SQLException {
		bd = new BancoDeDados();
		
		bd.consultarSituacao(getCodigo());
	}
	
	public ArrayList consultarTodos() throws SQLException {
		bd = new BancoDeDados();
		bd.consultarSituacao();
		
		return bd.getRetornoQuery();
	}
}
