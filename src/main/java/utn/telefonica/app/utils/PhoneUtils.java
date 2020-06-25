package utn.telefonica.app.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import utn.telefonica.app.exceptions.InvalidSessionException;
import utn.telefonica.app.exceptions.ValidationException;
import utn.telefonica.app.model.Call;
import utn.telefonica.app.model.PhoneLine;
import utn.telefonica.app.model.User;
import utn.telefonica.app.session.Session;
import utn.telefonica.app.session.SessionManager;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Component
public class PhoneUtils {

    SessionManager sessionManager;

    static PhoneUtils phoneUtils;

    @Autowired
    public PhoneUtils(SessionManager sessionManager){

        this.sessionManager = sessionManager;

        phoneUtils = this;
    }

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

    public static Session getUserByToken(String token) throws InvalidSessionException {
        return  Optional.
                ofNullable(getSessionManager().getSession(token)).
                orElseThrow(() -> new InvalidSessionException());

    }

    public static SessionManager getSessionManager(){
        return phoneUtils.sessionManager;
    }
}
