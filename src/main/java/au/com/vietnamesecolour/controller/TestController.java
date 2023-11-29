package au.com.vietnamesecolour.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/staff")
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "test from /api/v1/staff/**";
    }
}
