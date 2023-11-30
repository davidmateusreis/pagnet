package com.david.backend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.david.backend.service.CNABService;

@RestController
@RequestMapping("cnab")
public class CNABController {

    private final CNABService cnabService;

    public CNABController(CNABService cnabService) {
        this.cnabService = cnabService;
    }

    @PostMapping("upload")
    public String upload(@RequestParam("file") MultipartFile file) throws Exception {
        cnabService.uploadCnabFile(file);
        return "Processamento iniciado!";
    }
}
