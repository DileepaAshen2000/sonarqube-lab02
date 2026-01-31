package main.java.com.example;

import java.util.logging.Logger;

public class App {

    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        LOGGER.info(() -> String.valueOf(calc.calculate(10, 5, "add-again")));

        UserService service = new UserService();
        try {
            service.findUser("admin");
            service.deleteUser("admin");
        } catch (Exception e) {
            LOGGER.severe("UserService failed: " + e.getMessage());
        }
    }
}
