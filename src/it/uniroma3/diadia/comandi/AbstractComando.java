package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public abstract class AbstractComando {
	String nome;
	protected String parametro;
	 
	
	 public String getNome() {
		 return this.getNome();
	 }
	 public void setNome(String nome) {
		this.nome= nome;
	 }
	

	 
	 
	 public abstract void esegui(Partita partita);
	 
	 
	 
	  public void setParametro(String parametro) {
		  
	  }
	  
	
		public String getParametro() {
			return this.parametro;
		}

	
	
}
