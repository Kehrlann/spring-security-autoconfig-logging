package wf.garnier.spring.security.singleauthprovider;

import java.io.IOException;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(OutputCaptureExtension.class)
class SingleAuthProviderApplicationTests {

    @Autowired
    WebClient webClient;

    @Test
    void showsUsername() throws IOException {
        // This test fails, on purpose: the UserDetailsService is ignored
        HtmlPage loginPage = webClient.getPage("/");

        var username = (HtmlInput) loginPage.getElementById("username");
        var password = (HtmlInput) loginPage.getElementById("password");
        var signInButton = (HtmlButton) loginPage.getElementsByTagName("button").getFirst();

        username.type("user");
        password.type("password");

        HtmlPage mainPage = signInButton.click();

        assertThat(mainPage.getBody().getTextContent()).contains("Hello [user]");
    }

    @Test
    void usesCustomAuhtenticationProvider(CapturedOutput output) throws IOException {
        // The custom authentication provider is used, so it logs a line
        HtmlPage loginPage = webClient.getPage("/");

        var username = (HtmlInput) loginPage.getElementById("username");
        var password = (HtmlInput) loginPage.getElementById("password");
        var signInButton = (HtmlButton) loginPage.getElementsByTagName("button").getFirst();

        username.type("user");
        password.type("password");

        signInButton.click();

        assertThat(output.getOut()).contains("custom-authentication-provider used");
    }
}
