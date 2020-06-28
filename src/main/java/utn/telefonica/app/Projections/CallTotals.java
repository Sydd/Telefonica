package utn.telefonica.app.Projections;

public interface CallTotals {

    String getDate();

    String getPhoneline();

    float getCost();

    void setDate(String date);

    void setCost(float cost);

    void setPhoneline(String phoneline);

}
