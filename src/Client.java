import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.json.JSONObject;

public class Client extends Thread {
    private Socket sock;
    public int id;
    private int Rol;
    private Operations operations=new Operations();
    private ObjectOutputStream Oos;
    private ObjectInputStream Ois;
    private Sihir sihir=new Sihir();
    private JSONObject jsonObject;

    

    Client(Socket sock)
    {
        this.sock=sock;
        
        try {
            sock.setSoTimeout(300000);
            Oos=new ObjectOutputStream(sock.getOutputStream());
            Ois=new ObjectInputStream(sock.getInputStream());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        start();
        
    }

    public void Durdur()
    {
        /**
         * İşlemi sonlandırmak için kullanılır. Bağlantıyı sonlandırır.
         */
        try {
            sock.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void run() 
    {
       try {
        while(!sock.isClosed())
        {
            
            try {
                sihir=null;
                sihir=(Sihir)Ois.readObject();
                jsonObject=new JSONObject(sihir.jsonObject);
            } catch (ClassNotFoundException | IOException e) {
                System.out.println(e.getMessage());
            }

            switch (sihir.islem) {
                case GİRİS:
                {
                   sihir=operations.Giris(jsonObject.getString("Mail"),jsonObject.getString("sifre"));
                   
                   if(sihir!=null)
                   {
                       id=jsonObject.getInt("idKullanicilar");
                       Rol=jsonObject.getInt("Rol");
                       Oos.writeObject(sihir);
                   }
                   else
                   {
                       Oos.writeObject(null);
                   }

                }break;

                case KAYİT:
                {
                    System.out.println("KAYIT ÇALIŞTI");
                }break;
            
                default:
                    break;
            }
        }

       } catch (Exception e) {
           try {
            sock.close();
           } catch (Exception ee) {}
       }
       finally
       {
           App.Baglantilar.remove(this);
       } 
    }
    
}
