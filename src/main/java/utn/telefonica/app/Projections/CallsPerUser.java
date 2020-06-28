package utn.telefonica.app.Projections;

import java.util.Date;

public interface CallsPerUser {

    String getDateCall();
    String getDuration();
    String getTotalPrice();

    void setDateCall(Date dateCall);
    void setDuration(float duration);
    void setTotalPrice(float totalPrice);
}
