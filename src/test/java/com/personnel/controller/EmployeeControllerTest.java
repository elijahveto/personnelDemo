package com.personnel.controller;

import com.personnel.request.EmployeePatchRequest;
import org.hamcrest.Matchers;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class EmployeeControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void update() throws Exception {

        mvc.perform(patch("/employee/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(getJsonInput(new EmployeePatchRequest("Erin", "Hannon","01.01.1990", null))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", Matchers.is("Erin")))
                .andExpect(jsonPath("$.department", Matchers.is("Personal")));

    }

    @Test
    void updateWithValidationOfBirthday() throws Exception {

        MvcResult response = mvc.perform(patch("/employee/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(getJsonInput(new EmployeePatchRequest("Erin", "Hannon","today", null))))
                .andReturn();

        assertEquals("{\"birthday\":\"Birthday Format must be 'dd.mm.yyyy'\"}", response.getResponse().getContentAsString());
    }

    @Test
    void updateWithValidationOfDepartment() throws Exception {

        MvcResult response = mvc.perform(patch("/employee/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(getJsonInput(new EmployeePatchRequest("Erin", "Hannon","01.01.1990", "Quabity Assuance"))))
                .andReturn();

        assertEquals("{\"department\":\"must be any of enum 'Personal, Verkauf, Entwicklung'\"}", response.getResponse().getContentAsString());
    }

    public static String getJsonInput(EmployeePatchRequest request) {

            JSONObject json = new JSONObject();
            request.firstName.ifPresent(value -> {
                try {
                    json.put("firstName", value);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            });
            request.lastName.ifPresent(value -> {
                try {
                    json.put("lastName", value);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            });
            request.birthday.ifPresent(value -> {
                try {
                    json.put("birthday", value);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            });
            request.department.ifPresent(value -> {
                try {
                    json.put("department", value);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            });

            return json.toString();
    }
}