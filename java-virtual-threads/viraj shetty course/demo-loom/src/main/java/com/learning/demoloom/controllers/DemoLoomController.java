package com.learning.demoloom.controllers;

import com.learning.demoloom.controllers.services.DemoApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Random;

@RestController
@RequestMapping("/demo-loom")
public class DemoLoomController {

    private static final Random random = new Random();

    @Autowired
    private DemoApiService demoApiService;
    @GetMapping("/thread-info")
    public String threadInfo(){
        return Thread.currentThread().toString();
    }


    @GetMapping("/single-call")
    public ResponseEntity<String> singleCall(){
        try {
            return ResponseEntity.ok(demoApiService.callAPI());
        } catch (IOException e) {

        } catch (InterruptedException e) {

        }
        return ResponseEntity.internalServerError().body(null);
    }

    @GetMapping("/parallel-call")
    public ResponseEntity<String> parallelCall(){
        try {
            String response1 = demoApiService.callAPI();
            String response2 = demoApiService.callAPI();

            return random.nextBoolean() ? ResponseEntity.ok(response1) :  ResponseEntity.ok(response2);
        } catch (IOException e) {

        } catch (InterruptedException e) {

        }
        return ResponseEntity.internalServerError().body(null);
    }
}
