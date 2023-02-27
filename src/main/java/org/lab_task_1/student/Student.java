package org.lab_task_1.student;

import org.lab_task_1.human.FullName;
import org.lab_task_1.human.Human;

import java.time.LocalDate;

public class Student extends Human {
    private String faculty;

    public Student(
        final FullName fullName,
        final LocalDate birthDate,
        final String gender,
        final String nationality,
        final int height,
        final int weight,
        final String faculty
    ) {
        super(fullName, birthDate, gender, nationality, height, weight);
        if (faculty == null) {
            throw new IllegalArgumentException(
                "Null-ref passed to Student constructor as faculty-name"
            );
        }
        if (faculty.isEmpty()) {
            throw new IllegalArgumentException(
                "The empty string passed to Student constructor as faculty-name"
            );
        }
        this.faculty = faculty;
    }

    public Student(final Student otherStudent) {
        super(otherStudent);
        faculty = otherStudent.faculty;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(final String newFaculty) {
        if (newFaculty == null) {
            throw new IllegalArgumentException(
                "Null-ref passed to setFaculty as new-faculty-name"
            );
        }
        if (newFaculty.isEmpty()) {
            throw new IllegalArgumentException(
                "The empty string passed to setFaculty as new-faculty-name"
            );
        }
        if (!faculty.equals(newFaculty)) {
            faculty = newFaculty;
        }
    }
}
