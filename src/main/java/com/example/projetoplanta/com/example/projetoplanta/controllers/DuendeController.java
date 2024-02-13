package com.example.projetoplanta.com.example.projetoplanta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projetoplanta.com.example.projetoplanta.DTO.DuendeRecordDTO;
import com.example.projetoplanta.com.example.projetoplanta.repositories.DuendeRepository;

@RestController
@RequestMapping("/duende")
public class DuendeController {
    
    @Autowired
    DuendeRepository duendeRepository;

    @PostMapping("/participar")
    public ResponseEntity<DuendeController>
}
