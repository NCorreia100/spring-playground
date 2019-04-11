package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(WebController.class)
public class DemoApplicationTests {

	@Autowired
	MockMvc mvc;

	@Test
	public void testHomePage() throws Exception {
		//set up
		RequestBuilder request = MockMvcRequestBuilders.get("/").param("q","test");
		//exercise
		this.mvc.perform(request)
		//assert
				.andExpect(status().isOk())
				.andExpect(content().string("The parameter passed in was test"));
	}
	@Test
	public void testEndpoints() throws Exception {
		//set up
		RequestBuilder request = MockMvcRequestBuilders.get("/app/json").param("q","test");
		//exercise
		this.mvc.perform(request)
				//assert
				.andExpect(status().isOk())
				.andExpect(content().json("{q:test}"));
	}

	@Test
	public void getPi() throws Exception {
		//set up
		RequestBuilder request = MockMvcRequestBuilders.get("/math/pi");
		//exercise
		this.mvc.perform(request)
				//assert
				.andExpect(status().isOk())
				.andExpect(content().string("3.141592653589793"));
	}
}
