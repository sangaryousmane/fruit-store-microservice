package com.ous.auth2.sociallogin.access;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@Tag(name= "ResourceEndpoint")
@Hidden
public class ResourceEndpoint {

    @GetMapping
    public String hiThere(){
        return "<br>Hi there</b>";
    }
}
