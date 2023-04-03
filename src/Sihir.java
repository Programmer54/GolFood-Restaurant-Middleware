import java.io.Serializable;

public class Sihir implements Serializable {
    İslem islem;
    String jsonObject;
    Object object;
}
enum İslem{
    KONTROL,
    GİRİS,
    KAYİT,
    SİPARİS,
    BEKLEYEN_SİPARİS,
    YORUMLAR,
    MENU,
    MENU_EKLE,
}
