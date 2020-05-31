package utn.telefonica.app.projections;

import utn.telefonica.app.model.Call;

import java.util.List;

public interface CustomerCalls {

    String getFirstName();

    List<Call> getCalls();

}
