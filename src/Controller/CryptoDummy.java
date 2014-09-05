package Controller;

import java.io.*;

public class CryptoDummy {
	private byte[] textoCifrado;
	private byte[] textoDecifrado;

	public CryptoDummy() {
		textoCifrado = null;
		textoDecifrado = null;
	}

	public void geraChave(File fDummy) throws IOException {

		// Gera uma chave Dummy simétrica (dk: 0 a 100):
		int dk = (int) (Math.random() * 101);

		// Grava a chave Dummy simétrica em formato serializado
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
				fDummy));
		oos.writeObject(dk);
		oos.close();

	}

	public void geraCifra(byte[] texto, File fDummy) throws IOException,
			ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
				fDummy));
		int iDummy = (Integer) ois.readObject();
		ois.close();
		textoCifrado = texto;
		for (int i = 0; i < texto.length; i++) {
			textoCifrado[i] = (byte) (textoCifrado[i] + i + iDummy);
		}
	}

	public byte[] getTextoCifrado() throws Exception {
		return textoCifrado;
	}

	public void geraDecifra(byte[] texto, File fDummy) throws IOException,
			ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
				fDummy));
		int iDummy = (Integer) ois.readObject();
		ois.close();
		textoDecifrado = texto;
		for (int i = 0; i < texto.length; i++) {
			textoDecifrado[i] = (byte) (textoDecifrado[i] - i - iDummy);
		}
	}

	public byte[] getTextoDecifrado() throws Exception {
		return textoDecifrado;
	}

	public String cifraSenha(String senha) throws Exception {
		// Converte o texto String dado no equivalente byte[]
		byte[] bSenhaClara = senha.getBytes("ISO-8859-1");

		// Gera a cifra Dummy da senha dada, com a chave Dummy simetrica
		// dada
		geraCifra(bSenhaClara, new File("chave.dummy"));

		// Recebe o texto cifrado
		byte[] bSenhaCifrada = getTextoCifrado();

		// Converte o texto byte[] no equivalente String
		String sSenhaCriptografada = "";
		sSenhaCriptografada = (new String(bSenhaCifrada, "ISO-8859-1"));

		return sSenhaCriptografada;
	}
}
