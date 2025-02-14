package sk.posam.fsa.dependencies;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {

    private record Employee(String name, int age, String department) {
    }

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        OBJECT_MAPPER.findAndRegisterModules();
    }

    public static void main(String[] args) {

        try {
            String json = OBJECT_MAPPER.writeValueAsString(new Employee("John Doe", 30, "IT"));
            System.out.println(json);
        } catch (JsonProcessingException e) {
            System.out.println("Error occurred while processing JSON %s".formatted(e.getMessage()));
        }
    }

}
