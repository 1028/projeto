package Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.IllegalStateException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import Model.Login;

public class LerArquivo {
	private FileReader reader;
	private BufferedReader leitor;
	private ArrayList<Login> vetLogin;

	private void abrirArquivo() {
		try {
			reader = new FileReader("src/Sistema Acesso.txt");
		} // fim try
		catch (FileNotFoundException fileNotFoundException) {
			// mensagem("Erro ao abrir o arquivo!");
			System.exit(1);
		} // fim catch
	} // fim método abrirArquivo

	public int verificaArquivo(String sLogin, String sSenha) throws Exception {
		try {
			abrirArquivo();
			String linha = null;
			String sMsgDecifrada = null;

			byte[] bMsgCifrada = null;
			byte[] bMsgDecifrada = null;

			leitor = new BufferedReader(reader);
			linha = leitor.readLine();

			if (!(linha == null)) {
				bMsgCifrada = (linha).getBytes("ISO-8859-1");

				// Instancia um objeto da classe CryptoDummy
				CryptoDummy cdummy = new CryptoDummy();

				// Gera a decifra Dummy da mensagem dada, segundo a chave
				// Dummy
				// simetrica gerada
				cdummy.geraDecifra(bMsgCifrada, new File("src/chave.dummy"));

				// recebe o texto decifrado
				bMsgDecifrada = cdummy.getTextoDecifrado();

				// Converte o texto byte[] no equivalente String
				sMsgDecifrada = (new String(bMsgDecifrada, "ISO-8859-1"));

				String vetAux[] = sMsgDecifrada.split("; ");

				vetLogin = new ArrayList<Login>();

				for (int x = 0; x < vetAux.length - 1; x += 3) {
					Login oLog = new Login();
					oLog.setLogin(vetAux[x]);
					oLog.setSenha(vetAux[x + 1]);
					oLog.setTipoUsuario(Integer.parseInt(vetAux[x + 2]));
					vetLogin.add(oLog);
				}
			}// if de verificação string nula

			// BuscaBinaria
			int indice = buscaBin(vetLogin, sLogin, 0, vetLogin.size() - 1);
			if (indice != -1) {
				if (sSenha.equals(vetLogin.get(indice).getSenha())) {
					fecharArquivo();
					return (vetLogin.get(indice).getTipoUsuario());
				}
			}
		} // fim try
		catch (NoSuchElementException elementException) {
			fecharArquivo();
			System.exit(1);
		} // fim catch
		catch (IllegalStateException stateException) {
			// mensagem("Erro ao ler o arquivo");
			System.exit(1);
		} // fim catch
		finally {
			// mensagem(sMsg);
			try {
				fecharArquivo();
			} catch (Exception e) {
				// mensagem("Erro no fechamento do arquivo!");
			}
		}

		return -1;
	}// fim método verificaArquivo

	private void fecharArquivo() throws IOException {
		if (reader != null) {
			reader.close();
		}
	} // fim método fecharArquivo

	private int buscaBin(ArrayList<Login> vet, String procura, int inicio,
			int fim) {
		if (!(inicio > fim)) {
			// acha o indice do meio
			int meio = (inicio + fim) / 2;

			// guarda a comparação da string de indice meio com a que se procura
			int comp = vet.get(meio).getLogin().compareTo(procura);

			// se elas forem iguais, o valor de comp será 0
			if (comp == 0) {
				return meio;
			}
			// a String procura vier depois da String de vet[meio], então comp
			// será maior que 0
			else if (comp > 0) {
				return buscaBin(vet, procura, inicio, meio - 1);
			}
			// a String procura vier antes da String de vet[meio], então comp
			// será maior que 0
			else if (comp < 0) {
				return buscaBin(vet, procura, meio + 1, fim);
			}
		}
		return -1;
	}

} // fim da classe LerArquivo