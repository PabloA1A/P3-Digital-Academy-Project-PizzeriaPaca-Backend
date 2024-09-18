package org.factoriaf5.pizzeriapaca.uploadimage.firebase.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.cloud.StorageClient;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.io.IOException;
import java.net.URL;

@RestController
@RequestMapping(path = "${api-endpoint}/images")
public class UploadController {
    
    @PostMapping()
    public String upload(@RequestParam("file")MultipartFile file) throws IOException{

        Bucket bucket = StorageClient.getInstance().bucket();
        String blobName = "public/" + UUID.randomUUID() + " " + file.getOriginalFilename();
        String contentType = file.getContentType();

        Blob blob = bucket.create(blobName, file.getInputStream(), contentType);

        URL signUrl = blob.signUrl(1, TimeUnit.HOURS);

        return signUrl.toString();
    }
}
