package main;

import controller.AppController;

/**
 * Main entry point for the Enrollment System application.
 * Follows MVC pattern with minimal responsibilities.
 */
public class MainApp {
    /**
     * Main method - starts the MVC application.
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        try {
            AppController controller = new AppController();
            controller.start();
            
        } catch (Exception e) {
            System.err.println("ERROR FATAL: No se pudo iniciar el sistema");
            System.err.println("Detalles: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}