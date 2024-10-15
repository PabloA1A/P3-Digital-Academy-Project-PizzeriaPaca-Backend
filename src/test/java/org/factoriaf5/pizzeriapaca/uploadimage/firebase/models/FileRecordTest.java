package org.factoriaf5.pizzeriapaca.uploadimage.firebase.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FileRecordTest {

    private FileRecord fileRecord;

    @BeforeEach
    public void setUp() {
        fileRecord = new FileRecord();
    }

    @Test
    public void testNoArgsConstructor() {
        FileRecord emptyFileRecord = new FileRecord();
        assertNotNull(emptyFileRecord);
    }

    @Test
    public void testAllArgsConstructor() {
        FileRecord fileRecord = new FileRecord(1L, "example.txt", "http://example.com/file", 1024L);
        assertEquals(1L, fileRecord.getId());
        assertEquals("example.txt", fileRecord.getFileName());
        assertEquals("http://example.com/file", fileRecord.getFileUrl());
        assertEquals(1024L, fileRecord.getFileSize());
    }

    @Test
    public void testGetAndSetId() {
        fileRecord.setId(1L);
        assertEquals(1L, fileRecord.getId());
    }

    @Test
    public void testGetAndSetFileName() {
        fileRecord.setFileName("document.pdf");
        assertEquals("document.pdf", fileRecord.getFileName());
    }

    @Test
    public void testGetAndSetFileUrl() {
        fileRecord.setFileUrl("http://example.com/document.pdf");
        assertEquals("http://example.com/document.pdf", fileRecord.getFileUrl());
    }

    @Test
    public void testGetAndSetFileSize() {
        fileRecord.setFileSize(2048L);
        assertEquals(2048L, fileRecord.getFileSize());
    }

    @Test
    public void testToString() {
        fileRecord.setId(1L);
        fileRecord.setFileName("example.txt");
        fileRecord.setFileUrl("http://example.com/file");
        fileRecord.setFileSize(1024L);
    }
}

