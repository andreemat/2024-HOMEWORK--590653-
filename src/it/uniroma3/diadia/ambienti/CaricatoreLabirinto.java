package it.uniroma3.diadia.ambienti;

import java.io.*;
import java.util.*;

import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza.Direction;


import it.uniroma3.diadia.attrezzi.Attrezzo;

public class CaricatoreLabirinto {

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MARKER = "Stanze: ";             

	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String STANZA_INIZIALE_MARKER = "Inizio: ";    

	/* prefisso della riga contenente il nome stanza vincente */
	private static final String STANZA_VINCENTE_MARKER = "Vincente: ";  

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	private static final String ATTREZZI_MARKER = "Attrezzi: ";

	/* prefisso della riga contenente le specifiche dei collegamenti tra stanza nel formato <nomeStanzaDa> <direzione> <nomeStanzaA> */
	private static final String USCITE_MARKER = "Uscite: ";
	
	
	private static final String STANZE_BUIE_MARKER = "Stanze buie: ";
	
	
	private static final String STANZE_BLOCCATA_MARKER = "Stanze bloccate: ";
	
	
	private static final String STANZE_MAGICHE_MARKER = "Stanze magiche: ";
	
	private static final String STREGHE_MARKER = "Streghe: ";
	
	private static final String MAGHI_MARKER = "Maghi: ";
	
	private static final String CANI_MARKER = "Cani: ";
	
	

	/*
	 *  Esempio di un possibile file di specifica di un labirinto (vedi POO-26-eccezioni-file.pdf)

		Stanze: biblioteca, N10, N11
		Inizio: N10
		Vincente: N11
		Attrezzi: martello 10 biblioteca, pinza 2 N10
		Uscite: biblioteca nord N10, biblioteca sud N11

	 */
	private BufferedReader reader;

	private Map<String, Stanza> nome2stanza;

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;
	private LabirintoBuilder  lab;

	public CaricatoreLabirinto(String nomeFile) throws IOException {
		this.nome2stanza = new HashMap<String,Stanza>();

	    // Get the class loader
        
		this.reader = new LineNumberReader(new FileReader(nomeFile));
//      
        
        lab= new LabirintoBuilder();
	}

	public CaricatoreLabirinto(StringReader s) {
		this.nome2stanza = new HashMap<String,Stanza>();

	    // Get the class loader
        
		this.reader = new LineNumberReader(s);
//      
        
        lab= new LabirintoBuilder();
	}

	public void carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiECollocaStanzeMagiche();
			this.leggiECollocaStanzeBuie();
			this.leggiECollocaStanzaBloccate();
			this.leggiInizialeEvincente();
			this.leggiECollocaAttrezzi();
			this.leggiEImpostaUscite();
			this.leggiECollocaMaghi();
			this.leggiECollocaCani();
			this.leggiECollocaStrega();
		
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}

	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			check(riga.startsWith(marker),"era attesa una riga che cominciasse per "+marker);
			return riga.substring(marker.length());
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			
			lab.addStanza(nomeStanza);
		
			this.nome2stanza.put(nomeStanza, lab.getStanza(nomeStanza));
		}
	
		
	}
	
	private void leggiECollocaStanzeMagiche() throws FormatoFileNonValidoException {
		String specificheStanzaMagiche = this.leggiRigaCheCominciaPer(STANZE_MAGICHE_MARKER);

		for(String specificaStanzeMagiche : separaStringheAlleVirgole(specificheStanzaMagiche)) {
			String nomeStanzaMagica = null;
			String soglia = null;

			try (Scanner scannerLinea = new Scanner(specificaStanzeMagiche)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di una stanza."));
				nomeStanzaMagica = scannerLinea.next();
				
				
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("La soglia della stanza."));
				soglia = scannerLinea.next();
				
				int sogliaMagica = Integer.parseInt(soglia);
				lab.addStanzaMagica(nomeStanzaMagica,sogliaMagica);
		
				this.nome2stanza.put(nomeStanzaMagica, lab.getStanza(nomeStanzaMagica));
				
			}				
			
		}
	}

	private void leggiECollocaStanzeBuie() throws FormatoFileNonValidoException {
		String specificheStanzaBuie = this.leggiRigaCheCominciaPer(STANZE_BUIE_MARKER);

		for(String specificaStanzeBuie : separaStringheAlleVirgole(specificheStanzaBuie)) {
			String nomeStanzaBuia = null;
			String nomeAttrezzo = null;

			try (Scanner scannerLinea = new Scanner(specificaStanzeBuie)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di una stanza."));
				nomeStanzaBuia = scannerLinea.next();
				
				
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("l'attrezzo della stanza."));
				nomeAttrezzo = scannerLinea.next();
				
;
				lab.addStanzaBuia(nomeStanzaBuia,nomeAttrezzo);
		
				this.nome2stanza.put(nomeStanzaBuia, lab.getStanza(nomeStanzaBuia));
				
			}				
			
		}
	}
	
	private void leggiECollocaStanzaBloccate() throws FormatoFileNonValidoException {
		String StanzaBloccate = this.leggiRigaCheCominciaPer(STANZE_BLOCCATA_MARKER);

		for(String StanzaBloccata : separaStringheAlleVirgole(StanzaBloccate)) {
			String nomeStanza = null;
			String direzioneBloccata = null;
			String AttrezzoSbloccante = null; 
			try (Scanner scannerLinea = new Scanner(StanzaBloccata)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di una stanza."));
				nomeStanza = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la direzione."));
				direzioneBloccata = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome dell'attrezzo."));
				AttrezzoSbloccante = scannerLinea.next();
				lab.addStanzaBloccata(nomeStanza,direzioneBloccata,AttrezzoSbloccante);
				this.nome2stanza.put(nomeStanza, lab.getStanza(nomeStanza));
				
				
			}				
			
		}
	}
	
	
	
	private void leggiECollocaMaghi() throws FormatoFileNonValidoException {
		String specificheMaghi = this.leggiRigaCheCominciaPer(MAGHI_MARKER);

		for(String specificaMaghi : separaStringheAlleVirgole(specificheMaghi)) {
			String nomeMago = null;
			String Descrizione = null;
			String NomeAttrezzo = null;
			String PesoAttrezzo = null;
			String NomeStanza = null;

			try (Scanner scannerLinea = new Scanner(specificaMaghi)) {
				scannerLinea.useDelimiter("'");
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un mago."));
				nomeMago = scannerLinea.next();
				
		
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("La descrizione di un mago."));
				
				Descrizione = scannerLinea.next();
				
				scannerLinea.skip("'");
				scannerLinea.useDelimiter(" ");
				
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				
				NomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso di un attrezzo."));
			
				PesoAttrezzo = scannerLinea.next();
			
			
				scannerLinea.useDelimiter(",");
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare il mago "+nomeMago+"."));
				
				NomeStanza = scannerLinea.next().substring(1);

				AggiungiMago(nomeMago,Descrizione,NomeAttrezzo,PesoAttrezzo,NomeStanza);
				
				
			}				
			
		}
	}
	private void AggiungiMago(String nomeMago,String Descrizione,String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			check(isStanzaValida(nomeStanza),"Mago "+ nomeMago+" non collocabile: stanza " +nomeStanza+" inesistente");
			lab.addMago(nomeMago,Descrizione,attrezzo,nomeStanza);	
		}
		catch (NumberFormatException e) {
			check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
		}
	}
	
	
	private void leggiECollocaCani() throws FormatoFileNonValidoException {
		String specificheCani = this.leggiRigaCheCominciaPer(CANI_MARKER);

		for(String specificaCani : separaStringheAlleVirgole(specificheCani)) {
			String nomeCane = null;
			String Descrizione = null;
			String NomeAttrezzo = null;
			String PesoAttrezzo =null;
			String NomeAttrezzoPreferito = null;
			String NomeStanza = null;

			try (Scanner scannerLinea = new Scanner(specificaCani)) {
				scannerLinea.useDelimiter("'");
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un cane."));
				nomeCane = scannerLinea.next();
				
		
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("La descrizione di un cane."));
				
				Descrizione = scannerLinea.next();
				
				scannerLinea.skip("'");
				scannerLinea.useDelimiter(" ");
				
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				
				NomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso di un attrezzo."));
				
				PesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome del prefrito."));
			
				NomeAttrezzoPreferito = scannerLinea.next();
			
			
				scannerLinea.useDelimiter(",");
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare il cane "+nomeCane+"."));
				
				NomeStanza = scannerLinea.next().substring(1);

				AggiungiCane(nomeCane,Descrizione,NomeAttrezzo,PesoAttrezzo,NomeAttrezzoPreferito,NomeStanza);
				
				
			}				
			
		}
	}
	private void AggiungiCane(String nomeCane,String Descrizione,String nomeAttrezzo,String PesoAttrezzo, String NomeAttrezzoPreferito, String nomeStanza) throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(PesoAttrezzo);
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			check(isStanzaValida(nomeStanza),"Cane"+ nomeCane+" non collocabile: stanza " +nomeStanza+" inesistente");
			lab.addCane(nomeCane,Descrizione,attrezzo,NomeAttrezzoPreferito,nomeStanza);	
		}
		catch (NumberFormatException e) {
			check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
		}
	}
	
	
	private void leggiECollocaStrega() throws FormatoFileNonValidoException {
		String specificheStreghe = this.leggiRigaCheCominciaPer(STREGHE_MARKER);

		for(String specificaStrega : separaStringheAlleVirgole(specificheStreghe)) {
			String nomeStrega = null;
			String Descrizione = null;
			
			String NomeStanza = null;

			try (Scanner scannerLinea = new Scanner(specificaStrega)) {
				scannerLinea.useDelimiter("'");
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di una strega."));
				nomeStrega = scannerLinea.next();
				
		
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("La descrizione di una Strega."));
				
				Descrizione = scannerLinea.next();
				
				scannerLinea.skip("'");
				scannerLinea.useDelimiter(" ");
			
				scannerLinea.useDelimiter(",");
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare il cane "+nomeStrega+"."));
				
				NomeStanza = scannerLinea.next().substring(1);

				AggiungiStrega(nomeStrega,Descrizione,NomeStanza);
				
				
			}				
			
		}
	}
	private void AggiungiStrega(String nomeStrega,String Descrizione, String nomeStanza) throws FormatoFileNonValidoException {
			check(isStanzaValida(nomeStanza),"Strega"+ nomeStrega+" non collocabile: stanza " +nomeStanza+" inesistente");
			lab.addStrega(nomeStrega,Descrizione,nomeStanza);	
		}
		
	



	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result = new LinkedList<>();
		Scanner scanner = new Scanner(string);
		scanner.useDelimiter(", ");
		try (Scanner scannerDiParole = scanner) {
			while(scanner.hasNext())
				result.add(scannerDiParole.next());
		}
		return result;
	}


	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = null;
		nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
		check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale +" non definita");
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
		check(this.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");
		this.stanzaIniziale = lab.getStanza(nomeStanzaIniziale);
		this.stanzaVincente = lab.getStanza(nomeStanzaVincente);

		
	}

	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);

		for(String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo "+nomeAttrezzo+"."));
				nomeStanza = scannerLinea.next();
				
				
			}				
			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}
	}

	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			check(isStanzaValida(nomeStanza),"Attrezzo "+ nomeAttrezzo+" non collocabile: stanza " +nomeStanza+" inesistente");
			lab.addAttrezzo(nomeStanza, attrezzo);
		}
		catch (NumberFormatException e) {
			check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
		}
	}


	private boolean isStanzaValida(String nomeStanza) {
		return this.nome2stanza.containsKey(nomeStanza);
	}

	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);
		try (Scanner scannerDiLinea = new Scanner(specificheUscite)) {			
		
			while (scannerDiLinea.hasNext()) {
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("le uscite di una stanza."));
				String stanzaPartenza = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la direzione di una uscita della stanza "+stanzaPartenza));
				String dir = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la destinazione di una uscita della stanza "+stanzaPartenza+" nella direzione "+dir));
				StringBuilder stanzaDestinazione= new StringBuilder(scannerDiLinea.next());
				if(stanzaDestinazione.charAt(stanzaDestinazione.length()-1)==',')
					stanzaDestinazione.deleteCharAt(stanzaDestinazione.length()-1);
				String StanzaDest =stanzaDestinazione.toString();
				impostaUscita(stanzaPartenza, dir, StanzaDest);
				//lab.addAdiacenza(stanzaPartenza, StanzaDest, dir);
				//lab.addAdiacenza(StanzaDest, stanzaPartenza, Direction.valueOf(dir.toUpperCase()).opposta().toString());
			
			
			}
		} 
	}
	
	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere "+msg;
	}

	private void impostaUscita(String stanzaDa, String dir, String nomeA) throws FormatoFileNonValidoException {
		check(isStanzaValida(stanzaDa),"Stanza di partenza sconosciuta "+dir);
		check(isStanzaValida(nomeA),"Stanza di destinazione sconosciuta "+ dir);
		//Stanza partenzaDa = this.nome2stanza.get(stanzaDa);
		//Stanza arrivoA = this.nome2stanza.get(nomeA);
		//partenzaDa.impostaStanzaAdiacente(dir, arrivoA);
		
		lab.addAdiacenza(stanzaDa,nomeA,dir);
		lab.addAdiacenza(nomeA, stanzaDa,Direction.valueOf(dir.toUpperCase()).opposta().toString() );
	}
	
	
	
	


	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.reader.lines() + "] "+messaggioErrore);		
	}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}
}