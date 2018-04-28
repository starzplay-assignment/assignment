package com.starzplay.media;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.starzplay.media.MediaApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={MediaApplication.class})

public class MediaApplicationTests {

	/*@Autowired
    private MockMvc mvc;
	
	@Test
	public void test() throws Exception{
		mvc.perform(get("/media?filter=censoring&level=Censored ")
			      .contentType(MediaType.APPLICATION_FORM_URLENCODED))
			      .andExpect(status().isOk());
			 }
	*/
	
	@Test
	public void contextLoads() {
		
	}

}
