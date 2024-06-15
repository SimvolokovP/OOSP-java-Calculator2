package controller;

import model.EquationModel;
import view.EquationView;

import java.util.Scanner;

public class EquationController {
    public static void main(String[] args) {
//        String example = "- 123 - 65 * (12 / 2) + 2 ^ 2";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your equation: ");
        String equation = scanner.nextLine();
        EquationModel equationModel = new EquationModel(equation);
        EquationView.displayResult(equation, equationModel.evaluateEquation());
    }
}