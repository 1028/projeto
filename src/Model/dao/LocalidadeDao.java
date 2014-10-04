package Model.dao;

import java.util.List;

import Model.LocalidadeTO;

public interface LocalidadeDao {

	public void inserir(LocalidadeTO Localidade);
	
	public void consultarLocalidade(LocalidadeTO Localidade);
	
	public void alterar(int codigo, String uf, String nome);
	
	public void excluir(LocalidadeTO Localidade);
	
	public List<LocalidadeTO> consultar();
	
	public int total();
}
