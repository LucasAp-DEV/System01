package demo.TCC.controller;

import demo.TCC.domain.feedback.Feedback;
import demo.TCC.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService service;

    @GetMapping("/list")
    public ResponseEntity<List<Feedback>> returnall() {
        return ResponseEntity.status(HttpStatus.OK).body(service.returnAll());
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Feedback data) {
        service.saveFeedback(data);
        return ResponseEntity.status(HttpStatus.OK).body("Feedback Cadastrado");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFeedback(@PathVariable(value = "id")Long id) {
        service.deleteById(id);
        return ResponseEntity.ok().body("FeedBack Excluido");

    }
}
