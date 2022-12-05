package com.example.skyprocoursework3.service;

import com.example.skyprocoursework3.essense.Question;
import com.example.skyprocoursework3.exception.IncorrectAmountOfQuestionsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class JavaQuestionServiceTest {

    private JavaQuestionService questionService;
    private List<Question> questions = List.of(
            new Question("Q1", "A1"),
            new Question("Q2", "A2"),
            new Question("Q3", "A3"),
            new Question("Q4", "A4"),
            new Question("Q5", "A5")
    );


    @BeforeEach
    void setUp() {
        this.questionService = new JavaQuestionService();
        questions.forEach(questionService::add);
    }

    @Test
    void add() {
        Question question = new Question("Q6", "A6");
        Question actual = questionService.add(question);
        assertThat(actual).isEqualTo(question);
        assertThat(questionService.getAll()).hasSize(6).contains(question);
    }


    @Test
    void remove() {
        Question expected = this.questions.get(0);
        Question actual = questionService.remove(expected);
        assertThat(actual).isEqualTo(expected);
        assertThat(questionService.getAll()).hasSize(4).doesNotContain(expected);
    }

    @Test
    void getAll() {
        assertThat(questionService.getAll())
                .hasSize(5)
                .containsAll(questions);
    }

    @Test
    void getRandomQuestion() {
        Question actual = questionService.getRandomQuestion();
        assertThat(actual).isIn(questions);
    }

    @Test
    void QuestionNotFoundException() {
        questionService = new JavaQuestionService();
        assertThatThrownBy(() -> questionService.getRandomQuestion())
                .isInstanceOf(IncorrectAmountOfQuestionsException.class);
    }

}