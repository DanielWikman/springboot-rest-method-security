package net.wikmans.unit;

import net.wikmans.srmsexample.Application;
import net.wikmans.srmsexample.domain.Item;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by Daniel on 2015-03-13.
 * Tests Method security on ItemRepository.
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)   // 1
@SpringApplicationConfiguration(classes = Application.class)   // 2
@WebAppConfiguration   // 3
@IntegrationTest("server.port:0")   // 4
public class TestSecurity {

    private static final String ADMIN_USER = "danne";
    private static final String ADMIN_PWD = "test";
    private static final String USER = "dan";
    private static final String PWD = "test";
    private static final String HOST = "localhost";

    @Value("${local.server.port}")
    private int port;

    private String getUrl(String path) {
        return String.format("http://%s:%d/%s", HOST, port, path);
    }

    @Test
    public void testGetItemsNoCredentials() {
        TestRestTemplate template = new TestRestTemplate();
        ResponseEntity<String> entity = template.exchange(getUrl("items"), HttpMethod.GET, null, String.class);
        Assert.assertEquals("Unauthorized access allowed", HttpStatus.UNAUTHORIZED, entity.getStatusCode());
    }

    @Test
    public void testGetItemsUserCredentials() {
        TestRestTemplate template = new TestRestTemplate(USER, PWD);
        ResponseEntity<String> entity = template.exchange(getUrl("items"), HttpMethod.GET, null, String.class);
        Assert.assertEquals("Authorized access denied", HttpStatus.OK, entity.getStatusCode());
    }

    @Test
    public void testGetItemsAdminCredentials() {
        TestRestTemplate template = new TestRestTemplate(ADMIN_USER, ADMIN_PWD);
        ResponseEntity<String> entity = template.exchange(getUrl("items"), HttpMethod.GET, null, String.class);
        Assert.assertEquals("Authorized access denied", HttpStatus.OK, entity.getStatusCode());
    }

    @Test
    public void testPostItemsNoCredentials() {
        Item i = new Item();
        i.setDescription("Test Item");
        TestRestTemplate template = new TestRestTemplate();
        HttpHeaders h = new HttpHeaders();
        h.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Item> e = new HttpEntity<>(i, h);
        try {
            ResponseEntity<String> entity = template.exchange(getUrl("items"), HttpMethod.POST, e, String.class);
            Assert.assertEquals("Unauthorized access allowed", HttpStatus.UNAUTHORIZED, entity.getStatusCode());
        } catch (Exception ex) {
            // Currently RestTemplate follows the re-direct to /login which throws an example.
        }
    }

    @Test
    public void testPostItemsUserCredentials() {
        Item i = new Item();
        i.setDescription("Test Item");
        TestRestTemplate template = new TestRestTemplate(USER, PWD);
        HttpHeaders h = new HttpHeaders();
        h.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Item> e = new HttpEntity<>(i, h);
        ResponseEntity<String> entity = template.exchange(getUrl("items"), HttpMethod.POST, e, String.class);
        Assert.assertEquals("Unauthorized access allowed", HttpStatus.FORBIDDEN, entity.getStatusCode());
    }

    @Test
    public void testPostItemsAdminCredentials() {
        Item i = new Item();
        i.setDescription("Test Item");
        TestRestTemplate template = new TestRestTemplate(ADMIN_USER, ADMIN_PWD);
        HttpHeaders h = new HttpHeaders();
        h.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Item> e = new HttpEntity<>(i, h);
        ResponseEntity<String> entity = template.exchange(getUrl("items"), HttpMethod.POST, e, String.class);
        Assert.assertEquals("Authorized access not allowed", HttpStatus.CREATED, entity.getStatusCode());
    }

}
