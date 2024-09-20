package org.factoriaf5.pizzeriapaca.uploadimage.local.controllers;

import org.factoriaf5.pizzeriapaca.uploadimage.local.services.implementations.IStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("${api-endpoint}/upload-image")
public class FileUploaderController {

    private IStorageService storageService;

    @Autowired
    public FileUploaderController(IStorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping()
    public ResponseEntity<String> handleFileUpload(@RequestParam("files") MultipartFile[] files,
            RedirectAttributes redirectAttributes) {

        if (files == null || files.length == 0) {
            return ResponseEntity.badRequest().body("No files selected for upload.");
        }

        try {
            for (MultipartFile file : files) {
                storageService.store(file);
                System.out.println("--------------------------------------------------" + file.getName());
            }
            return ResponseEntity.ok("Successfully uploaded " + files.length + " file(s)!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload files. Error: " + e.getMessage());
        }
    }
}
