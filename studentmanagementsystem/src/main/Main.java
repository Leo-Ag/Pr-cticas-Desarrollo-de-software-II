package main;

import controller.AppController;

/**
 * Application entry point.
 * Creates and starts the main controller which initializes the MVC architecture.
 * Minimal main class following the single responsibility principle.
 */
public class Main {
    
    /**
     * Main method that launches the Student Management System.
     * Handles any uncaught exceptions at the application level.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        try {
            AppController controller = new AppController();
            controller.start();
        } catch (Exception e) {
            System.out.println("Error critico en la aplicacion: " + e.getMessage());
        }
    }
}

