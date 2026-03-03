package org.springframework.samples.petclinic.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Vets;
import org.springframework.samples.petclinic.service.ClinicService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class VetControllerTest {

    @Mock
    ClinicService clinicService;

    @Mock
    Map<String, Object> model;

    @InjectMocks
    VetController vetController;

    List<Vet> vetsList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        vetsList.add(new Vet());
        given(clinicService.findVets()).willReturn(vetsList);
    }

    @Test
    void showVetList() {
        //Given
        String viewName = vetController.showVetList(model);

        //Then
        then(clinicService).should().findVets();
        then(model).should().put(anyString(), any());
        assertEquals("vets/vetList", viewName);

        verify(clinicService).findVets();
        verifyNoMoreInteractions(clinicService);
    }

    @Test
    void showResourcesVetList() {
        //Given
        Vets returnedVets = vetController.showResourcesVetList();

        //Then
        then(clinicService).should().findVets();
        assertEquals(1, returnedVets.getVetList().size());
        verifyNoMoreInteractions(clinicService);
    }
}