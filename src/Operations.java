import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import org.json.JSONObject;



public class Operations {
    Connection con=App.con;
    Sihir sihir;

    
   Operations()
   {
        
   }

   public Sihir Kontrol(ArrayList<String> AndroidList)
   {
       sihir=new Sihir();
        /**
         * Uygulamanın menu kısmını kontrol eder. Eğer eksin ise gerekli resimleri indirmesi için bağlantı sağlar.
         */
        File file=new File("./Resimler/");
        ArrayList<String> list=new ArrayList<>();
        for (File item : file.listFiles()) {
            list.add(item.getName());
        }
        if(!list.equals(AndroidList))
        {
            
            list.removeAll(AndroidList);
            sihir.islem=İslem.KONTROL;
            sihir.jsonObject=null;
            ArrayList<File> files= new ArrayList<>();
            for (String file2 : list) {
                files.add(new File((String)("./Resimler/"+file2)));
            }

            sihir.object=files;
            return sihir;
        }
        return null;
   }

   public Sihir Giris(String K_Adi,String sifre)
   {
        sihir=new Sihir();
        JSONObject jsonObject=null;
        try {
            PreparedStatement giris= con.prepareStatement("select * from Kullanicilar where Mail=? and sifre=?");
            giris.setString(1, K_Adi);
            giris.setString(2, sifre);
            jsonObject=new JSONObject(giris.execute());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        if(jsonObject!=null)
        {
            jsonObject.remove("sifre");
            sihir.islem=İslem.GİRİS;
            sihir.jsonObject=jsonObject.toString();
            sihir.object=null;
            return sihir;
            
        }
        return null;

   }
}
