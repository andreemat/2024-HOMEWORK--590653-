package it.uniroma3.diadia;


import java.io.FileNotFoundException;

import java.io.IOException;
import java.util.Scanner;

import it.uniroma3.diadia.ambienti.FormatoFileNonValidoException;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comandi.AbstractComando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */
 class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	

	private Partita partita;
	private  IO io;
	
	public DiaDia(IO io) throws FormatoFileNonValidoException, IOException {
		this.io=io;
		this.partita = new Partita(io);
		
	}
	public DiaDia(IO io,Labirinto labirinto) throws FileNotFoundException, IOException {
		this.io=io;
		this.partita = new Partita(io,labirinto);
	}

	public void gioca(Scanner scannerDiLinee) {
		String istruzione; 

		io.mostraMessaggio(MESSAGGIO_BENVENUTO);
				do		{
					String riga = scannerDiLinee.nextLine(); 
					istruzione=riga;
					}
				while (!processaIstruzione(istruzione));
				
			
		
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		AbstractComando comandoDaEseguire;
		FabbricaDiComandi factory = new FabbricaDiComandiRiflessiva();
		comandoDaEseguire = factory.costruisciComando(istruzione);
		comandoDaEseguire.esegui(this.partita) ;
		if (this.partita.vinta())
			io.mostraMessaggio("Hai vinto!");
		if (!this.partita.giocatoreIsVivo())
			io.mostraMessaggio("Hai esaurito i CFU...");
		return this.partita.isFinita();
	}


	public static void main(String[] argc) throws FileNotFoundException, FormatoFileNonValidoException, IOException {		
		IO io =new IOconsole();

		DiaDia gioco = new DiaDia(io);
	
		try(Scanner scannerDiLinee = new Scanner(System.in)){ 
			gioco.gioca(scannerDiLinee);
				
			}
		
	}
}