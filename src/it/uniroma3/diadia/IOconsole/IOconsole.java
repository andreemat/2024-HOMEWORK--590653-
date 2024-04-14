
package it.uniroma3.diadia.IOconsole;
import java.util.Scanner;

public class IOconsole{
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}
	
	public String leggiRiga() {
		Scanner scannerDiLinee = new Scanner(System.in); 
		String riga = scannerDiLinee.nextLine(); 
//		scannerDiLinee.close();
		return riga;
	}
}
