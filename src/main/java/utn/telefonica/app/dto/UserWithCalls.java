package utn.telefonica.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import utn.telefonica.app.Projections.CallTotals;

import java.util.List;
@AllArgsConstructor
@Data
public class UserWithCalls {

    String completeName;

    List<CallTotals> callsWithDate;

}
