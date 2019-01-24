package app.models;


import app.entities.Question;

import java.text.SimpleDateFormat;

public class QuestionModel {
    private String picture;
    private String ownerName;
    private String ownerLink;
    private int rep;
    private String created;
    private String title;
    private String link;
    private String[] tags;
    private boolean answered;
    private int viewCount;
    private int answerCount;
    private int score;
    private String lastAct;
    private String lastEdited;

    public QuestionModel() {

    }

    public QuestionModel(Question question) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        picture = question.getOwner().getProfilePicture();
        ownerName = question.getOwner().getName();
        ownerLink = question.getOwner().getLink();
        rep = question.getOwner().getReputation();
        created = format.format(question.getCreationDate());
        title = question.getTitle();
        link = question.getLink();
        tags = question.getTags();
        answered = question.isAnswered();
        viewCount = question.getViewCount();
        answerCount = question.getAnswerCount();
        score = question.getScore();
        lastAct = format.format(question.getLastActivityDate());
        lastEdited = format.format(question.getLastEditDate());
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerLink() {
        return ownerLink;
    }

    public void setOwnerLink(String ownerLink) {
        this.ownerLink = ownerLink;
    }

    public int getRep() {
        return rep;
    }

    public void setRep(int rep) {
        this.rep = rep;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(int answerCount) {
        this.answerCount = answerCount;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getLastAct() {
        return lastAct;
    }

    public void setLastAct(String lastAct) {
        this.lastAct = lastAct;
    }

    public String getLastEdited() {
        return lastEdited;
    }

    public void setLastEdited(String lastEdited) {
        this.lastEdited = lastEdited;
    }
}
