package utn.telefonica.app.utils;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import utn.telefonica.app.model.User;

import java.net.URI;

public class UriUtils {

    public static URI getLocation(User user) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
    }
}
