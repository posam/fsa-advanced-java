package sk.posam.fsa.workshop;

import java.time.LocalDateTime;

public class Switch {

    enum DayOfWeek {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THRUSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY,
    }

    interface SoftwareEngineer {
        void doCode();
    }

    static class SeniorEngineer implements SoftwareEngineer {

        public final String firstName;

        SeniorEngineer(String firstName) {
            this.firstName = firstName;
        }

        @Override
        public void doCode() {

        }
    }

    static class JuniorEngineer implements SoftwareEngineer {

        public final String lastName;

        JuniorEngineer(String lastName) {
            this.lastName = lastName;
        }

        @Override
        public void doCode() {

        }
    }
    
    enum State {
        OPEN, IMPLEMENTING, TESTING, DONE
    }

    public static void main(String[] args) {
        DayOfWeek day = DayOfWeek.SATURDAY;

        String transalation = switch (day) {
            case MONDAY -> {
                //
                yield "pondelok";
            }
            default -> "nvm";
        };

        System.out.println(transalation);

        SoftwareEngineer eng = new JuniorEngineer("dudak");

        String s = switch (eng) {
            case SeniorEngineer senior when "pavol".equals(senior.firstName) -> "senior";
            case JuniorEngineer junior when "_dudak".equals(junior.lastName) -> "junior";
            case null -> "null";
            default -> throw new IllegalStateException("Unexpected value: " + eng);
        };

        System.out.println(s);

        State state = State.IMPLEMENTING;
        String message = switch (state) {
            case State open when State.OPEN.equals(open) -> "Transitioned to state %s, at %s".formatted(open, LocalDateTime.now());
            case State implementing when State.IMPLEMENTING.equals(implementing) -> "Transitioned to state %s, at %s".formatted(implementing, LocalDateTime.now());
            default -> throw new IllegalStateException("Unexpected value: " + state);
        };

        System.out.println(message);

    }

}
