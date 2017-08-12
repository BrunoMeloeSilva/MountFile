package bcms.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MountFile {

	public static void main(String[] args) throws IOException {
		BufferedReader tuple = new BufferedReader(new FileReader("Variaveis.txt"));

		String line = null;
		int posSemicolon = 0;
		String[] vars = new String[2];
		ArrayList<String> cmdsSql = new ArrayList<>();
		while ((line = tuple.readLine()) != null) {
			posSemicolon = line.indexOf(';');
			vars[0] = line.substring(0, posSemicolon);
			vars[1] = line.substring(posSemicolon + 1, line.length());
			cmdsSql.add("\n--$0 = " + vars[0] + " $1 = " + vars[1]);
			BufferedReader fileCmdTxt = new BufferedReader(new FileReader("Comandos.txt"));
			int tag = 0;
			while ((line = fileCmdTxt.readLine()) != null) {
				String cmd = "";
				while ((tag = line.indexOf('$')) != -1) {
					cmd = cmd + line.substring(cmd.length(), tag);
					String index = line.substring(tag + 1, tag + 2);
					int i = new Integer(index);
					cmd = cmd + vars[i];
					int begin = cmd.length() - vars[i].length() + 2;
					int end = line.length();
					line = cmd + line.substring(begin, end);
					cmd = "";
				}
				cmdsSql.add(line);
			}
			fileCmdTxt.close();
		}
		for (String string : cmdsSql) {
			System.out.println(string);
		}
		tuple.close();
	}
}
/* Limitações: 
 * a. O arquivo de variaveis só pode ter até duas colunas, exemplo: a;b
 * 		pois quando codifiquei, não generalizei o código..
 * 
 * Obs.: Se estiver executando no eclipse, deve-se aumentar o limite de caracteres de saida em 
 * (No console, botão direito, Preferences, Limit console output)
 */