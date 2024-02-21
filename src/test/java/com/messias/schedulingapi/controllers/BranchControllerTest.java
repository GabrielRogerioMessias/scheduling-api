package com.messias.schedulingapi.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.messias.schedulingapi.domain.Branch;
import com.messias.schedulingapi.repositories.BranchRepository;
import com.messias.schedulingapi.services.BranchService;
import com.messias.schedulingapi.services.exceptionsServices.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = BranchController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class BranchControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Mock
    private BranchRepository branchRepository;
    @MockBean
    private BranchService branchService;

    @Autowired
    private ObjectMapper objectMapper;
    private Branch branch;

    @BeforeEach
    void setUp() {
        branch = new Branch(1, "TEST", "TEST");

    }

    @Test
    @DisplayName("When create branch return created")
    void insertCase1() throws Exception {
        given(branchService.insert(any(Branch.class))).willReturn(branch);

        ResultActions response = mockMvc.perform(post("/branchs").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(branch)));

        response.andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @DisplayName("When create branch return bad request")
    void insertCase2() throws Exception {
        ResultActions response = mockMvc.perform(post("/branchs").contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("When findAll method return a list")
    void findAll() throws Exception {
        // Criar uma lista fictícia de filiais para o teste
        List<Branch> branchList = Arrays.asList(new Branch(1, "TEST", "TEST"), new Branch(1, "TEST", "TEST"));

        // Configuração do mock para o serviço retornar branchList
        given(branchService.findAll()).willReturn(branchList);

        // Configuração do mock para o serviço retornar branchList
        ResultActions response = mockMvc.perform(get("/branchs").contentType(MediaType.APPLICATION_JSON));

        // Verifica se a resposta tem o status HTTP 200 OK
        response.andExpect(MockMvcResultMatchers.status().isOk())
                // Verifica se o corpo da resposta contém a representação JSON de branchList
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(branchList)));
    }

    @Test
    @DisplayName("when findById method return a object")
    void findByIdCase1() throws Exception {
        Integer idBranch = 1;
        given(branchService.findById(idBranch)).willReturn(branch);

        ResultActions response = mockMvc.perform(get("/branchs/{idbranch}", idBranch)
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(branch)));
        response.andExpect(MockMvcResultMatchers.status().isOk());
        response.andExpect(MockMvcResultMatchers.jsonPath("$.nameBranch").value("TEST"));
    }

    @Test
    @DisplayName("when the findById method does not return the object")
    void findByIdCase2() throws Exception {
        Integer idBranch = 1;
        given(branchService.findById(idBranch)).willThrow(new ResourceNotFoundException(Branch.class, idBranch));
        ResultActions response = mockMvc.perform(get("/branchs/{idBranch}", idBranch).contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void updateCase1() throws Exception {
        Integer idBranch = 1;
        Branch oldBranch = new Branch(idBranch, "TEST", "TEST");
        Branch updateBranch = new Branch(idBranch, "BRANCH UPDATE", "UPDATE");
        given(branchService.findById(idBranch)).willReturn(oldBranch);
        given(branchService.update(idBranch, updateBranch)).willReturn(updateBranch);

        ResultActions response = mockMvc.perform(put("/branchs/{idBranch}", idBranch)
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(oldBranch)));

        response.andExpect(MockMvcResultMatchers.status().isOk());
        response.andExpect(MockMvcResultMatchers.jsonPath("$.nameBranch").value("BRANCH UPDATE"));

    }

}