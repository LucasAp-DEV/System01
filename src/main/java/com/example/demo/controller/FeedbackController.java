package com.example.demo.controller;

import com.example.demo.domain.feedback.Feedback;
import com.example.demo.repository.FeedbackRepository;
import com.example.demo.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("feedback")
public class FeedbackController {

    @Autowired
    private FeedbackRepository repository;

    @Autowired
    private FeedbackService service;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody Feedback data) {

        return ResponseEntity.status(HttpStatus.OK).body(service.saveFeedback(data));
//        Feedback newFeedback = new Feedback(data.descricao(), data.nota(), data.localId());
//        this.repository.save(newFeedback);
//        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteFeedback(@PathVariable(value = "id")Long id) {
        Optional<Feedback> dell = service.returnId(id);

        if (dell.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        service.dellFeedback(dell);
        return ResponseEntity.status(HttpStatus.OK).build();

    }
}
