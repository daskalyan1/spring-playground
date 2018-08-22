package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockMvcClientHttpRequestFactory;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PagesController.class)

public class PagesControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void testPI() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/math/pi");
		this.mvc.perform(request)
			.andExpect(status().isOk())
			.andExpect(content().string("3.141592653589793"));
	}

	@Test
	public void testAdd() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/math/calculate?operation=add&x=4&y=6");
		this.mvc.perform(request)
			.andExpect((status().isOk()))
			.andExpect(content().string("10"));
	}

	@Test
	public void testAddAsDefault() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/math/calculate?x=4&y=6");
		this.mvc.perform(request)
			.andExpect((status().isOk()))
			.andExpect(content().string("10"));
	}

	@Test
	public void testSubtract() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/math/calculate/?operation=subtract&x=4&y=6");
		this.mvc.perform(request)
			.andExpect((status().isOk()))
			.andExpect(content().string("-2"));
	}

	@Test
	public void testMultiply() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/math/calculate?operation=multiply&x=4&y=6");
		this.mvc.perform(request)
			.andExpect((status().isOk()))
			.andExpect(content().string("24"));
	}

	@Test
	public void testDivide() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/math/calculate?operation=divide&x=9&y=3");
		this.mvc.perform(request)
			.andExpect((status().isOk()))
			.andExpect(content().string("3"));
	}

	/*@Test
	public void testAddPost() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.post("/math/sum/?x=9&x=3&x=5");
		this.mvc.perform(request)
			.andExpect((status().isOk()))
			.andExpect(content().string("17"));
	}*/

	@Test
	public void testVolume() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.post("/math/volume/3/4/5");
		this.mvc.perform(request)
			.andExpect((status().isOk()))
			.andExpect(content().string("The volume of a 3X4X5 rectangle is 60"));
	}

	@Test
	public void testAreaOfCircle() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/math/area");
		request.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			.param("type", "circle")
			.param("radius", "4");
		this.mvc.perform(request)
			.andExpect((status().isOk()))
			.andExpect(content().string("Area of a circle with a radius of 4 is 50.26548245743669"));
	}

	@Test
	public void testAreaOfRectangle() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/math/area");
		request.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			.param("type", "rectangle")
			.param("width", "4")
			.param("height", "8");
		this.mvc.perform(request)
			.andExpect((status().isOk()))
			.andExpect(content().string("Area of a 4X8 rectangle is 32"));
	}

	@Test
	public void testInvalidArea() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/math/area");
		request.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			.param("type", "rectangle")
			.param("radius", "4");
		this.mvc.perform(request)
			.andExpect((status().isOk()))
			.andExpect(content().string("Invalid"));
	}
}
