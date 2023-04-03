import java.sql.Connection;
import java.util.ArrayList;

public class App {
    static Sql sql=new Sql();
    static String commond;//Satırdan alınan veri
    static boolean loop=true;//Temel sunucu döngüsü
    static Connection con=sql.Giris(false);//Parametre "sunucu reset edilirse" true verilir.
    static ArrayList<Client> Baglantilar=new ArrayList<>();
    static Server server=new Server();
    
    
    public static void main(String[] args) {

        if(con==null)
        {
            System.out.println("Veri tabanı bağlantı hatası");
        }
        //server.start();
       while(loop)
       {
            commond=System.console().readLine(">");
            switch(commond)
            {
                case "exit":
                {
                    loop=false;

                }break;

                default:
                {
                    System.out.println("Hatalı komut. Yardım için \"help\" komutunu deneyin");
                }break;
            }


       }
    }
}
