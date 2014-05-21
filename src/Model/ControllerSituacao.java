package Model;

import java.sql.SQLException;
import java.util.ArrayList;


public class ControllerSituacao {
	private ArrayList<ClasseDominioSituacao> listaSituacao;
	
	public ControllerSituacao() {
		listaSituacao = new ArrayList<ClasseDominioSituacao>();
	}
	
	public ClasseDominioSituacao getClasseDominioSituacao(int indice) {
		return listaSituacao.get(indice);
	}
	
	public void consultarTodos() throws SQLException {
		ClasseDominioSituacao sit = new ClasseDominioSituacao();
		
		ArrayList aux = sit.consultarTodos();
		
		for(int i = 0; i < aux.size(); i+=2) {
			ClasseDominioSituacao auxSit = new ClasseDominioSituacao((String)aux.get(i+1),(Integer)aux.get(i));
			listaSituacao.add(auxSit);
		}
	}
	
	public ArrayList<ClasseDominioSituacao> getArrayList() {
		return listaSituacao;
	}
}
