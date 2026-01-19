package controller;

import model.Student;
import model.StudentManager;
import model.ValidationException;
import view.ConsoleView;

/**
 * Orchestrates the flow between the View and Model in the MVC pattern.
 * Responsible for handling user menu selections, invoking business logic, and
 * passing data between UI and business layers.
 */
public class AppController {

    private StudentManager model;
    private ConsoleView view;
    private boolean running;

    /**
     * Initializes the controller with new instances of Model and View.
     * Establishes the core MVC triangle for the application.
     */
    public AppController() {
        this.model = new StudentManager();
        this.view = new ConsoleView();
        this.running = true;
    }

    /**
     * Main application entry point after instantiation. Controls the primary
     * application loop and main menu navigation.
     */
    public void start() {
        while (running) {
            view.showMainMenu();
            byte option = view.readOption();

            if (option == 1) {
                handleStudentMenu();
            } else if (option == 2) {
                running = false;
                view.showMessage("Saliendo del sistema...");
            } else {
                view.showError("Opcion no valida");
            }
        }
    }

    /**
     * Manages the student operations submenu. Continuously displays student
     * menu until user returns to main menu.
     */
    private void handleStudentMenu() {
        boolean inStudentMenu = true;

        while (inStudentMenu) {
            view.showStudentMenu();
            byte option = view.readOption();

            switch (option) {
                case 1 ->
                    addStudent();
                case 2 ->
                    listAllStudents();
                case 3 ->
                    updateStudentGrade();
                case 4 ->
                    deleteStudent();
                case 5 ->
                    sortStudentsByAverage();
                case 6 ->
                    searchStudent();
                case 7 ->
                    showTopThreeAverages();
                case 8 ->
                    inStudentMenu = false;
                default ->
                    view.showError("Opcion no valida");
            }

            if (inStudentMenu && option != 8) {
                view.waitForEnter();
            }
        }
    }

    /**
     * Handles the complete student registration flow. Collects data from view,
     * validates through model, and shows results.
     */
    private void addStudent() {
        try {
            String[] data = view.enterStudentData();

            float[] grades = new float[3];
            for (int i = 0; i < 3; i++) {
                grades[i] = Float.parseFloat(data[2 + i]);
            }

            Student newStudent = new Student(data[0], data[1], grades);
            model.addStudent(newStudent);

            view.showMessage("Estudiante registrado exitosamente");

        } catch (ValidationException e) {
            view.showError(e.getMessage());
        } catch (NumberFormatException e) {
            view.showError("Las notas deben ser numeros validos");
        }
    }

    /**
     * Retrieves and displays all registered students. Shows informative message
     * if no students are registered.
     */
    private void listAllStudents() {
        Student[] students = model.getAllStudents();
        view.showStudentList(students);
    }

    /**
     * Manages the grade update process for a specific student. Validates
     * student existence and grade range through model.
     */
    private void updateStudentGrade() {
        try {
            String carnet = view.readCarnet("Ingrese el carnet del estudiante: ");
            short courseIndex = view.readCourseIndex();
            float newGrade = view.readGrade("Ingrese la nueva nota: ");

            model.updateGrade(carnet, courseIndex, newGrade);
            view.showMessage("Nota actualizada exitosamente");

        } catch (ValidationException e) {
            view.showError(e.getMessage());
        }
    }

    /**
     * Handles student deletion after confirming existence. Uses carnet as the
     * unique identifier for deletion.
     */
    private void deleteStudent() {
        try {
            String carnet = view.readCarnet("Ingrese el carnet del estudiante a eliminar: ");

            model.deleteStudent(carnet);
            view.showMessage("Estudiante eliminado exitosamente");

        } catch (ValidationException e) {
            view.showError(e.getMessage());
        }
    }

    /**
     * Triggers the sorting algorithm and displays sorted results. Sorts
     * students by average grade in descending order.
     */
    private void sortStudentsByAverage() {
        model.orderByAverage();
        view.showMessage("Estudiantes ordenados por promedio (mayor a menor)");
        listAllStudents();
    }

    /**
     * Provides student search functionality by carnet or name. Displays
     * detailed information for found students.
     */
    private void searchStudent() {
        view.showMessage("Buscar por:");
        System.out.println("1. Carnet");
        System.out.println("2. Nombre");
        System.out.print("Seleccione: ");

        byte searchType = view.readOption();

        if (searchType == 1) {
            
            String carnet = view.readCarnet("Ingrese el carnet: ");
            Student student = model.findByCarnet(carnet);

            if (student != null) {
                float average = model.getStudentAverage(student);
                view.showStudentDetails(student, average);
            } else {
                view.showError("Estudiante no encontrado");
            }

        } else if (searchType == 2) {
            // Búsqueda por nombre (NUEVO)
            String name = view.readName("Ingrese el nombre: ");
            Student student = model.findByName(name); 

            if (student != null) {
                float average = model.getStudentAverage(student);
                view.showStudentDetails(student, average);
            } else {
                view.showError("Estudiante no encontrado");
            }

        } else {
            view.showError("Opcion no valida");
        }
    }

    /**
     * Displays the top 3 students based on average grades. Calculates averages
     * separately for display purposes.
     */
    private void showTopThreeAverages() {
        Student[] topThree = model.getTopThree();

        if (topThree.length == 0) {
            view.showMessage("No hay estudiantes registrados");
            return;
        }

        float[] averages = new float[topThree.length];
        for (int i = 0; i < topThree.length; i++) {
            averages[i] = model.getStudentAverage(topThree[i]);
        }

        view.showTopThree(topThree, averages);
    }
}
