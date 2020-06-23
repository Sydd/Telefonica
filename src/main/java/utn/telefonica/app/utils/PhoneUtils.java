package utn.telefonica.app.utils;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import utn.telefonica.app.exceptions.ValidationException;
import utn.telefonica.app.model.Call;
import utn.telefonica.app.model.User;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PhoneUtils {

    public static URI getLocation(User user) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
    }

    public static Date dateConverter(String toConvert) throws ParseException {
        Date aux = new SimpleDateFormat("dd-MM-yyyy").parse(toConvert);
        return aux;
    }
}
