package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Handles all user interface interactions in the console.
 * Methods and variables in English, displayed text in Spanish.
 */
public class ConsoleView {

    private final BufferedReader inputReader;

    public ConsoleView() {
        this.inputReader = new BufferedReader(new InputStreamReader(System.in));
    }

    // ==================== MAIN MENU ====================
    
    /**
     * Displays the main menu and reads user selection.
     * @return selected option as byte
     * @throws IOException if input reading fails
     */
    public byte showMainMenu() throws IOException {
        System.out.println("\n=== SISTEMA DE MATRICULA ===");
        System.out.println("1. Registrar estudiante");
        System.out.println("2. Registrar curso");
        System.out.println("3. Matricular estudiante");
        System.out.println("4. Listar estudiantes");
        System.out.println("5. Listar cursos");
        System.out.println("6. Listar matriculas");
        System.out.println("7. Ver matriculas por estudiante");
        System.out.println("8. Salir");
        System.out.print("Seleccione opcion: ");
        return Byte.parseByte(inputReader.readLine());
    }

    // ==================== DATA INPUT METHODS ====================
    
    private void clearInputBuffer() throws IOException {
        if (inputReader.ready()) {
            inputReader.readLine();
        }
    }

    public String readCarnet() throws IOException {
        System.out.print("Ingrese carnet: ");
        clearInputBuffer();
        return inputReader.readLine();
    }

    public String readName() throws IOException {
        System.out.print("Ingrese nombre: ");
        return inputReader.readLine();
    }

    public String readCourseCode() throws IOException {
        System.out.print("Ingrese codigo del curso: ");
        clearInputBuffer();
        return inputReader.readLine();
    }

    public String readCourseName() throws IOException {
        System.out.print("Ingrese nombre del curso: ");
        return inputReader.readLine();
    }

    public byte readCredits() throws IOException {
        System.out.print("Ingrese creditos del curso: ");
        try {
            return Byte.parseByte(inputReader.readLine());
        } catch (NumberFormatException e) {
            throw new IOException("Los creditos deben ser un numero valido");
        }
    }

    public byte readQuota() throws IOException {
        System.out.print("Ingrese cupo del curso: ");
        try {
            return Byte.parseByte(inputReader.readLine());
        } catch (NumberFormatException e) {
            throw new IOException("El cupo debe ser un numero valido");
        }
    }

    // ==================== DISPLAY METHODS ====================
    
    public void showMessage(String message) {
        System.out.println("INFO: " + message);
    }

    public void showSuccess(String action) {
        System.out.println("EXITO: " + action + " realizado exitosamente.");
    }

    public void showError(String error) {
        System.err.println("ERROR: " + error);
    }

    public void showList(String title, String[] items) {
        System.out.println("\n=== " + title + " ===");
        if (items == null || items.length == 0) {
            System.out.println("No hay elementos para mostrar.");
        } else {
            displayListRecursive(items, 0);
            System.out.println("Total: " + items.length + " elemento(s)");
        }
    }

    private void displayListRecursive(String[] items, int index) {
        if (index >= items.length) {
            return;
        }
        System.out.println("- " + items[index]);
        displayListRecursive(items, index + 1);
    }

    public void showHeader(String header) {
        System.out.println("\n--- " + header + " ---");
    }

    // ==================== UTILITY METHODS ====================
    
    public void clearScreen() {
        clearScreenRecursive(0);
    }

    private void clearScreenRecursive(int lines) {
        if (lines >= 50) {
            return;
        }
        System.out.println();
        clearScreenRecursive(lines + 1);
    }

    public void pause() throws IOException {
        System.out.print("\nPresione Enter para continuar...");
        clearInputBuffer();
        inputReader.readLine();
    }
}