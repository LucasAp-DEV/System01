package com.example.demo.controller;

import com.example.demo.domain.feedback.Feedback;
import com.example.demo.domain.feedback.FeedbackDTO;
import com.example.demo.repository.FeedbackRepository;
import com.example.demo.service.FeedbackServide;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("feedback")
public class FeedbackController {

    @Autowired
    private FeedbackRepository repository;

    @Autowired
    private FeedbackServide service;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody Feedback data) {

        return ResponseEntity.status(HttpStatus.OK).body(service.saveFeedback(data));
//        Feedback newFeedback = new Feedback(data.descricao(), data.nota(), data.localId());
//        this.repository.save(newFeedback);
//        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteFeedbac(@PathVariable(value = "id")Long id, @RequestBody @Valid Feedback feedback) {
        Optional<Feedback> dell = service.returnId(id);

//        Optional<Feedback> deleteFeedback = repository.findById((data.id()));

        if (dell.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        service.dellFeedback(dell);
        return ResponseEntity.status(HttpStatus.OK).body("Feedback Deletado");

    }
}
