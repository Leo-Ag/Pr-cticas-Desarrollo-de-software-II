package controller;

import Model.Excepcion.BussinessException;
import Model.Service.RegisterService;
import view.ConsoleView;
import java.io.IOException;

/**
 * Coordinates the flow between View and Model following MVC pattern.
 * Manages user interactions and delegates operations to appropriate components.
 */
public class AppController {
    private RegisterService model;
    private ConsoleView view;
    private boolean isRunning;
    
    /**
     * Default constructor initializes all MVC components.
     */
    public AppController() {
        this.model = new RegisterService();
        this.view = new ConsoleView();
        this.isRunning = true;
    }
    
    /**
     * Constructor for dependency injection (useful for testing).
     * @param model RegisterService instance
     * @param view ConsoleView instance
     */
    public AppController(RegisterService model, ConsoleView view) {
        this.model = model;
        this.view = view;
        this.isRunning = true;
    }
    
    /**
     * Main entry point of the application.
     * Starts the main loop and handles critical errors.
     */
    public void start() {
        try {
            initializeSystem();
            mainLoop();
            
        } catch (Exception e) {
            view.showError("Error critico en el sistema: " + e.getMessage());
            e.printStackTrace();
        }
        
        view.showMessage("Programa finalizado. Hasta pronto.");
    }
    
    /**
     * Initializes the system with welcome messages.
     */
    private void initializeSystem() {
        view.clearScreen();
        view.showMessage("=== SISTEMA DE MATRICULA UNIVERSITARIA ===");
        view.showMessage("Desarrollado por: Leonardo Aguilar");
        view.showMessage("Curso: Desarrollo de Software II");
        view.showMessage("===========================================\n");
    }
    
    /**
     * Main application loop that processes user options.
     */
    private void mainLoop() {
        while (isRunning) {
            try {
                byte option = view.showMainMenu();
                processOption(option);
                
            } catch (IOException e) {
                view.showError("Error de entrada/salida: " + e.getMessage());
            } catch (NumberFormatException e) {
                view.showError("Debe ingresar un numero valido");
            } catch (BussinessException e) {
                view.showError(e.getMessage());
            } catch (Exception e) {
                view.showError("Error inesperado: " + e.getMessage());
            }
            
            try {
                view.pause();
                view.clearScreen();
            } catch (IOException e) {
                view.showError("Error al pausar: " + e.getMessage());
            }
        }
    }
    
    /**
     * Processes the selected menu option.
     * @param option User's menu selection
     * @throws IOException If input/output error occurs
     * @throws BussinessException If business rules are violated
     */
    private void processOption(byte option) throws IOException, BussinessException {
        switch (option) {
            case 1 -> registerStudent();
            case 2 -> registerCourse();
            case 3 -> enrollStudent();
            case 4 -> listStudents();
            case 5 -> listCourses();
            case 6 -> listEnrollments();
            case 7 -> viewEnrollmentsByStudent();
            case 8 -> exitProgram();
            default -> view.showError("Opcion no valida. Intente de nuevo.");
        }
    }
    
    // ==================== USE CASES ====================
    
    private void registerStudent() throws IOException, BussinessException {
        view.showHeader("REGISTRO DE ESTUDIANTE");
        
        String carnet = view.readCarnet();
        String name = view.readName();
        
        model.registerStudent(carnet, name);
        view.showSuccess("Estudiante registrado");
    }
    
    private void registerCourse() throws IOException, BussinessException {
        view.showHeader("REGISTRO DE CURSO");
        
        String code = view.readCourseCode();
        String name = view.readCourseName();
        byte credits = view.readCredits();
        byte quota = view.readQuota();
        
        model.registerCourse(code, name, credits, quota);
        view.showSuccess("Curso registrado");
    }
    
    private void enrollStudent() throws IOException, BussinessException {
        view.showHeader("MATRICULA DE ESTUDIANTE");
        
        String carnet = view.readCarnet();
        String courseCode = view.readCourseCode();
        
        model.enrollStudent(carnet, courseCode);
        view.showSuccess("Matricula realizada");
    }
    
    private void listStudents() {
        view.showHeader("LISTA DE ESTUDIANTES");
        String[] students = model.listAllStudents();
        view.showList("ESTUDIANTES REGISTRADOS", students);
    }
    
    private void listCourses() {
        view.showHeader("LISTA DE CURSOS");
        String[] courses = model.listAllCourses();
        view.showList("CURSOS REGISTRADOS", courses);
    }
    
    private void listEnrollments() {
        view.showHeader("LISTA DE MATRICULAS");
        String[] enrollments = model.listAllEnrollments();
        view.showList("MATRICULAS REGISTRADAS", enrollments);
    }
    
    private void viewEnrollmentsByStudent() throws IOException {
        view.showHeader("CONSULTA DE MATRICULAS POR ESTUDIANTE");
        
        String carnet = view.readCarnet();
        String[] enrollments = model.listEnrollmentsByStudent(carnet);
        
        view.showList("MATRICULAS DEL ESTUDIANTE: " + carnet, enrollments);
    }
    
    private void exitProgram() {
        view.showMessage("Saliendo del sistema...");
        isRunning = false;
    }
}