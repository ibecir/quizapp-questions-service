package ba.edu.ibu.questionservice.rest.controllers;

import ba.edu.ibu.questionservice.core.model.Question;
import ba.edu.ibu.questionservice.core.model.enums.Category;
import ba.edu.ibu.questionservice.core.service.QuestionService;
import ba.edu.ibu.questionservice.rest.dto.QuestionResponse;
import ba.edu.ibu.questionservice.rest.dto.QuestionsScore;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("questions")
public class QuestionController {

    private final QuestionService questionService;
    private final Environment environment;

    public QuestionController(QuestionService questionService, Environment environment) {
        this.questionService = questionService;
        this.environment = environment;
    }

    @RequestMapping(method = RequestMethod.GET, path = "")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return ResponseEntity.ok(questionService.getAllQuestions());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/filter/{categoryFilter}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable Category categoryFilter) {
        return ResponseEntity.ok(questionService.getQuestionsByCategory(categoryFilter));
    }

    @RequestMapping(method = RequestMethod.POST, path = "")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        return new ResponseEntity(questionService.addQuestion(question), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/generate/ids")
    public ResponseEntity<List<String>> generateQuestionIds(@RequestParam Category category, @RequestParam int numOfQuestions){
        return ResponseEntity.ok(questionService.generateQuestionsIds(category, numOfQuestions));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/generate")
    public ResponseEntity<List<Question>> generateQuestions(@RequestParam Category category, @RequestParam int numOfQuestions){
        return ResponseEntity.ok(questionService.generateQuestions(category, numOfQuestions));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{questionIds}")
    public ResponseEntity<List<Question>> getQuestionsByIds(@PathVariable String[] questionIds){
        return ResponseEntity.ok(questionService.getQuestionsByIds(questionIds));
    }

    @RequestMapping(method = RequestMethod.POST, path = "/submit")
    public ResponseEntity<QuestionsScore> submitAnswers(@RequestBody List<QuestionResponse> responses){
        System.out.println("Server triggered " + environment.getProperty("local.server.port"));
        return ResponseEntity.ok(questionService.submitQuestionAnswers(responses));
    }
}
