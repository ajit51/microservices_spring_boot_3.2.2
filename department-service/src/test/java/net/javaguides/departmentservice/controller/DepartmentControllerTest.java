package net.javaguides.departmentservice.controller;

import net.javaguides.departmentservice.entity.Department;
import net.javaguides.departmentservice.service.DepartmentService;
import net.javaguides.departmentservice.service.impl.DepartmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes = DepartmentControllerTest.class)
public class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    private DepartmentController departmentController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(DepartmentController.class).build();
    }
    /*@Test
    public void saveDepartmentTest() throws Exception {
        // given - setup or precondition
        Department department = Department.builder().departmentName("Developer").departmentCode("101").departmentAddress("Pune").build();
        departmentService.saveDepartment(department);

        // when - action
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/departments"));

        // then - verify the output
        response.andExpect(MockMvcResultMatchers.status().isOk());

    }*/

}
