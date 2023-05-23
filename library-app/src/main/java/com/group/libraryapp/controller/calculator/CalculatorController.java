package com.group.libraryapp.controller.calculator;

import com.group.libraryapp.dto.calculator.request.CalculatorAddRequest;
import com.group.libraryapp.dto.calculator.request.CalculatorMultiplyRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController // api 클래스를 진입 지점으로 만들어준다.
public class CalculatorController {

//    @GetMapping("/add")
//    public int addToNumbers(@RequestParam int number1 , @RequestParam int number2) {
//        // 단순한 파라미터라면 상관이 없지만, 많은 값이 들어올때는 당연히 객체로 받아줘야한다.
//        return number1 + number2;
//    }

    @GetMapping("/add")
    public int addToNumbers(CalculatorAddRequest request) {
        System.out.println(request.toString());
        return request.getNumber1() + request.getNumber2();
    }

    @PostMapping("/multiply")
    public int multiplyTowNumbers(@RequestBody CalculatorMultiplyRequest request) {
        // body 가 들어올때는 @RequestBody 반드시 적어준다.
        // POST 는 생성자가 필요가 없다.
        // RequestBody 는 JSON -> DTO 로(CalculatorMultiplyRequest) 바꿔준다.
        // DTO 의 필드명은 요청보낼때의 키값과 같아야한다.

      return request.getNumber1() * request.getNumber2();
    }
}
