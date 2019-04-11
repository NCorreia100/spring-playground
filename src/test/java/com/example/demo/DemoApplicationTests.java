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
	@Test
	public void testVehiclesEndpoint() throws Exception {
		//set up
		RequestBuilder request = MockMvcRequestBuilders.get("/app/vehicles?year=1987&doors=2");
		//exercise
		this.mvc.perform(request)
				//assert
				.andExpect(status().isOk())
				.andExpect(content().json("{vehicles:{year:1987,doors:2}}"));
	}
	@Test
	public void testMathEndpoint() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders.get("/app/math/calculate?x=5&y=2");
		RequestBuilder addRequest = MockMvcRequestBuilders.get("/app/math/calculate?operation=add&x=5&y=2");
		RequestBuilder subtractRequest = MockMvcRequestBuilders.get("/app/math/calculate?operation=subtract&x=5&y=2");
		RequestBuilder multiplyRequest = MockMvcRequestBuilders.get("/app/math/calculate?operation=multiply&x=5&y=2");
		RequestBuilder divideRequest = MockMvcRequestBuilders.get("/app/math/calculate?operation=divide&x=5&y=2");
		//exercise
		this.mvc.perform(request)
				//assert
				.andExpect(status().isOk())
				.andExpect(content().string("5 + 2 = 7"));
		this.mvc.perform(addRequest).andExpect(content().string("5 + 2 = 7"));
		this.mvc.perform(subtractRequest).andExpect(content().string("5 - 2 = 3"));
		this.mvc.perform(multiplyRequest).andExpect(content().string("5 * 2 = 10"));
		this.mvc.perform(divideRequest).andExpect(content().string("5 / 2 = 2.5"));
	}
}
