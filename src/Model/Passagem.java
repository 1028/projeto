package Model;

import Model.dao.DaoFactory;
import Model.dao.PassagemDao;

public class Passagem {
	PassagemTO dadosPassagem = null;

	public Passagem(PassagemTO dadosPassagem) {
		this.dadosPassagem = dadosPassagem;
	}

	public void efetuarCheckin(){
		
	}
	
	public void transferir(){
		DaoFactory factory = DaoFactory.getInstance();
		PassagemDao dao = factory.getPassagemDao();
		dao.tranferirPassagem(dadosPassagem);
	}
	
	public void consultarPassagem(){
		DaoFactory factory = DaoFactory.getInstance();
		PassagemDao dao = factory.getPassagemDao();
		dao.consultarPassagem(dadosPassagem);
	}
	
	public int emitirCartaoEmbarque(){
		
		return 1;
	}
}
