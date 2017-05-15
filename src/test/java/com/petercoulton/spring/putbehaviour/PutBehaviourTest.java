package com.petercoulton.spring.putbehaviour;

import org.hamcrest.core.*;
import org.junit.*;
import org.junit.runner.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.http.*;
import org.springframework.test.context.junit4.*;
import org.springframework.test.context.web.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.setup.*;
import org.springframework.web.context.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@WebAppConfiguration
@RunWith(SpringRunner.class)
public class PutBehaviourTest {
	private static final Logger log = LoggerFactory.getLogger(PutBehaviourTest.class);

	@Autowired
	WebApplicationContext context;

	MockMvc mvc;

	@Before
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void createBlob() throws Exception {
		final MvcResult createResult =
				mvc.perform(post("/blobs")
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.content("{\"name\": \"bob\"}"))
						.andExpect(status().isCreated())
						.andDo(print())
						.andReturn();

		final String newBlobLocation = createResult.getResponse().getHeader("Location");

		mvc.perform(get(newBlobLocation))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", Is.is("bob")))
				.andReturn();
	}

	@Test
	public void updateBlobWithPut() throws Exception {
		final MvcResult createResult =
				mvc.perform(post("/blobs")
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.content("{\"name\": \"bob\"}"))
						.andExpect(status().isCreated())
						.andDo(print())
						.andReturn();

		final String newBlobLocation = createResult.getResponse().getHeader("Location");

		mvc.perform(put(newBlobLocation)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content("{\"name\": \"bobby\"}"))
				.andDo(print())
				.andExpect(status().isNoContent())
				.andReturn();

		mvc.perform(get(newBlobLocation))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", Is.is("bobby")))
				.andReturn();
	}

	@Test
	public void updateBlobWithPatch() throws Exception {
		final MvcResult createResult =
				mvc.perform(post("/blobs")
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.content("{\"name\": \"bob\"}"))
						.andExpect(status().isCreated())
						.andDo(print())
						.andReturn();

		final String newBlobLocation = createResult.getResponse().getHeader("Location");

		mvc.perform(patch(newBlobLocation)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content("{\"name\": \"bobby\"}"))
				.andDo(print())
				.andExpect(status().isNoContent())
				.andReturn();

		mvc.perform(get(newBlobLocation))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", Is.is("bobby")))
				.andReturn();
	}
}
