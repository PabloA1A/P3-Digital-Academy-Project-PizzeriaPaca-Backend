package org.factoriaf5.pizzeriapaca.uploadimage.firebase.services;

import java.io.IOException;
import java.util.UUID;

import org.factoriaf5.pizzeriapaca.uploadimage.firebase.models.FileRecord;
import org.factoriaf5.pizzeriapaca.uploadimage.firebase.repository.FirebaseRepository;
import org.springframework.web.multipart.MultipartFile;

import com.google.cloud.storage.Acl;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.cloud.StorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service

public class UploadService {

    @Autowired
    FirebaseRepository repository;

    public String uploadFileToFirebaseAndSaveRecord(MultipartFile file) throws IOException {
        Bucket bucket = StorageClient.getInstance().bucket();
        String blobName = "uploads/" + UUID.randomUUID() + "_" + file.getOriginalFilename();
        String contentType = file.getContentType();

        Blob blob = bucket.create(blobName, file.getInputStream(), contentType);
        blob.createAcl(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));

        String publicUrl = "https://storage.googleapis.com/" + bucket.getName() + "/" + blob.getName();

        FileRecord fileRecord = new FileRecord();
        fileRecord.setFileName(file.getOriginalFilename());
        fileRecord.setFileUrl(publicUrl);
        fileRecord.setFileSize(file.getSize());
        repository.save(fileRecord);

        return publicUrl;
    }
}
