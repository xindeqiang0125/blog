package com.xdq.blog.manager.controller;

import com.xdq.blog.manager.properties.BlogProperties;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/upload")
public class FileController {

    @Autowired
    BlogProperties blogProperties;

    @RequestMapping("")
    public Response upload(MultipartFile[] files) {
        List<String> urls = new ArrayList<>(files.length);
        for (MultipartFile file : files) {
            String upload = upload(file);
            if (upload == null) {
                return Response.error(1, new String[0]);
            } else {
                urls.add(upload);
            }
        }
        return Response.success(urls.toArray(new String[0]));
    }

    private String upload(MultipartFile file) {
        try {
            String root = blogProperties.getRootPath();
            InputStream in = file.getInputStream();
            String imagePath = getImagePath(file);
            File dest = new File(root + imagePath);
            FileUtils.copyInputStreamToFile(in, dest);
            return imagePath;
        } catch (IOException e) {
            return null;
        }
    }

    private String getImagePath(MultipartFile file) {
        LocalDate now = LocalDate.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        String filename = file.getOriginalFilename();
        String suffix = filename.substring(filename.lastIndexOf("."));
        StringBuilder sb = new StringBuilder();
        sb.append("/image/")
                .append(year).append("/")
                .append(month).append("/")
                .append(UUID.randomUUID())
                .append(suffix);
        return sb.toString();
    }

    static class Response {
        private int errno;
        private String[] data;

        public static Response success(String[] data) {
            return new Response(0, data);
        }

        public static Response error(int errno, String[] data) {
            return new Response(errno, data);
        }

        private Response(int errno, String[] data) {
            this.errno = errno;
            this.data = data;
        }

        public int getErrno() {
            return errno;
        }

        public void setErrno(int errno) {
            this.errno = errno;
        }

        public String[] getData() {
            return data;
        }

        public void setData(String[] data) {
            this.data = data;
        }
    }
}
