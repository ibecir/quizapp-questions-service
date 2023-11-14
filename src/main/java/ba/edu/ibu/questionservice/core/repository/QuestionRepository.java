package ba.edu.ibu.questionservice.core.repository;


import ba.edu.ibu.questionservice.core.model.Question;
import ba.edu.ibu.questionservice.core.model.enums.Category;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends MongoRepository<Question, String> {
    public List<Question> findByCategory(Category category);

    @Aggregation(pipeline = {
            "{ $match: { category: { $eq: '?0' } } }",
            "{ $sample: { size: ?1 } }",
            "{ $project: { _id: 1 } }"
    })
    public List<String> findTopByCategory(Category category, int limit);

    @Aggregation(pipeline = {
            "{ $match: { category: { $eq: '?0' } } }",
            "{ $sample: { size: ?1 } }"
    })
    public List<Question> findTopQuestionsByCategory(Category category, int limit);

    public List<Question> findQuestionByIdIsIn(List<String> questionIds);
}
