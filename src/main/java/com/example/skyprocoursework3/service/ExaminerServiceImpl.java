package com.example.skyprocoursework3.service;

import com.example.skyprocoursework3.essense.Question;
import com.example.skyprocoursework3.exception.IncorrectAmountOfQuestionsException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ExaminerServiceImpl implements ExaminerService {
        private final QuestionService questionService;

        public ExaminerServiceImpl(QuestionService questionService) {
            this.questionService = questionService;
        }
        @Override
        public Collection<Question> getQuestions(int amount) {
            if (amount > questionService.getAll().size() || amount <= 0) {
                throw new IncorrectAmountOfQuestionsException();
            }
            Set<Question> questions = new HashSet<>();
            while (questions.size() < amount) {
                questions.add(questionService.getRandomQuestion());
            }
            return questions;
        }

}
