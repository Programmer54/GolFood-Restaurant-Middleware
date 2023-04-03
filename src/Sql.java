import java.sql.*;

public class Sql {
    public Connection Giris(Boolean reset)
    {
        
        String K_Adi=null,Sifre=null;
        

        K_Adi=System.console().readLine("Veri tabanı kullanıcı adı:");
        Sifre=System.console().readLine("Veri tabanı Şifre:");
        Connection con;
        if(reset)
        {
            try {
                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "root");
                System.out.println("Varsayılan oturum ile giriş veri tabanı girişi yapıldı");
                return con;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        try {
            if(!K_Adi.equals("") && !Sifre.equals(""))
            {
                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db", K_Adi, Sifre);
                return con;
            }
            else
            {
                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "root");
                System.out.println("Varsayılan oturum ile giriş veri tabanı girişi yapıldı");
                return con;
            }

        } catch (SQLException e) {
                if(e.getErrorCode()==1045)
                {
                    System.out.println("Kullanıcı adı veya şifre hatalı");
                    return null;
                }
                System.out.println(e.getMessage());
                return null;
            }
        
        
        
    }
}
