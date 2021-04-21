package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {
    @PostMapping("/calculate")
    public String calculate(@RequestParam("first-operand") float firstOperand,
                            @RequestParam("second-operand") float secondOperand,
                            @RequestParam("operator") char operator, Model model) {
        model.addAttribute("firstOperand",firstOperand);
        model.addAttribute("secondOperand",secondOperand);
        try {
            float result = calculateOption(firstOperand, secondOperand, operator);
            model.addAttribute("result",result);

        } catch (Exception ex) {
            String result = ex.getMessage();
            model.addAttribute("result",result);
        }
        return "index";

    }

    private float calculateOption(float firstOperand, float secondOperand, char operator) {
        switch (operator) {
            case '+':
                return firstOperand + secondOperand;
            case '-':
                return firstOperand - secondOperand;
            case '*':
                return firstOperand * secondOperand;
            case '/':
                if (secondOperand != 0)
                    return firstOperand / secondOperand;
                else
                    throw new RuntimeException("Can't divide by zero");
            default:
                throw new RuntimeException("Invalid operation");
        }
    }

}
