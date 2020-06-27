package utn.telefonica.app.projections;

public interface BillsByCustomer {

    String getTotalPrice();

    String getCreatedAt();

    int getTotalCalls();

    boolean getIsPayed();

    String getDueDate();

    void setTotalPrice(String totalPrice);

    void setDueDate(String dueDate);

    void setCreatedAt(String createdAt);

    void setTotalCalls(int totalCalls);

    void setIsPayed(boolean isPayed);
}
