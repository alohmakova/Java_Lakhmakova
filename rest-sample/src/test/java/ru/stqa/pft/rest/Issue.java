package ru.stqa.pft.rest;

public class Issue {

    private int id;
    private String subject;
    private String description;
    private String state_name;
    public Issue withStateName(String state_name) {
        this.state_name = state_name;
        return this;
    }
    public String getStateName() {
        return state_name;
    }
    public int getId() {
        return id;
    }

    public Issue withId(int id) {
        this.id = id;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public Issue withSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Issue withDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", description='" + description + '\'' +
                ", state_name='" + state_name + '\'' +
                '}';
    }
}
