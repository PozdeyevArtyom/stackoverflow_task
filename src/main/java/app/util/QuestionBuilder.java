package app.util;

import app.entities.Owner;
import app.entities.Question;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.Date;

@JsonPOJOBuilder()
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuestionBuilder {
    private String[] tagsValue;
    private Owner ownerValue;
    private boolean isAnsweredValue;
    private int viewCountValue;
    private int answerCountValue;
    private int scoreValue;
    private long lastActivityDateValue;
    private long creationDateValue;
    private long lastEditDateValue;
    private int questionIdValue;
    private String linkValue;
    private String titleValue;

    public QuestionBuilder withTags(String[] tags) {
        tagsValue = tags;
        return this;
    }

    public QuestionBuilder withOwner(Owner owner) {
        ownerValue = owner;
        return this;
    }

    public QuestionBuilder withIs_answered(boolean is_answered) {
        isAnsweredValue = is_answered;
        return this;
    }

    public QuestionBuilder withView_count(int view_count) {
        viewCountValue = view_count;
        return this;
    }

    public QuestionBuilder withAnswer_count(int answer_count) {
        answerCountValue = answer_count;
        return this;
    }

    public QuestionBuilder withScore(int score) {
        scoreValue = score;
        return this;
    }

    public QuestionBuilder withLast_activity_date(long last_activity_date) {
        lastActivityDateValue = last_activity_date;
        return this;
    }

    public QuestionBuilder withCreation_date(long creation_date) {
        creationDateValue = creation_date;
        return this;
    }

    public QuestionBuilder withLast_edit_date(long last_edit_date) {
        lastEditDateValue = last_edit_date;
        return this;
    }

    public QuestionBuilder withQuestion_id(int question_id) {
        questionIdValue = question_id;
        return this;
    }

    public QuestionBuilder withLink(String link) {
        linkValue = link;
        return this;
    }

    public QuestionBuilder withTitle(String title) {
        titleValue = title;
        return this;
    }

    public Question build() {
        Question q = new Question();
        q.setAnswerCount(answerCountValue);
        q.setAnswered(isAnsweredValue);
        q.setId(questionIdValue);
        q.setCreationDate(new Date(creationDateValue * 1000));
        q.setLastActivityDate(new Date(lastActivityDateValue * 1000));
        q.setLastEditDate(new Date(lastEditDateValue * 1000));
        q.setLink(linkValue);
        q.setOwner(ownerValue);
        q.setScore(scoreValue);
        q.setTags(tagsValue);
        q.setTitle(titleValue);
        q.setViewCount(viewCountValue);

        return q;
    }
}
