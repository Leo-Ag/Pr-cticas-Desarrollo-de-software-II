package View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleView {

    private BufferedReader br;

    public ConsoleView() {
        this.br = new BufferedReader(new InputStreamReader(System.in));
    }

    // ==================== MAIN MENU ====================
    public byte mostrarMenuPrincipal() throws IOException {
        System.out.println("\n=== SISTEMA DE MATRÍCULA ===");
        System.out.println("1. Registrar estudiante");
        System.out.println("2. Registrar curso");
        System.out.println("3. Matricular estudiante");
        System.out.println("4. Listar estudiantes");
        System.out.println("5. Listar cursos");
        System.out.println("6. Listar matrículas");
        System.out.println("7. Ver matrículas por estudiante");
        System.out.println("8. Salir");
        System.out.print("Seleccione opción: ");
        return Byte.parseByte(br.readLine());
    }

    // ==================== LECTURA DE DATOS ====================
  
    private void clearBuffer() throws IOException {
        if (br.ready()) {
            br.readLine();
        }
    }

    public String readCarnet() throws IOException {
        System.out.print("Ingrese carnet: ");
        clearBuffer();  // Limpiar antes de leer
        return br.readLine();
    }

    public String readName() throws IOException {
        System.out.print("Ingrese nombre: ");
        // No necesita limpiar si viene después de otro String
        return br.readLine();
    }

    public String readCourseCode() throws IOException {
        System.out.print("Ingrese código del curso: ");
        clearBuffer();  // Limpiar si viene después de un número
        return br.readLine();
    }

    public String readCourseName() throws IOException {
        System.out.print("Ingrese nombre del curso: ");
        return br.readLine();
    }

    public byte readCredits() throws IOException {
        System.out.print("Ingrese créditos del curso: ");
        try {
            byte credits = Byte.parseByte(br.readLine());
            return credits;
        } catch (NumberFormatException e) {
            throw new IOException("Los créditos deben ser un número válido");
        }
    }

    public byte readQuota() throws IOException {
        System.out.print("Ingrese cupo del curso: ");
        try {
            byte quota = Byte.parseByte(br.readLine());
            return quota;
        } catch (NumberFormatException e) {
            throw new IOException("El cupo debe ser un número válido");
        }
    }

    // ==================== MOSTRAR RESULTADOS ====================
    public void mostrarMensaje(String mensaje) {
        System.out.println("ℹ️  " + mensaje);
    }

    public void mostrarExito(String accion) {
        System.out.println("✅ " + accion + " exitoso/a.");
    }

    public void showError(String error) {
        System.err.println("❌ Error: " + error);
    }

    public void showLista(String titulo, String[] items) {
        System.out.println("\n=== " + titulo + " ===");
        if (items == null || items.length == 0) {
            System.out.println("No hay elementos para mostrar.");
        } else {
            mostrarListaRecursiva(items, 0);
            System.out.println("Total: " + items.length + " elemento(s)");
        }
    }

    private void mostrarListaRecursiva(String[] items, int index) {
        if (index >= items.length) {
            return;
        }
        System.out.println("• " + items[index]);
        mostrarListaRecursiva(items, index + 1);
    }

    public void mostrarEncabezado(String encabezado) {
        System.out.println("\n--- " + encabezado + " ---");
    }

    // ==================== UTILIDADES ====================
    public void clearScreen() {
        clearScreenRecursive(0);
    }

    private void clearScreenRecursive(int lineas) {
        if (lineas >= 50) {
            return;
        }
        System.out.println();
        clearScreenRecursive(lineas + 1);
    }

    public void pause() throws IOException {
        System.out.print("\nPresione Enter para continuar...");
       clearBuffer();  // Limpiar antes
        br.readLine();    // Esperar Enter
    }
}
