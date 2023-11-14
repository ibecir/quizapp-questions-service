package ba.edu.ibu.questionservice.rest.dto;

public class QuestionResponse {
    private String questionId;
    private String response;

    public QuestionResponse(String questionId, String response) {
        this.questionId = questionId;
        this.response = response;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
