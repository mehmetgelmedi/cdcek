package classes;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Timer;
import java.util.TimerTask;

public class Dosya implements Runnable {
	
	private URI url;
	private Path path;
	private Timer timer;
	private int sure;
	
	public Dosya(String url, String konum, String sure) {
		try {
			this.url=new URI(url);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		path =Paths.get(konum+getUzanti());
		timer =new Timer();
		this.sure = Integer.parseInt(sure);
	}
	
	public Runnable DosyaCek() {
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				System.out.println(getUzanti()+" ,indirilmeye baþlandi.");
				try (InputStream in = url.toURL().openStream()) {
				    Files.copy(in, path, StandardCopyOption.REPLACE_EXISTING);
				    in.close();
				    System.out.println(getUzanti()+" ,dosyasi belirtilen konuma indirildi.");
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
		}, 0, 1000*3600*sure); //ne zaman(þimdi) , ne kadar surede(saat bazinda)
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

	@Override
	public void run() {
		DosyaCek();
	}

}
