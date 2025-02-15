package sk.posam.fsa.exception;

import java.io.Closeable;
import java.io.IOException;
import java.util.*;

public class ExceptionExample {

    private enum Course {
        MATH, PHYSICS
    }

    private static final int MAX_STUDENTS = 2;
    private static final Map<Course, List<String>> COURSE_REGISTRATION = new HashMap<>();

    static {
        COURSE_REGISTRATION.put(Course.MATH, new ArrayList<>());
        COURSE_REGISTRATION.put(Course.PHYSICS, new ArrayList<>());
    }

    static class Trieda implements Closeable {

        public void nieco() {
            System.out.println("Nieco");
        }

        @Override
        public void close() throws IOException {
            System.out.println("Volam close()");
        }
    }

    public static void main(String[] args) throws IOException {
        Trieda t = new Trieda();
        try {
            t.nieco();
        } catch (Exception e) {

        } finally {
            t.close();
        }


        try {
            registerStudent(Course.MATH, "Alice");
            registerStudent(Course.MATH, "Bob");
            registerStudent(Course.MATH, "Charlie");
        } catch (CourseFullException e) {
            List<Course> availableCourses = COURSE_REGISTRATION.entrySet()
                    .stream()
                    .filter(entry -> entry.getValue().size() < MAX_STUDENTS)
                    .map(Map.Entry::getKey)
                    .toList();
            System.out.println("Course is full, here is list of available courses: " + availableCourses);
        }

    }

    private static void registerStudent(Course course, String student) throws CourseFullException {
        Objects.requireNonNull(course);
        Objects.requireNonNull(student);

        if (!COURSE_REGISTRATION.containsKey(course)) {
            throw new CourseNotFoundException();
        }

        List<String> students = COURSE_REGISTRATION.get(course);
        if (students.size() < MAX_STUDENTS) {
            students.add(student);
            System.out.println("Student %s registered to course %s".formatted(student, course));
        } else {
            throw new CourseFullException();
        }
    }


}
