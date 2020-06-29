package utn.telefonica.app.service;

import org.springframework.dao.EmptyResultDataAccessException;
import utn.telefonica.app.exceptions.*;
import utn.telefonica.app.model.PhoneLine;
import utn.telefonica.app.repository.LineRepository;

import org.junit.Before;
import org.junit.Test;
import utn.telefonica.app.model.User;
import utn.telefonica.app.model.enums.UserType;
import utn.telefonica.app.projections.UserProjection;
import utn.telefonica.app.repository.UserRepository;
import utn.telefonica.app.service.UserService;
import utn.telefonica.app.testutils.TestUtils;
import utn.telefonica.app.utils.PhoneUtils;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

public class TestLineService {

    LineService lineService;

    LineRepository lineRepository;


    @Before
    public void setUp() {
        lineRepository = mock(LineRepository.class);

        lineService = new LineService(lineRepository);
    }

    @Test
    public void testGetLineById() throws LineNotFoundException {
        when(lineRepository.findById(1)).thenReturn(Optional.ofNullable(TestUtils.getTestingPhoneline()));
        assertEquals(lineService.getLineById(1).getId(), 1);

    }

    @Test(expected = LineNotFoundException.class)
    public void testGetLineByIdException() throws LineNotFoundException {
        when(lineRepository.findById(1)).thenReturn(Optional.ofNullable(null));
        assertEquals(lineService.getLineById(1).getId(), 1);
    }

    @Test
    public void testGetAllLinesNoNumber() {
        List<PhoneLine> phoneLines = new ArrayList<>();

        phoneLines.add(TestUtils.getTestingPhoneline());

        when(lineRepository.findAll()).thenReturn(phoneLines);

        assertEquals(lineService.getAllLines(null).get(0).getId(), 1);

    }


    @Test
    public void testGetAllLinesWithNumber() {

        List<PhoneLine> phoneLines = new ArrayList<>();

        phoneLines.add(TestUtils.getTestingPhoneline());

        when(lineRepository.findByLineNumber("1234")).thenReturn(phoneLines);

        assertEquals(lineService.getAllLines("1234").get(0).getId(), 1);

    }

    @Test
    public void testUpdatePholine() throws InvalidPhoneLineException{

        PhoneLine aux = TestUtils.getTestingPhoneline();

        aux.setState(false);

        when(lineRepository.findById(1)).thenReturn(Optional.of(TestUtils.getTestingPhoneline()));

        assertEquals(lineService.updatePhonelines(aux).isState(),aux.isState());

    }


    @Test
    public void testDeleteLine() throws LineNotFoundException  {
        assertEquals(lineService.deleteLine(2),"Line 2 deleted.");
    }


}
