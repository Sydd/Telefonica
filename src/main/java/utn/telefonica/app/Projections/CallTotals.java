package utn.telefonica.app.projections;

public interface CallTotals {

    String getDate();

    String getOriginPhoneline();

    String getDestinyNumber();

    String getPrice();

    String getDestinyCity();

    String getOriginCity();

    String getDuration();

    void setDuration(String duration);

    void setDate(String date);

    void setPrice(String price);

    void setOriginPhoneline(String phoneline);

    void setDestinyNumber(String number);

    void setOriginCity(String city);

    void setDestinyCity(String city);


}
