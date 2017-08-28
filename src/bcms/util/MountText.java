package bcms.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MountText {

	public static void mountText(String variableTextFilePath, char variableTextFileSeparator,
			String commandTextFilePath) throws IOException {
		BufferedReader tuple = new BufferedReader(new FileReader(variableTextFilePath));
		// Descobrir a quantidade de colunas de "Variaveis.txt"
		// Inicio
		String line = null;
		int positionSeparator = 0;
		int numberOfColumns = 0;
		if ((line = tuple.readLine()) != null) {
			while ((positionSeparator = line.indexOf(variableTextFileSeparator)) != -1) {
				numberOfColumns++;
				line = line.substring(++positionSeparator, line.length());
			}
			positionSeparator = 0;
		}
		System.out.println("--> " + numberOfColumns+" colunas identificadas, proceso de escrita iniciado..\n");
		String[] valores = new String[numberOfColumns];
		// Fim
		while ((line = tuple.readLine()) != null) {
			// obtem valores da linha atual do arquivo de variaveis
			// Inicio
			for (int i = 0; i < numberOfColumns; i++) {
				line = line.substring(positionSeparator, line.length());
				positionSeparator = line.indexOf(variableTextFileSeparator) + 1;
				valores[i] = line.substring(0, line.indexOf(variableTextFileSeparator));
			}
			positionSeparator = 0;
			// Fim
			// replace no arquivo de comando, o arquivo inteiro é considerado
			// como uma linha
			BufferedReader fileCmdTxt = new BufferedReader(new FileReader(commandTextFilePath));
			while ((line = fileCmdTxt.readLine()) != null) {
				for (int j = 0; j < numberOfColumns; j++) {
					line = line.replace("$" + j +"$", valores[j]);
				}
				System.out.println(line);
			}
			fileCmdTxt.close();
		}
		tuple.close();
	}

	public static void main(String[] args) throws IOException {
		mountText("Variaveis.txt", ';', "Comandos.txt");
	}
}
/*
 * a. O arquivo de variaveis pode N colunas, exemplo: a;b;...N;
 * b. A primeira linha do aquivo de variaveis deve ser o cabecalho, mas será
 * desconsiderado na saída. Obs.: Se estiver executando no eclipse, deve-se
 * aumentar o limite de caracteres de saida em (No console, botão direito,
 * Preferences, Limit console output)
 * c. O arquivo de comandos deve seguir a seguinte regra para os valores que serão substituidos pela respectiva
 * posição no arquivo de variaveis, $posicao$ , exemplo: $0$ para pegar o valor na posição 0 do arquivo de variaveis.
 */
