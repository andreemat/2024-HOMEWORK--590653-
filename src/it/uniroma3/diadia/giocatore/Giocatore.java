package it.uniroma3.diadia.giocatore;

import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.io.LineNumberReader;
import java.util.Properties;

public class Giocatore {
	private int cfu_iniziali;
	private int cfu;
	private Borsa borsa;
	private BufferedReader reader;
	
	public Giocatore() throws FileNotFoundException, IOException{
		Properties prop = new Properties();
		String nomeFile = "resources/p/diadia.properties";
	        this.reader = new LineNumberReader(new FileReader(nomeFile));
		prop.load(reader);
 		cfu_iniziali=(Integer.parseInt(prop.getProperty("cfu_iniziali"))); 
		this.cfu = cfu_iniziali;
		this.borsa = new Borsa();
	}
	
	public int getCfu() {
		return cfu;
	}
	public void setCfu(int cfu) {
		this.cfu = cfu;
	}
	public Borsa getBorsa() {
 		return this.borsa;
	}
	

	
}
