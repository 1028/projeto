package Model;

public class PassagemReserva extends Passagem{
	
	PassagemReservaTO dadosPassagemReserva = null;
	
	public PassagemReserva(PassagemTO dadosPassagem, PassagemReservaTO dadosPassagemReserva) {
		super(dadosPassagem);
		this.dadosPassagemReserva = dadosPassagemReserva;
	}

	public void validarReserva() {

	}

	public void cadastrarAgencia() {

	}
}
