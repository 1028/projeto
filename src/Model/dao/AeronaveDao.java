package Model.dao;

import java.util.ArrayList;
import java.util.List;

import Model.AeronaveTO;

public interface AeronaveDao {
	public void inserirAeronave(AeronaveTO aeronave);
	
	public void alterarAeronave(AeronaveTO aeronave);
	
	public void excluirAeronave(AeronaveTO aeronave);
	
	public List<AeronaveTO> consultarAeronave(AeronaveTO aeronave);
	
	public ArrayList<AeronaveTO> consultar();

}
