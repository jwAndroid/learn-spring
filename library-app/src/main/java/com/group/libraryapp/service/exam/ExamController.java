package com.group.libraryapp.service.exam;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExamController {

    private final Exam exam;

    // 셋중 하나만 가져오고 싶을때
//    public ExamController(@Qualifier("exam1") Exam exam) {
//        this.exam = exam;
//    }

    public ExamController(@Qualifier("main") Exam exam) {
        this.exam = exam;
    }
}
