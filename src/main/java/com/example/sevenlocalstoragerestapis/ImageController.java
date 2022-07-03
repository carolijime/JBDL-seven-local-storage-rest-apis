package com.example.sevenlocalstoragerestapis;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.awt.*;

@RestController
public class ImageController {

    @GetMapping(value = "/image", produces = {MediaType.IMAGE_PNG_VALUE})
    public byte[] getImage(@RequestParam("id") int id,
                           @RequestParam(value = "length", required = false, defaultValue = "200") int length,
                           @RequestParam(value = "breadth", required = false, defaultValue = "300") int breadth){
        String url="https://picsum.photos/id/" + id + "/" + length + "/" + breadth;

        RestTemplate restTemplate = new RestTemplate();
        byte[] image = restTemplate.getForObject(url, byte[].class);

        return image;
    }

    @GetMapping(value = "/imageSecondMethod")
    public ResponseEntity<byte[]> getImageSecondMethod(@RequestParam("id") int id,
                                                       @RequestParam(value = "length", required = false, defaultValue = "200") int length,
                                                       @RequestParam(value = "breadth", required = false, defaultValue = "300") int breadth){
        String url="https://picsum.photos/id/" + id + "/" + length + "/" + breadth;

        RestTemplate restTemplate = new RestTemplate();
        byte[] image = restTemplate.getForObject(url, byte[].class);

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        return new ResponseEntity<byte[]>(image, headers, HttpStatus.OK);
    }




}
