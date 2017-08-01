package classes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class JsonAyristir {
    private ArrayList<VeriSeti> veriSeti;

    public JsonAyristir(){
    	veriSeti= new ArrayList<VeriSeti>();
    }

    public ArrayList<VeriSeti> dosyaUrlveKonumGetir(String jsonDosyasi) {

        String jsonData = "";
        BufferedReader br = null;
        try {
            String line;
            br = new BufferedReader(new FileReader(jsonDosyasi));
            while ((line = br.readLine()) != null) {
                jsonData += line + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        try {
            JSONObject obj = new JSONObject(jsonData);
            JSONArray array = obj.getJSONArray("dosya");
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                String url = object.getString("url");
                String konum = object.getString("konum");
                String sure = object.getString("sure");
                veriSeti.add((new VeriSeti(url,konum,sure)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return veriSeti;
    }
}
