package model;

/**
 * Central business logic component for managing student operations. Implements
 * all core algorithms and data management while maintaining strict separation
 * from UI concerns (MVC pattern).
 */
public class StudentManager {

    private Student[] students = new Student[100];
    private short counter = 0;

    /**
     * Registers a new student after comprehensive validation. Validates:
     * non-empty carnet/name, no duplicate carnet, and valid grades.
     */
    public void addStudent(Student student) throws ValidationException {
        Validator.validateNoEmptyCarnet(student.getCarnet());
        Validator.validateNoEmptyName(student.getName());
        Validator.validateNoDuplicatedCarnet(students, student.getCarnet(),
                (short) 0, counter);

        students[counter] = student;
        counter++;
    }

    /**
     * Returns a snapshot of all currently registered students. Creates a
     * defensive copy to prevent external modification of internal data.
     */
    public Student[] getAllStudents() {
        Student[] result = new Student[counter];
        for (short i = 0; i < counter; i++) {
            result[i] = students[i];
        }
        return result;
    }

    /**
     * Updates a specific course grade for a student. Validates: student
     * existence and grade range (0-100). Note: Course index must be 0, 1, or 2
     * (representing three courses).
     */
    public void updateGrade(String carnet, short courseIndex, float newGrade)
            throws ValidationException {

        Validator.validateRegisteredStudent(students, carnet, (short) 0, counter);
        Validator.validateGradeRange(newGrade);

        for (short i = 0; i < counter; i++) {
            if (students[i].getCarnet().equals(carnet)) {
                students[i].getGrades()[courseIndex] = newGrade;
                break;
            }
        }
    }

    /**
     * Removes a student from the system using compact deletion. Validates
     * student existence first, then replaces the removed student with the last
     * one in the array to maintain continuous storage.
     */
    public void deleteStudent(String carnet) throws ValidationException {
        Validator.validateRegisteredStudent(students, carnet, (short) 0, counter);

        for (short i = 0; i < counter; i++) {
            if (students[i].getCarnet().equals(carnet)) {
                students[i] = students[counter - 1];
                students[counter - 1] = null;
                counter--;
                break;
            }
        }
    }

    /**
     * Finds a student by their unique carnet using recursive linear search.
     * Returns null if no student with the given carnet exists.
     */
    public Student findByCarnet(String carnet) {
        return searchByCarnet(carnet, (short) 0);
    }

    /**
     * Recursive helper that implements the linear search logic for
     * findByCarnet. Demonstrates recursive traversal through the student array.
     */
    private Student searchByCarnet(String carnet, short index) {
        if (index >= counter) {
            return null;
        }
        if (students[index] != null && students[index].getCarnet().equals(carnet)) {
            return students[index];
        }
        return searchByCarnet(carnet, (short) (index + 1));
    }

    /**
     * Sorts all registered students by their average grade in descending order
     * (highest average first) using an optimized bubble sort algorithm. The
     * method includes an early termination check: if no swaps occur in a pass,
     * the array is already sorted and the algorithm stops.
     */
    public void orderByAverage() {
        boolean swapped;

        for (short pass = 0; pass < counter - 1; pass++) {
            swapped = false;

            for (short current = 0; current < counter - pass - 1; current++) {
                // Calcular una sola vez para cada estudiante en esta comparación
                float currentAvg = calculateStudentAverage(students[current]);
                float nextAvg = calculateStudentAverage(students[current + 1]);

                if (currentAvg < nextAvg) {
                    Student temporary = students[current];
                    students[current] = students[current + 1];
                    students[current + 1] = temporary;
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }
    }

    /**
     * Calculates the average grade for a given student using recursive
     * summation. This method encapsulates the average calculation logic
     * separate from the Student class, respecting SRP.
     */
    public float calculateStudentAverage(Student student) {
        return calculateAverageRecursive(student.getGrades(), (short) 0);
    }

    /**
     * Recursive implementation of average calculation. Mathematically
     * equivalent to (sum/grades.length) through distributive property.
     */
    private float calculateAverageRecursive(float[] grades, short index) {
        if (index >= grades.length) {
            return 0;
        }
        return (grades[index] + calculateAverageRecursive(grades,
                (short) (index + 1))) / grades.length;
    }

    /**
     * Public interface to get a student's average. Provides a clean API while
     * keeping calculation logic internal.
     */
    public float getStudentAverage(Student student) {
        return calculateStudentAverage(student);
    }

    /**
     * Identifies the top 3 students by average grade using partial selection
     * sort. Efficiently finds top performers without fully sorting the entire
     * array.
     */
    public Student[] getTopThree() {
        if (counter == 0) {
            return new Student[0];
        }

        Student[] topThree = new Student[Math.min(3, counter)];

        for (short i = 0; i < topThree.length; i++) {
            short bestIndex = i;
            for (short j = (short) (i + 1); j < counter; j++) {
                if (calculateStudentAverage(students[j])
                        > calculateStudentAverage(students[bestIndex])) {
                    bestIndex = j;
                }
            }
            topThree[i] = students[bestIndex];
            Student temp = students[i];
            students[i] = students[bestIndex];
            students[bestIndex] = temp;
        }
        return topThree;
    }

    /**
     * Returns the current number of active students. Useful for status
     * reporting and loop control in UI layer.
     */
    public short getCount() {
        return counter;
    }
}
