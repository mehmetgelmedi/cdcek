package classes;

public class VeriSeti {
	private String url;
	private String konum;
	private String sure;
	
	public VeriSeti(String url, String konum, String sure){
		this.url=url;
		this.konum=konum;
		this.sure=sure;
	}

	public String getUrl() {
		return url;
	}

	public String getKonum() {
		return konum;
	}

	public String getSure() {
		return sure;
	}
}
