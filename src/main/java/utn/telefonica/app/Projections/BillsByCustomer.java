package utn.telefonica.app.projections;

public interface BillsByCustomer {

    String getTotalPrice();

    String getCreatedAt();

    int getTotalCalls();

    boolean getIsPayed();
}
