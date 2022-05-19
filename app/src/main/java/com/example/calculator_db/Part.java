package com.example.calculator_db;

import java.util.UUID;

public class Part {
    private String request;
    private String answer;

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Part(String request, String answer) {
        this.request = request;
        this.answer = answer;
    }
}
