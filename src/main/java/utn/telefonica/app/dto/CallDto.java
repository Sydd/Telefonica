package utn.telefonica.app.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CallDto {

    private String originNumber;

    private String destinyNumber;

    private int callDuration;

    private Date callDate;

}
