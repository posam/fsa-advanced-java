package sk.posam.fsa.exception;

public class ErrorExample {

    public static void main(String[] args) {

        overflow();

    }

    private static void overflow() {
        overflow();
    }

}
