package com.barrett.authserver.controller;

import com.barrett.authserver.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @GetMapping("/api/protected")
    public @ResponseBody Object hellWorld() {
        return "Hello World! This is a protected api";
    }

    @PostMapping("/login")
    public Object login(HttpServletResponse response, @RequestBody final Account account) throws IOException {
        if(isValidPassword(account)) {
            String jwt = JwtUtil.generateToken(account.username);
            return new HashMap<String,String>(){{
                put("token", jwt);
            }};
        }else {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }

    /*
    Check if the username and password is valid.
    This is just a simple example, you can build up your own logic here.
     */
    private boolean isValidPassword(Account ac) {
        return "admin".equals(ac.username)
                && "admin".equals(ac.password);
    }


    // Java bean Account class
    public static class Account {
        public String username;
        public String password;
    }

}
