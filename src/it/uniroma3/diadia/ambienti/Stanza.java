package it.uniroma3.diadia.ambienti;

import java.util.Map;

import java.util.HashMap;

import java.util.LinkedList;
import java.util.List;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
*/

public class Stanza implements Comparable<Stanza>{
	
	//static final private int NUMERO_MASSIMO_DIREZIONI = 4;

	private String nome;
    private List<Attrezzo> attrezzi;
    private Map<Direction,Stanza> StanzaAdiacenti;
    private AbstractPersonaggio personaggio;

    public enum Direction {
    	NORD() {
    		@Override public Direction opposta() {
    			return SUD;
    		}
    	},
    	OVEST(){
    		@Override 
    			public Direction opposta() {
    				return EST;
    			}
    		},
    	EST() {
    		@Override 
    			public Direction opposta() {
    				return OVEST;
    			}
    		},
    		SUD() {
    			@Override public Direction opposta() {
    				return NORD;
    			}
    		};
    		public abstract Direction opposta();
    	}
    /**
     * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
     * @param nome il nome della stanza
     */
    public Stanza(String nome) {
        this.nome = nome;
        this.StanzaAdiacenti =new HashMap<>();
        this.attrezzi = new LinkedList<Attrezzo>();
        Direction.valueOf("NORD").opposta();
    }

    /**
     * Imposta una stanza adiacente.
     *
     * @param direzione direzione in cui sara' posta la stanza adiacente.
     * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
     */
    public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
    	try {
    	if(this.getStanzaAdiacente(direzione)!=null)
    		this.StanzaAdiacenti.put(Direction.valueOf(direzione.toUpperCase()), stanza);
    	else {
    		if(this.getMapStanzeAdiacenti().size()<4)
    			this.StanzaAdiacenti.put(Direction.valueOf(direzione.toUpperCase()), stanza);
    		
    	}
    	}catch(IllegalArgumentException e) {
    		
    	};
    }

    /**
     * Restituisce la stanza adiacente nella direzione specificata
     * @param direzione
     */
	public Stanza getStanzaAdiacente(String direzione) {
		try {
        return this.StanzaAdiacenti.get(Direction.valueOf(direzione.toUpperCase()));
        }catch(IllegalArgumentException e ) {

      
        return null;
        }
	}

    /**
     * Restituisce la nome della stanza.
     * @return il nome della stanza
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Restituisce la descrizione della stanza.
     * @return la descrizione della stanza
     */
    public String getDescrizione() {
        return this.toString();
    }

    /**
     * Restituisce la collezione di attrezzi presenti nella stanza.
     * @return la collezione di attrezzi nella stanza.
     */
    public List<Attrezzo> getAttrezzi() {
        return this.attrezzi;
    }

    /**
     * Mette un attrezzo nella stanza.
     * @param attrezzo l'attrezzo da mettere nella stanza.
     * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
     */
    public boolean addAttrezzo(Attrezzo attrezzo) {
    	if(!hasAttrezzo(attrezzo.getNome()))
    		return this.attrezzi.add(attrezzo);
    	else return false;
    }

   /**
	* Restituisce una rappresentazione stringa di questa stanza,
	* stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	* @return la rappresentazione stringa
	*/
    public String toString() {
    	StringBuilder risultato = new StringBuilder();
    	risultato.append(this.nome+"\n");
    	risultato.append("Direzioni stanza:");
    	for ( Direction a : this.getDirezioni())
    			risultato.append(" " + a.toString().toLowerCase());
    	risultato.append("\nAttrezzi nella stanza: ");
    	for (Attrezzo attrezzo : this.attrezzi) {
    			risultato.append(attrezzo.toString()+";");
    	}
    	if(personaggio!=null)
    		risultato.append("\nPersonaggio: "+personaggio.getNome()+".");
    	return risultato.toString();
    }

    /**
	* Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	* @return true se l'attrezzo esiste nella stanza, false altrimenti.
	*/
	public boolean hasAttrezzo(String nomeAttrezzo) {
		for (Attrezzo attrezzo : this.attrezzi) {
			if (attrezzo.getNome().equals(nomeAttrezzo))
				return true;
		}
		return false;
	}

	/**
     * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
     * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		
		for(Attrezzo a : this.attrezzi) {
			if(a.getNome().equals(nomeAttrezzo))
				return a;
		}
		return null;
	}
	

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	

	public boolean removeAttrezzo(Attrezzo attrezzo) {
		return this.attrezzi.remove(attrezzo);
	}

	public List<Direction> getDirezioni() {
		final List<Direction> Direzioni= new LinkedList<>();
		for(Direction direzione: this.StanzaAdiacenti.keySet())
			Direzioni.add(direzione);
		
		return Direzioni;
    }
	
	public Map<Direction, Stanza>getMapStanzeAdiacenti(){
		
		return this.StanzaAdiacenti;
	}
	
	
	@Override
	public int compareTo(Stanza that) {
		return this.getNome().compareTo(that.getNome());
		
	}

	public AbstractPersonaggio getPersonaggio() {
	
		return this.personaggio;
	}
	public void SetPersonaggio(AbstractPersonaggio personaggio) {
		this.personaggio=personaggio;
	}
	
	public boolean hasPersonaggio() {
		return personaggio!=null;
	}


}
	
	

