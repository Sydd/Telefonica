package utn.telefonica.app.projections;

import utn.telefonica.app.model.City;
import utn.telefonica.app.model.PhoneLine;
import utn.telefonica.app.model.enums.UserType;
import utn.telefonica.app.utils.PhoneUtils;

import java.util.List;

public interface UserProjection {

    int getId();

    void setId(int id);

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    UserType getUserType();

    void setUserType(UserType userType);

    String getUsername();

    void setUsername(String username);

    String getDni();

    void setDni(String dni);

    List<PhoneLine> getPhoneLines();

    void setPhoneLines(PhoneLine phoneLines);

    City getCity();

    void setCity(City city);

}
