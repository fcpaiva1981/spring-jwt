package br.com.fcpaiva.jwtsprinsecurity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @GetMapping("/lista")
    public ResponseEntity<?> listaProdutos(){
        List<String> produtos = new ArrayList<>();

        produtos.add("Calça");
        produtos.add("Sapato");
        produtos.add("BLusa");
        produtos.add("Meia");
        produtos.add("Tênis");

        return ResponseEntity.ok(produtos);
    }
}
