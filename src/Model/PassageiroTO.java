package Model;
public class PassageiroTO {
	private int codigo, formaTrata, tipoPassageiro;
	private String nome, sobrenome, celular, email, dataNascimento;
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public int getFormaTrata() {
		return formaTrata;
	}

	public void setFormaTrata(int formaTrata) {
		this.formaTrata = formaTrata;
	}

	public int getTipoPassageiro() {
		return tipoPassageiro;
	}

	public void setTipoPassageiro(int tipoPassageiro) {
		this.tipoPassageiro = tipoPassageiro;
	}

}
