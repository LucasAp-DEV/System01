package com.example.demo.controller;

import com.example.demo.domain.feedback.Feedback;
import com.example.demo.domain.feedback.FeedbackDTO;
import com.example.demo.repository.FeedbackRepository;
import jakarta.validation.Valid;
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

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid FeedbackDTO data) {
        Feedback newFeedback = new Feedback(data.descricao(), data.nota(), data.localId());
        this.repository.save(newFeedback);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteFeedbac(@RequestBody @Valid FeedbackDTO data) {

        Optional<Feedback> deleteFeedback = repository.findById((data.id()));
        if(deleteFeedback.isEmpty()) {
            this.repository.deleteById(data.id());
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }
}
