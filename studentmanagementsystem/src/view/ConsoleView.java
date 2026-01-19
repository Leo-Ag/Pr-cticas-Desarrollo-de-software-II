package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import model.Student;

/**
 * Handles all user interface operations for the Student Management System.
 * Responsible for displaying information and collecting user input.
 * Follows strict MVC separation - no business logic, only presentation.
 */
public class ConsoleView {
    
    private BufferedReader reader;
    
    public ConsoleView() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }
    
    // ==================== DISPLAY METHODS ====================
    
    public void showMainMenu() {
        System.out.println("\n=== SISTEMA DE GESTION DE ESTUDIANTES ===");
        System.out.println("1. Gestionar Estudiantes");
        System.out.println("2. Salir");
        System.out.print("Seleccione una opcion: ");
    }
    
    public void showStudentMenu() {
        System.out.println("\n=== GESTION DE ESTUDIANTES ===");
        System.out.println("1. Registrar nuevo estudiante");
        System.out.println("2. Listar todos los estudiantes");
        System.out.println("3. Modificar nota de un estudiante");
        System.out.println("4. Eliminar estudiante");
        System.out.println("5. Ordenar estudiantes por promedio");
        System.out.println("6. Buscar estudiante");
        System.out.println("7. Mostrar 3 mejores promedios");
        System.out.println("8. Volver al menu principal");
        System.out.print("Seleccione una opcion: ");
    }
    
    public void showStudentList(Student[] students) {
        if (students.length == 0) {
            System.out.println("\nNo hay estudiantes registrados.");
            return;
        }
        
        System.out.println("\n=== LISTA DE ESTUDIANTES ===");
        System.out.println("Carnet     Nombre                          Promedio");
        System.out.println("----------------------------------------------------");
        
        for (Student student : students) {
            // Simple concatenation without printf
            String carnet = student.getCarnet();
            String name = student.getName();
            float average = student.getGrades()[0]; // Placeholder
            
            // Pad strings manually for alignment
            String paddedCarnet = carnet;
            while (paddedCarnet.length() < 10) paddedCarnet += " ";
            
            String paddedName = name;
            while (paddedName.length() < 30) paddedName += " ";
            
            System.out.println(paddedCarnet + " " + paddedName + " " + average);
        }
    }
    
    public void showTopThree(Student[] topThree, float[] averages) {
        if (topThree.length == 0) {
            System.out.println("\nNo hay estudiantes para mostrar.");
            return;
        }
        
        System.out.println("\n=== TOP 3 MEJORES PROMEDIOS ===");
        for (byte i = 0; i < topThree.length; i++) {
            System.out.println((i + 1) + ". " + topThree[i].getName() + " - " + averages[i]);
        }
    }
    
    public void showStudentDetails(Student student, float average) {
        if (student == null) {
            System.out.println("\nEstudiante no encontrado.");
            return;
        }
        
        System.out.println("\n=== INFORMACION DEL ESTUDIANTE ===");
        System.out.println("Carnet: " + student.getCarnet());
        System.out.println("Nombre: " + student.getName());
        System.out.println("Nota 1: " + student.getGrades()[0]);
        System.out.println("Nota 2: " + student.getGrades()[1]);
        System.out.println("Nota 3: " + student.getGrades()[2]);
        System.out.println("Promedio: " + average);
    }
    
    public void showMessage(String message) {
        System.out.println("\n" + message);
    }
    
    public void showError(String error) {
        System.out.println("\n[ERROR] " + error);
    }
    
    // ==================== INPUT METHODS ====================
    
    public String[] enterStudentData() {
        System.out.println("\n=== REGISTRO DE NUEVO ESTUDIANTE ===");
        
        String[] data = new String[5];
        
        System.out.print("Nombre completo: ");
        data[0] = readLine();
        
        System.out.print("Carnet: ");
        data[1] = readLine();
        
        for (byte i = 0; i < 3; i++) {
            System.out.print("Nota del curso " + (i + 1) + ": ");
            data[2 + i] = readLine();
        }
        
        return data;
    }
    
    public String readCarnet(String prompt) {
        System.out.print(prompt);
        return readLine();
    }
    
    public String readName(String prompt) {
        System.out.print(prompt);
        return readLine();
    }
    
    public byte readOption() {
        while (true) {
            try {
                return Byte.parseByte(readLine());
            } catch (NumberFormatException e) {
                System.out.print("Por favor ingrese un numero valido: ");
            }
        }
    }
    
    public float readGrade(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Float.parseFloat(readLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un numero valido (ej: 85.5)");
            }
        }
    }
    
    public short readCourseIndex() {
        while (true) {
            System.out.print("Numero del curso (1, 2 o 3): ");
            String input = readLine();
            if (input.equals("1") || input.equals("2") || input.equals("3")) {
                return (short) (Byte.parseByte(input) - 1);
            }
            System.out.println("Por favor ingrese 1, 2 o 3");
        }
    }
    
    // ==================== UTILITY METHODS ====================
    
    private String readLine() {
        try {
            return reader.readLine().trim();
        } catch (IOException e) {
            return "";
        }
    }
    
    public void waitForEnter() {
        System.out.print("\nPresione Enter para continuar...");
        readLine();
    }
    
    public void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}