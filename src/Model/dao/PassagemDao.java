package Model.dao;

import Model.PassagemTO;

public interface PassagemDao {
	public void consultarPassagem(PassagemTO passagem);
	
	public void tranferirPassagem(PassagemTO passagem);
}
