package it.uniroma3.diadia;


import java.util.Scanner;
import it.uniroma3.diadia.IOconsole.IOconsole;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

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
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine","prendi","posa"};

	private Partita partita;
	private IOconsole IO;
	

	public DiaDia(IOconsole IO) {
		this.IO=IO;
		this.partita = new Partita();
	}

	public void gioca() {
		String istruzione; 

		IO.mostraMessaggio(MESSAGGIO_BENVENUTO);
		
		do		
			istruzione=IO.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		
		Comando comandoDaEseguire = new Comando(istruzione);
		if(comandoDaEseguire.getNome()!=null) {
			if (comandoDaEseguire.getNome().equals("fine")) {
				this.fine(); 
				return true;
			} else if (comandoDaEseguire.getNome().equals("vai"))
				this.vai(comandoDaEseguire.getParametro());
			else if (comandoDaEseguire.getNome().equals("aiuto"))
				this.aiuto();
			else if (comandoDaEseguire.getNome().equals("prendi"))
				this.PrendiAttrezzo(comandoDaEseguire.getParametro());
			else if (comandoDaEseguire.getNome().equals("posa"))
				this.PosaAttrezzo(comandoDaEseguire.getParametro());
			else
				IO.mostraMessaggio("Comando sconosciuto");
			if (this.partita.vinta()) {
				IO.mostraMessaggio("Hai vinto!");
				return true;}
			else if(this.partita.isFinita()){
					IO.mostraMessaggio("hai perso");
					return true;
			}	
			else
				return false;}
		else return false;
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		String Comandi= new String();
		for(int i=0; i< elencoComandi.length; i++) 
			Comandi= Comandi+ (elencoComandi[i]+" ");
		IO.mostraMessaggio(Comandi);
		IO.mostraMessaggio("\n");
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null)
			IO.mostraMessaggio("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			IO.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			
			int cfu = this.partita.getGiocatore().getCfu()-1;
			this.partita.getGiocatore().setCfu(cfu);
		}
		IO.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		IO.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
	}
	
	
	public void PrendiAttrezzo(String NomeAttrezzo) {
		if(NomeAttrezzo==null) {
			IO.mostraMessaggio("Che attrezzo vuoi prendere? ");
		}
		else {
			if(this.partita.getStanzaCorrente().hasAttrezzo(NomeAttrezzo)){
			
				Attrezzo attrezzo= this.partita.getStanzaCorrente().getAttrezzo(NomeAttrezzo);
				this.partita.getStanzaCorrente().removeAttrezzo(attrezzo);
				this.partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
				IO.mostraMessaggio("hai preso l'attrezzo: "+this.partita.getGiocatore().getBorsa().getAttrezzo(NomeAttrezzo));				
			}
		}
		IO.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		IO.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
	}
	
	public void PosaAttrezzo(String NomeAttrezzo) {
		if(this.partita.getGiocatore().getBorsa().hasAttrezzo(NomeAttrezzo)){
			Attrezzo attrezzo=this.partita.getGiocatore().getBorsa().removeAttrezzo(NomeAttrezzo);
			this.partita.getStanzaCorrente().addAttrezzo(attrezzo);
			IO.mostraMessaggio("hai posato l'attrezzo: "+ attrezzo);
		}
		IO.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		IO.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
	}
	/**
	 * Comando "Fine".
	 */
	private void fine() {
		IO.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		IOconsole IO =new IOconsole();
		DiaDia gioco = new DiaDia(IO);

		
		gioco.gioca();
	}
}