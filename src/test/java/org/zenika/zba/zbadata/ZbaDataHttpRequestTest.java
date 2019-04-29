package org.zenika.zba.zbadata;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ZbaDataHttpRequestTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getToRecipeShouldReturnOk() {
        assertThat(this.restTemplate.getForObject("http://localhost:8080/Recipe",String.class)).contains("Ok");
    }

    @Test
    public void getToStepsShouldReturnOk() {
        assertThat(this.restTemplate.getForObject("http://localhost:8080/Steps",String.class)).contains("Ok");
    }

}
