package com.example.skyprocoursework3.service;

import com.example.skyprocoursework3.essense.Question;
import com.example.skyprocoursework3.exception.IncorrectAmountOfQuestionsException;
import com.example.skyprocoursework3.exception.QuestionAlreadyExistException;
import com.example.skyprocoursework3.exception.QuestionNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {
    private final Set<Question> questions;
    private final Random random;

    public JavaQuestionService() {
        this.questions = new HashSet<>();
        this.random = new Random();
    }

    @Override
    public Question add(String question, String answer) {
        return add(new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
        if (!questions.add(question)) {
            throw new QuestionAlreadyExistException();
        }
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (!questions.remove(question)) {
            throw new QuestionNotFoundException();
        }
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return new HashSet<>(questions);
    }

    @Override
    public Question getRandomQuestion() {
        if (questions.isEmpty()) {
            throw new IncorrectAmountOfQuestionsException();
        }
        return questions.stream().skip(random
                        .nextInt(0, questions.size()))
                .findFirst().orElseThrow();

    }
}
