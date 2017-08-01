package classes;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;

public class Dosya implements Runnable {
	
	private URI url;
	private Path path;
	private Timer timer;
	private Date date;
	private String sure;
	private CountDownLatch countDownLatch;
	
	public Dosya(String url, String konum, String sure, CountDownLatch countDownLatch) {
		try {
			this.url=new URI(url);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		path =Paths.get(konum+getUzanti());
		timer =new Timer();
		date =new Date();
		this.sure =sure;
		this.countDownLatch=countDownLatch;
	}
	
	public Runnable DosyaCek() {
		sureAyristir();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				System.out.println(getUzanti()+" ,indirilmeye baþlandi.");
				try (InputStream in = url.toURL().openStream()) {
				    Files.copy(in, path, StandardCopyOption.REPLACE_EXISTING);
				    in.close();
				    System.out.println(getUzanti()+" ,dosyasi belirtilen konuma indirildi.");
				    countDownLatch.countDown();
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
		}, date, 1000*60*1440); //ne zaman , ne kadar surede(1 gun)
		return null;
	}
	public String getUzanti(){
		String dosyaAdi_uzanti = "";

		int i = url.toString().lastIndexOf('/');
		if (i > 0) {
			dosyaAdi_uzanti = url.toString().substring(i+1);
		}
		return dosyaAdi_uzanti;
	}
	public void sureAyristir(){
		SimpleDateFormat sdf = new SimpleDateFormat("d.M.yyyy hh:mm:ss");
		try {
			date =sdf.parse(sure);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		DosyaCek();
	}

}
