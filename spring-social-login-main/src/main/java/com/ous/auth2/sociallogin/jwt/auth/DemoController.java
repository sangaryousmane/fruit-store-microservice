package com.ous.auth2.sociallogin.jwt.auth;

import com.ous.auth2.sociallogin.SocialLoginApplication;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/app")
@Tag(name = "DemoController")
public class DemoController {

    @Operation(description = "Get endpoint for manager", summary = "This is a summary for management get endpoints", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / Invalid token", responseCode = "403")})
    @GetMapping("/demo-controller")
    public String defaultLoginPage() {
        return "<b> Hello, Welcome to you buy</b>";
    }


    @GetMapping("/students")
    public ResponseEntity<SocialLoginApplication.Student> students(){
        SocialLoginApplication.Student student=new
                SocialLoginApplication.Student(
                        "John Paul",
                "john@yahoo.com",
                "Criminal Justice");
        return ResponseEntity.ok(student);
    }
}
