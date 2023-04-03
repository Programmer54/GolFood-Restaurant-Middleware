import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
    private ServerSocket server;
    private boolean loop=true;
    private Socket sock;

    Server()
    {
        try {
            server =new ServerSocket(15);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Sunucu nesnesi oluşturuldu");
        start();
    }

    public void Durdur()
    {
        loop=false;
        System.out.println("Sunucu durduruldu");
    }

   
    public void run()
    {
        while(loop)
        {
            try {
                sock=server.accept();
            } catch (IOException e) {
               
                System.out.println(e.getMessage());
            }
            App.Baglantilar.add(new Client(sock));
        }
    }
    
}
