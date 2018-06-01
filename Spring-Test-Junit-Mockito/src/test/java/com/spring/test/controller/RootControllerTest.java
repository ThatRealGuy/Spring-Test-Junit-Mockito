package com.spring.test.controller;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.spring.test.entity.Employee;
import com.spring.test.service.EmployeeService;


@RunWith(SpringJUnit4ClassRunner.class)
public class RootControllerTest {

	@Mock
	private EmployeeService employeeService;
	@InjectMocks
	private RootController rootController;
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(rootController).build();
	}
	
	@Test
	public void testGetAll() throws Exception {
		List<Employee> employees = new ArrayList<>();
		employees.add(new Employee());
		employees.add(new Employee());
		
		when(employeeService.getAll()).thenReturn(employees);
		
		mockMvc.perform(get("/user/getAll"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$", hasSize(2)));
		
		Mockito.verify(employeeService).getAll();
	}
	
	@Test
	public void testGetById() throws Exception {
		
		Long id=(long) 1;
		Employee employee = new Employee();
		employee.setEmp_id(id);
		
		when(employeeService.getByID(id)).thenReturn(employee);
		
		mockMvc.perform(get("/user/getAll/1"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.emp_id", is(1)));
		
		Mockito.verify(employeeService).getByID(id);
	}
	
	@Test
	public void testAdd() throws Exception {
	
		String json = "{\r\n" + 
				"	\"fname\": \"java\",\r\n" + 
				"	\"lname\": \"oracle\",\r\n" + 
				"	\"mobile\": 80808080\r\n" + 
				"}";
		
		Employee employee = new Employee();
		employee.setFname("jack");
		when(employeeService.add(employee)).thenReturn(employee);
		
		mockMvc.perform(post("/user/add")
		.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
		.content(json))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$.fname",is("jack")));
		
		Mockito.verify(employeeService).add(employee);
	}
	
    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(
                delete("/user/delete/3"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("success", is(true)));
    }	
}
