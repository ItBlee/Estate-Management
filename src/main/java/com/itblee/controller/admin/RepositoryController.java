package com.itblee.controller.admin;

import com.itblee.utils.FileUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RepositoryController {

    @GetMapping(value = "/repository/**", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImages(HttpServletRequest request) {
        String url = request.getRequestURI();
        String relativePath = url.replace("/repository/", "");
        return ResponseEntity.ok().body(FileUtils.load(relativePath));
    }

}
