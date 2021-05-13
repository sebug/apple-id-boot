package ch.sebug.appleid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationInformationController {
    @GetMapping("/authInfo")
    public AuthenticationInformation authInfo(@AuthenticationPrincipal OAuth2User principal) {
        System.out.println("Controller call reached for principal " + principal.getName());
        System.out.println(principal);
        return new AuthenticationInformation("Not","yet");
    }
}
