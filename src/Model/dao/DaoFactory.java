package Model.dao;

import java.sql.SQLException;

import Model.dao.impl.AeronaveMysqlDaoImpl;
import Model.dao.impl.ChequeMysqlDaoImpl;
import Model.dao.impl.FormaTratamentoMysqlDaoImpl;
import Model.dao.impl.LoginMysqlDaoImpl;
import Model.dao.impl.PagamentoMysqlDaoImpl;
import Model.dao.impl.PassageiroMysqlDaoImpl;
import Model.dao.impl.PassagemCompraMysqlDaoImpl;
import Model.dao.impl.PassagemMysqlDaoImpl;
import Model.dao.impl.PerfilMysqlDaoImpl;
import Model.dao.impl.SituacaoMysqlDaoImpl;
import Model.dao.impl.VooMysqlDaoImpl;
import Model.dao.impl.LocalidadeMysqlDaoImpl;

public class DaoFactory {

	private static DaoFactory factory = null;

	private DaoFactory() {

	}

	public static DaoFactory getInstance() {
		if (factory == null) {
			factory = new DaoFactory();
		}
		return factory;
	}

	public PassageiroMysqlDaoImpl getPassageiroDao() throws SQLException {
		return new PassageiroMysqlDaoImpl();
	}

	public AeronaveMysqlDaoImpl getAeronaveDao() {
		return new AeronaveMysqlDaoImpl();
	}
	
	public VooMysqlDaoImpl getVooDao(){
		return new VooMysqlDaoImpl();
	}
	
	public LoginMysqlDaoImpl getLoginDao(){
		return new LoginMysqlDaoImpl(); 
	}
	
	public SituacaoMysqlDaoImpl getSituacaoDao(){
		return new SituacaoMysqlDaoImpl();
	}
	
	public FormaTratamentoMysqlDaoImpl getFormaTratamentoDao(){
		return new FormaTratamentoMysqlDaoImpl();
	}
	
	public PerfilMysqlDaoImpl getPerfilDao(){
		return new PerfilMysqlDaoImpl();
	}
	
	public LocalidadeMysqlDaoImpl getLocalidadeDao(){
		return new LocalidadeMysqlDaoImpl();
	}
	
	public PassagemMysqlDaoImpl getPassagemDao(){
		return new PassagemMysqlDaoImpl(); 
	}
	
	public PassagemCompraMysqlDaoImpl getPassagemCompraDao(){
		return new PassagemCompraMysqlDaoImpl(); 
	}
	
	public PagamentoMysqlDaoImpl getPagamentoDao(){
		return new PagamentoMysqlDaoImpl(); 
	}
	
	public ChequeMysqlDaoImpl getChequeDao(){
		return new ChequeMysqlDaoImpl(); 
	}
}
