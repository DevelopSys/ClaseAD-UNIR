package io.bootify.my_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * Serve Reacts index.html for all requests that are not relevant for the backend.
 */
@Controller
public class ReactForwardController {

    @GetMapping("{path:^(?!api|public|css|js|images|swagger)[^\\.]*}/**")
    public String handleForward() {
        return "forward:/";
    }

}
