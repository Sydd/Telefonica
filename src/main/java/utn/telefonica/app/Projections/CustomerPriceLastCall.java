package utn.telefonica.app.projections;

public interface CustomerPriceLastCall {

    String getName();

    String getDni();

    String getPrice();

    String setPrice(String price);

    String setDni(String dni);

    String setName(String name);


}
