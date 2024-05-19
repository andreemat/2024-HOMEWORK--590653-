package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoGuarda implements Comando {

	private String nome;
	private String parametro;
	
	public ComandoGuarda() {
		this.nome="guarda";

	}
	@Override
	public void esegui(Partita partita) {
		if(this.parametro==null) {
			partita.getIO().mostraMessaggio("Cosa vuoi guardare? borsa o stanza?");
			return;
		}
		if(parametro.equals("borsa")){
			
			
			StringBuilder risultato = new StringBuilder();
	    	risultato.append("\nContenuto ordinato per peso: \n");
	    	risultato.append("[");
	    	for ( Attrezzo a : partita.getGiocatore().getBorsa().getContenutoOrdinatoPerPeso()) {
	    		risultato.append(a+",");
	    		
	    	}
	    	if(risultato.charAt(risultato.length()-1)==',')
	    		risultato.deleteCharAt(risultato.length()-1);
	    		
	    
	    	risultato.append("]");
	    	partita.getIO().mostraMessaggio(risultato.toString());
	    	risultato.setLength(0);
	    	
	    	
	    	
	    	
	    	risultato.append("\nContenuto ordinato per nome: \n");
	    	risultato.append("{");
	    	for ( Attrezzo a : partita.getGiocatore().getBorsa().getContenutoOrdinatoPerNome())
    			risultato.append(" " + a+",");
	    	if(risultato.charAt(risultato.length()-1)==',')
	    		risultato.deleteCharAt(risultato.length()-1);
	    	risultato.append("}");
	    	partita.getIO().mostraMessaggio(risultato.toString());
	    	risultato.setLength(0);
	    	
	    	
	    	
	    	
	    	
	    	risultato.append("\nContenuto raggruppato per peso: \n");
	    	risultato.append("(");
	    	for ( int a : partita.getGiocatore().getBorsa().getContenutoRaggruppatoPerPeso().keySet()) {
	    		risultato.append("(");
    			risultato.append( a+","+"{");
    			for ( Attrezzo x : partita.getGiocatore().getBorsa().getContenutoRaggruppatoPerPeso().get(a)) {
    				risultato.append( x+",");
    				if(risultato.charAt(risultato.length()-1)==',')
    		    		risultato.deleteCharAt(risultato.length()-1);}
    			risultato.append( "}");
    			risultato.append(");");
	    	}
	    	if(risultato.charAt(risultato.length()-1)==',')
	    		risultato.deleteCharAt(risultato.length()-1);
	    	if(risultato.charAt(risultato.length()-1)==';')
	    		risultato.deleteCharAt(risultato.length()-1);
	    	risultato.append(")");
	    	partita.getIO().mostraMessaggio(risultato.toString());
	    	risultato.setLength(0);
		}
		else {
			if(parametro.equals("stanza")) {
				partita.getIO().mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
				
			}
			else partita.getIO().mostraMessaggio("Comando: guarda "+this.parametro+" inesistente.");
		}
		
		
	}

	@Override
	public void setParametro(String parametro) {
		this.parametro=parametro;
	}
	@Override
	public String getNome() {
		return this.nome;
	}
	@Override
	public String getParametro() {
		return this.parametro;
	}

}
