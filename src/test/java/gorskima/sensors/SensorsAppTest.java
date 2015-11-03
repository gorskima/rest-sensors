package gorskima.sensors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = SensorsApplication.class)
public class SensorsAppTest {

	@Autowired
	private WebApplicationContext ctx;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = webAppContextSetup(ctx)
				.alwaysExpect(content().contentType(MediaType.APPLICATION_JSON))
				.build();
	}

	@Test
	public void testGettingAllSensors() throws Exception {
		mockMvc.perform(get("/sensors"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(4)));
	}

	@Test
	public void testGettingSingleSensor() throws Exception {
		mockMvc.perform(get("/sensors/bmp085"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value("bmp085"));
	}

}
