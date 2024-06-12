package it.uniroma3.diadia.giocatore;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.io.LineNumberReader;
import java.util.Collections;
import java.util.HashMap;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.attrezzi.Attrezzo;


public class Borsa {
	
	
	public int peso_borsa_default;
	private List<Attrezzo> attrezzi;

	private int pesoMax;
	
	public Borsa() throws FileNotFoundException, IOException {
		Properties prop = new Properties();
		try {
			
		prop.load(new LineNumberReader(new FileReader(("resources/p/diadia.properties"))));
		peso_borsa_default=(Integer.parseInt(prop.getProperty("peso_max_borsa")));}
		catch(Exception e){
			peso_borsa_default= 10;
		}
		this.pesoMax = peso_borsa_default;
		this.attrezzi = new LinkedList<>(); 
	}
	
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new LinkedList<>(); 
		
		
		
		
	}
	
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(this.attrezzi.contains(attrezzo))
			return false;
		if(this.getPeso()+attrezzo.getPeso()<=this.getPesoMax())
			return this.attrezzi.add(attrezzo);
		return false;
	}
	
	public int getPesoMax() {
		return pesoMax;
	}
	
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		for(Attrezzo a:this.attrezzi) {
			if(a.getNome().equals(nomeAttrezzo))
				return a;
		}
		return null;
	}
	
	public int getPeso(){
		int peso = 0;
		for (Attrezzo a:this.attrezzi)
			peso += a.getPeso();
		return peso;
	}
		
	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}
	
	public boolean hasAttrezzo(String nomeAttrezzo) {
		for (Attrezzo attrezzo : this.attrezzi) {
			if (attrezzo.getNome().equals(nomeAttrezzo))
				return true;
		}
		return false;
	}	
	
	
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
	
		Attrezzo a = this.getAttrezzo(nomeAttrezzo);
		this.attrezzi.remove(this.getAttrezzo(nomeAttrezzo));
		return a;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for (Attrezzo a:this.attrezzi)
				s.append(a.getNome().toString()+" ");
			
		} else
			s.append("Borsa vuota");
			
			return s.toString();
	}
	
	public int getNumeroAttrezzi() {
		return this.attrezzi.size();
	}
	public List<Attrezzo>getContenutoOrdinatoPerPeso(){
		final List<Attrezzo> ordinata= new LinkedList<>(this.attrezzi);
		final ComparatoreAttrezziPerPeso cmp = new ComparatoreAttrezziPerPeso();
		Collections.sort(ordinata,cmp);
		return ordinata;
		
	}
	
	/*restituisce l'insieme degli attrezzi nella borsa ordinati per nome*/
	
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		final SortedSet<Attrezzo> ordinata= new TreeSet<>(this.attrezzi);
		return ordinata;
	}
	
	
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
		final SortedSet<Attrezzo> ordinata= new TreeSet<>(new ComparatoreAttrezziPerPeso());
		ordinata.addAll(this.attrezzi);
		return ordinata;
	}
	
	
	
	
	/*restituisce una mappa che associa un intero (rappresentante un
	peso) con l’insieme (comunque non vuoto) degli attrezzi di tale
	peso: tutti gli attrezzi dell'insieme che figura come valore hanno
	lo stesso peso pari all'intero che figura come chiave*/
	
	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		final Map<Integer,Set<Attrezzo>> peso2Attrezzi = new HashMap<>();
		
		for(Attrezzo a:this.attrezzi) {
			if(peso2Attrezzi.containsKey(a.getPeso())) {
				//Attrezzo con peso gia visto in precedenza;
				//peso il vecchio set e aggiungo il vecchio set senza questo
				//attrezzzo aggiungendo il nuov peso;
				//
				final Set<Attrezzo> AttrezzoStessoPeso= peso2Attrezzi.get(a.getPeso());
				AttrezzoStessoPeso.add(a);
	
			}
			else {
				//Questo attrezzo ha un peso mai visto prima;
				//creo un nuov set per ospitare un nuovo set correnti e futuri dello stesso peso
				final Set<Attrezzo> set = new TreeSet<>();
				set.add(a);
				peso2Attrezzi.put(a.getPeso(), set);
			}
			
			
		}
		
		return peso2Attrezzi;			
	}
	public List<Attrezzo> getAttrezzi(){
		return this.attrezzi;
	}
}





