package Model.dao;

import Model.PassagemCompraTO;

public interface PassagemCompraDao extends PassagemDao{
	public void cadastrarPassagem(PassagemCompraTO passagemCompra);
	
	public void cancelarPassagem(PassagemCompraTO passagemCompra);
}
