package utn.telefonica.app.projections;

public interface BillsByCustomer {

    String getTotalPrice();

    String getCreatedAt();

    int getTotalCalls();

    boolean getIsPayed();

    void setTotalPrice(String totalPrice);

    void setCreatedAt(String createdAt);

    void setTotalCalls(int totalCalls);

    void setIsPayed(boolean isPayed);
}
