package catmotion.controllers;

import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin
@RestController
public class AuthenticationController {

    @GetMapping("/authenticate")
    public Principal user(Principal user) {
        return user;
    }

}