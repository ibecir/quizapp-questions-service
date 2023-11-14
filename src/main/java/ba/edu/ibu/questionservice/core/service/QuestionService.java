package ba.edu.ibu.questionservice.core.service;

import ba.edu.ibu.questionservice.core.model.Question;
import ba.edu.ibu.questionservice.core.model.enums.Category;
import ba.edu.ibu.questionservice.core.repository.QuestionRepository;
import ba.edu.ibu.questionservice.rest.dto.QuestionResponse;
import ba.edu.ibu.questionservice.rest.dto.QuestionsScore;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public List<Question> getQuestionsByCategory(Category category) {
        return questionRepository.findByCategory(category);
    }

    public Question addQuestion(Question question) {
        return questionRepository.save(question);
    }

    public List<String> generateQuestionsIds(Category category, int numOfQuestions){
        List<String> questionList = questionRepository.findTopByCategory(category, numOfQuestions);
        return questionList;
    }

    public List<Question> generateQuestions(Category category, int numOfQuestions){
        return questionRepository.findTopQuestionsByCategory(category, numOfQuestions);
    }

    public List<Question> getQuestionsByIds(String[] ids) {
        return questionRepository.findQuestionByIdIsIn(Arrays.asList(ids));
    }

    public QuestionsScore submitQuestionAnswers(List<QuestionResponse> responses) {
        List<String> questionIds = responses
                .stream()
                .map(r -> r.getQuestionId())
                .collect(Collectors.toList());

        List<Question> questions = questionRepository.findQuestionByIdIsIn(questionIds);
        String message = "Some answers sent.";
        int score = 0;
        for (Question question : questions) {
            for (QuestionResponse response : responses) {
                if (question.getId().equals(response.getQuestionId())) {
                    if (question.getRightAnswer().equals(response.getResponse()))
                        score += 10;
                }
            }
        }
        return new QuestionsScore(message, score);
    }
}
