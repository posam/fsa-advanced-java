package sk.posam.fsa;

import sk.posam.fsa.employee.Employee;
import sk.posam.fsa.employee.SoftwareEngineer;
import sk.posam.fsa.employee.Tester;

import java.math.BigDecimal;
import java.util.function.Function;

public class Main {

    enum Status {
        UNPROCESSED, PROCESSED
    }

    record Order(
            Status status,
            BigDecimal price
    ) {
    }

    static int k = 0;


    public static void main(String[] args) {

        class UserNew {

            private final Long id;
            private final String name;
            private final String surname;


            UserNew() {
                this.id = 1L;
                this.name = "nam";
                this.surname = "surname";
            }
        }


        User user = new User(null, "ja", "ty");
        System.out.println(user.fullName());

        user.fullName();


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

        Function<Integer, Integer> timesTwo
                = (Integer i) -> {
            System.out.println("multiplying");
            return (i * 2) + k;
        };

        System.out.println(timesTwo.apply(2));
        k = 2;
        System.out.println(timesTwo.apply(2));
    }

    static Integer timesTwoStatic(Integer i) {
        return (i * 2) + k;
    }

}
