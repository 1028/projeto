package Controller;

public class Formatador {
	public Formatador() {
	}
	
	public String dataNacional(String data) {
		String dataFormatada = "";
		
		if(data.length() > 0) {
			String vet[] = data.split("/");
			dataFormatada = vet[2] + "-" +  vet[1] + "-" + vet[0];
		}
		
		return dataFormatada;
	}
	
	public String dataView(String data){
		String dataFormatada = "";
		
		if(data.length() > 0) {
			String vet[] = data.split("-");
			dataFormatada = vet[2] + "/" +  vet[1] + "/" + vet[0];
		}
		
		return dataFormatada;
	}
}
