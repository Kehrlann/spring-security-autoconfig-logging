package wf.garnier.spring.security.noauthprovider;

import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
class GreetingsController {

    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String hello(@AuthenticationPrincipal UserDetails user) {
        return "<h1>Hello [%s]<h1>".formatted(user.getUsername());
    }
}
