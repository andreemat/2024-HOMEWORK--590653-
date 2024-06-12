package it.uniroma3.diadia.comandi;

import java.util.Scanner;

public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi {

	@Override
	public AbstractComando costruisciComando(String istruzione) {
		try (Scanner scannerDiParole = new Scanner(istruzione)) {
			String nomeComando = null;
			String parametro = null;
			AbstractComando comando = null;
			if (scannerDiParole.hasNext()) 
				nomeComando = scannerDiParole.next(); // prima parola:nome del comando
			if (scannerDiParole.hasNext()) 
				parametro = scannerDiParole.next(); // seconda parola: eventuale param.
			try {
				String NomeClasse="it.uniroma3.diadia.comandi.Comando";
				NomeClasse+=Character.toUpperCase(nomeComando.charAt(0));
				NomeClasse+=nomeComando.substring(1);
				comando= (AbstractComando)Class.forName(NomeClasse).newInstance();
				comando.setParametro(parametro);
			}
			catch(Exception e){
				comando = new ComandoNonValido();

			}

			return comando;
		}
	}

}