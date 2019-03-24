package com.taks.prueba.integracion;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.taks.prueba.TaksBackEndApplication;
import com.taks.prueba.entity.UsuarioEntity;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = WebEnvironment.MOCK,
		classes = TaksBackEndApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:application.properties")
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
public class UsuarioIntegrationTest {
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	@Autowired
	private  WebApplicationContext weContext;
	

	public UsuarioEntity usuarioEntity;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Rule  
	public JUnitRestDocumentation jUnitRestDocumentation = new JUnitRestDocumentation();
	
	
	@Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(weContext)
        		.apply(documentationConfiguration(this.jUnitRestDocumentation)).build();
        
        usuarioEntity = new UsuarioEntity();
		usuarioEntity.setNombre("catalina");
		usuarioEntity.setApellido("alvarez");
		usuarioEntity.setEmail("Gco@ymail.com");
		usuarioEntity.setPassword("123456");
		usuarioEntity.setActive(true);
		usuarioEntity.setCreateBy("catalina alvarez");
		usuarioEntity.setCreateDt("2019-02-12");
		
    }
	
	@Test
	public  void  createUsuarioTest() throws Exception {

	    
	    this.mockMvc.perform(RestDocumentationRequestBuilders.post("/v1/api/signUp").contentType(APPLICATION_JSON_UTF8)
	            .content(this.ObjectoMapers(this.usuarioEntity)))
	            .andExpect(status().is(201)).andDo(document("usuario/registro"));
	
	}
	
	@Test 
	public void loginTest() throws JsonProcessingException, Exception {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("email",this.usuarioEntity.getEmail());
		map.put("password", this.usuarioEntity.getPassword());
		this.mockMvc.perform(RestDocumentationRequestBuilders.post("/login").contentType(APPLICATION_JSON_UTF8)
	            .content(this.ObjectoMapers(map)))
	            .andExpect(status().is(201)).andDo(document("persons/usuario-auth"));
	
	}
	
	public  String  ObjectoMapers( Object  object) throws JsonProcessingException {
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
	    String requestJson=ow.writeValueAsString(object);
		return requestJson;
		
	}
	

}
