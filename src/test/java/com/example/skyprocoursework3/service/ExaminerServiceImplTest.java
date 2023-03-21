package com.example.skyprocoursework3.service;

import com.example.skyprocoursework3.essense.Question;
import com.example.skyprocoursework3.exception.IncorrectAmountOfQuestionsException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {
    @Mock
    private JavaQuestionService javaQuestionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @Test
    void getQuestionsDistinctTest() {
        List<Question> questionList = List.of(
                new Question("Q1", "A1"),
                new Question("Q1", "A1"),
                new Question("Q1", "A1")
        );
        when(javaQuestionService.getAll()).thenReturn(questionList);
        when(javaQuestionService.getRandomQuestion()).thenReturn(
                questionList.get(0),
                questionList.get(1)
        );
        assertThat(examinerService.getQuestions(2)).hasSize(2)
                .containsOnly(questionList.get(0), questionList.get(1));
    }

    @Test
    void IncorrectAmountOfQuestionsException() {
        when(javaQuestionService.getAll()).thenReturn(Collections.emptyList());
        assertThatThrownBy(() -> examinerService.getQuestions(111))
                .isInstanceOf(IncorrectAmountOfQuestionsException.class);
    }

}