package utn.telefonica.app.projections;

import java.util.Date;

public interface CallsPerUser {

    String getDateCall();
    String getDuration();
    String getTotalPrice();

    void setDateCall(Date dateCall);
    void setDuration(float duration);
    void setTotalPrice(float totalPrice);
}
