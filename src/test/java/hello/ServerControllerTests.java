package hello;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import hello.com.mvc.api.entities.Student;
import hello.com.mvc.api.service.IStudentService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Base64Utils;
import org.springframework.web.context.WebApplicationContext;


import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ServerControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;


    @Autowired
    WebApplicationContext context;

    @Autowired
    FilterChainProxy springSecurityFilterChain;

    @MockBean
    IStudentService studentService;


    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilter(springSecurityFilterChain).build();

    }

    //test to save data
    @Test
    public void saveStudent() throws Exception {
        Student oneS = new Student();
        oneS.setMajor("dance4");
        oneS.setName("kate");

        Mockito.when(studentService.addStudent(oneS)).thenReturn(true);

        MvcResult mvcResult2 = this.mockMvc.perform(post("/user/student")
                .header("Authorization", "Bearer"+ getAccessToken("adr", "password"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(oneS)))
                .andReturn();

        System.out.println(mvcResult2.getResponse());

        ArgumentCaptor<Student> argument = ArgumentCaptor.forClass(Student.class);
        Mockito.verify(studentService).addStudent(argument.capture());
    }

    //test to get data
    @Test
    public void getStudents() throws Exception {

        List<Student> list = new ArrayList<Student>();
        Student oneS = new Student();
        oneS.setMajor("dance4");
        oneS.setName("kate");

        Student twoS = new Student();
        twoS.setMajor("art");
        twoS.setName("john");

        list.add(oneS);
        list.add(twoS);

        Mockito.when(studentService.getAllStudents()).thenReturn(list);

        MvcResult mvcResult = this.mockMvc.perform(get("/user/all-students")
                .header("Authorization", "Bearer"+ getAccessToken("adr", "password"))
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        System.out.println(mvcResult.getResponse());

        Mockito.verify(studentService).getAllStudents();


    }

    private String getAccessToken(String username, String password) throws Exception {
        MockHttpServletResponse response = this.mockMvc.perform(post("/oauth/token")
                        .header("Authorization", "Basic "
                                + new String(Base64Utils.encode(("studentapp:secret")
                                .getBytes())))
                        .param("username", username)
                        .param("password", password)
                        .param("grant_type", "password"))
                .andReturn().getResponse();

        return new ObjectMapper()
                .readValue(response.getContentAsByteArray(), OAuthToken.class)
                .accessToken;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class OAuthToken {
        @JsonProperty("access_token")
        public String accessToken;
    }





}
