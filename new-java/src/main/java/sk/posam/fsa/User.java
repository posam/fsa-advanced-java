package sk.posam.fsa;

import java.util.Objects;

public record User(
        Long id,
        String name,
        String surname
) {

//    TOTO dopisat konstruktory a metody ukazkove


    public User {
        id = Objects.requireNonNullElse(id, 1L);
    }

    public String fullName() {
        return "%s %s".formatted(name, surname);
    }
}
