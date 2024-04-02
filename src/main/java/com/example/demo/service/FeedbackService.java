package com.example.demo.service;

import com.example.demo.domain.feedback.Feedback;
import com.example.demo.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository repository;

    public Feedback saveFeedback (Feedback feedback) {
        return repository.save(feedback);
    }

    public Optional<Feedback> returnId(Long id) {
        return repository.findById(id);
    }

    public void dellFeedback(Optional<Feedback> feedback) {
        repository.delete(feedback.get());
    }

}
