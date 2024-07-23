//package com.example.mysite.controller;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class MyController {
//
//    private boolean flag = false;
//
//    @PostMapping("/your-endpoint")
//    public ResponseEntity<String> handlePostRequest(@RequestParam String phone) {
//        if ("111".equals(phone)) {
//            flag = true;
//            return ResponseEntity.ok("Flag set to true");
//        } else {
//            return ResponseEntity.ok("Flag remains false");
//        }
//    }
//
//    @GetMapping("/check-flag")
//    public ResponseEntity<String> checkFlag() {
//        return ResponseEntity.ok("Flag is set to: " + flag);
//    }
//}
