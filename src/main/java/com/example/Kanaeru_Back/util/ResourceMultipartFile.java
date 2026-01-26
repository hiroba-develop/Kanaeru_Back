package com.example.Kanaeru_Back.util;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.lang.NonNull;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ResourceMultipartFile implements MultipartFile {

    private final Resource resource;
    @NonNull
    private final String name;

    public ResourceMultipartFile(Resource resource, @NonNull String name) {
        this.resource = resource;
        this.name = name;
    }

    @Override
    @NonNull
    public String getName() {
        return name;
    }

    @Override
    public String getOriginalFilename() {
        return resource.getFilename();
    }

    @Override
    public String getContentType() {
        try {
            String filename = resource.getFilename();
            if (filename == null) {
                return "application/octet-stream";
            }
            if (filename.endsWith(".png")) {
                return "image/png";
            } else if (filename.endsWith(".gif")) {
                return "image/gif";
            } else if (filename.endsWith(".jpg") || filename.endsWith(".jpeg")) {
                return "image/jpeg";
            }
            return "application/octet-stream";
        } catch (Exception e) {
            return "application/octet-stream";
        }
    }

    @Override
    public boolean isEmpty() {
        try {
            return !resource.exists() || resource.contentLength() == 0;
        } catch (IOException e) {
            return true;
        }
    }

    @Override
    public long getSize() {
        try {
            return resource.contentLength();
        } catch (IOException e) {
            return 0;
        }
    }

    @Override
    @NonNull
    public byte[] getBytes() throws IOException {
        return resource.getInputStream().readAllBytes();
    }

    @Override
    @NonNull
    public InputStream getInputStream() throws IOException {
        return resource.getInputStream();
    }

    @Override
    public void transferTo(@NonNull File dest) throws IOException, IllegalStateException {
        throw new UnsupportedOperationException("transferTo is not supported");
    }
}
