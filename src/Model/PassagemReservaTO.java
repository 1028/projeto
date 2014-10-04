package Model;

public class PassagemReservaTO extends PassagemTO{
	private int iCodigoAgenciaExterna, iStatusReserva;

	public int getiCodigoAgenciaExterna() {
		return iCodigoAgenciaExterna;
	}

	public void setiCodigoAgenciaExterna(int iCodigoAgenciaExterna) {
		this.iCodigoAgenciaExterna = iCodigoAgenciaExterna;
	}

	public int getiStatusReserva() {
		return iStatusReserva;
	}

	public void setiStatusReserva(int iStatusReserva) {
		this.iStatusReserva = iStatusReserva;
	}

}
