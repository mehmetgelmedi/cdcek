package cdcek;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import classes.Dosya;
import classes.JsonAyristir;
import classes.VeriSeti;

public class Main {

	private static final String jsonDosyasi = "src/dosya-konum.json";
	
	public static void main(String[] args) {
		//String sure ="31.07.2017 18:38:38";
		ArrayList<VeriSeti> veriSetis=new JsonAyristir().dosyaUrlveKonumGetir(jsonDosyasi);
		CountDownLatch countDownLatch = new CountDownLatch(veriSetis.size());
		for(VeriSeti veriSeti : veriSetis)
		{
			//System.out.println(veriSeti.getUrl()+" "+veriSeti.getKonum()+" "+veriSeti.getSure());
			new Thread(new Dosya(veriSeti.getUrl()
					,veriSeti.getKonum()
					,veriSeti.getSure()
					,countDownLatch)
					.DosyaCek()).start();			
		}
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Tum indirmeler tamamlandi.");
		System.exit(0);
	}
}
