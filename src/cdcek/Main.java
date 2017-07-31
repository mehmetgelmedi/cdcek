package cdcek;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import classes.Dosya;
import classes.JsonAyristir;

public class Main {

	private static final String jsonDosyasi = "src/dosya-konum.json";
	
	public static void main(String[] args) {
		//String sure ="31.07.2017 18:38:38";
		Hashtable<String, String> hashtable= new JsonAyristir()
				.dosyaUrlveKonumGetir(jsonDosyasi);
		
		//key = url , value = konum
		
		Set<String> keys=hashtable.keySet();
		Iterator<String> itr = keys.iterator();
		 
	    while (itr.hasNext()) { 
	       String str = itr.next();
	       //System.out.println("Key: "+str+" & Value: "+hashtable.get(str));
	       new Thread(new Dosya(str, hashtable.get(str), args[0]).DosyaCek()).start();
	    }
	    
	}
}
