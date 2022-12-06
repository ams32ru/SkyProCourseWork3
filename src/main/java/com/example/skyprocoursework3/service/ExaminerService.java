package com.example.skyprocoursework3.service;

import com.example.skyprocoursework3.essense.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}
