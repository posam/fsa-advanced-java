package sk.posam.fsa;

import sk.posam.fsa.employee.Employee;
import sk.posam.fsa.employee.SoftwareEngineer;
import sk.posam.fsa.employee.Tester;

import java.math.BigDecimal;

public class Main {


    public static void main(String[] args) {
        Employee employee = new Tester();
        String em = switch (employee) {
            case Tester tester -> "tester";
            case SoftwareEngineer softwareEngineer -> "Software Engineer";
            default -> "General Employee";
        };

        BigDecimal salary = new BigDecimal("123.45");

        switch (salary) {
            case BigDecimal value when value.compareTo(new BigDecimal("100")) < 0 -> System.out.println("Low salary");
            case BigDecimal value when value.compareTo(new BigDecimal("200")) < 0 ->
                    System.out.println("Medium salary");
            case BigDecimal ignored -> System.out.println("High salary");
        }
    }

}
