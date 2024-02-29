package wf.garnier.spring.security.twouserdetailsservice;

import java.io.IOException;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class TwoUserDetailsServiceApplicationTests {

	@Autowired
	WebClient webClient;

	@Test
	void showsUsername() throws IOException {
		// This test fails, on purpose
		HtmlPage loginPage = webClient.getPage("/");

		var username = (HtmlInput) loginPage.getElementById("username");
		var password = (HtmlInput) loginPage.getElementById("password");
		var signInButton = (HtmlButton) loginPage.getElementsByTagName("button").getFirst();

		username.type("user");
		password.type("password");

		HtmlPage mainPage = signInButton.click();

		assertThat(mainPage.getBody().getTextContent()).contains("Hello [user]");
	}

}
