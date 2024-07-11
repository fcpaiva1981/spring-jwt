package br.com.fcpaiva.jwtsprinsecurity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/check-api-online")
public class ApiCallController {

    @GetMapping("/verificar-api")
    public ResponseEntity<?> vericarApi(){
        return ResponseEntity.ok("OK!");
    }
}
