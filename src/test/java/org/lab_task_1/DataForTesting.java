package org.lab_task_1;

import org.human.FullName;
import org.human.Human;
import org.lab_task_1.student.Student;

import java.time.LocalDate;

public class DataForTesting {
    public final static Human personAlexandreMerson = new Human(
        new FullName("Alexandre", "Igorevich", "Merson"),
        LocalDate.of(2002, 6, 24),
        "Male",
        "Russian"
    );
    public final static Student studentAlexandreMerson = new Student(personAlexandreMerson, "IMIT");

    public final static Human personPierreVeron = new Human(
        new FullName("Pierre", "", "Veron"),
        LocalDate.of(2002, 6, 24),
        "Male",
        "French"
    );
    public final static Student studentPierreVeron = new Student(personPierreVeron, "IMIT");

    public final static Human personJulieVirth = new Human(
        new FullName("Julie", "", "Virth"),
        LocalDate.of(2002, 6, 24),
        "Female",
        "Swiss"
    );
    public final static Student studentJulieVirth = new Student(personJulieVirth, "Economics");


    public final static Human personCyrillVirth = new Human(
        new FullName("Cyrill", "", "Virth"),
        LocalDate.of(1976, 12, 6),
        "Male",
        "Russian"
    );
    public final static Student studentCyrillVirth = new Student(personCyrillVirth, "Boilogy");

    public final static Human personOlgaMerson = new Human(
        new FullName("Olga", "Vladimirovna", "Merson"),
        LocalDate.of(1976, 12, 6),
        "Female",
        "Russian"
    );
    public final static Student studentOlgaMerson = new Student(personOlgaMerson, "Psychiatry");


    public final static Human personLucyEarl = new Human(
        new FullName("Lucy", "", "Earl"),
        LocalDate.of(2000, 5, 12),
        "Female",
        "English"
    );
    public final static Student studentLucyEarl = new Student(personLucyEarl, "Biology");

    public final static Human personLukeBrown = new Human(
        new FullName("Luke", "", "Brown"),
        LocalDate.of(2000, 5, 12),
        "Male",
        "English"
    );
    public final static Student studentLukeBrown = new Student(personLukeBrown, "Chemistry");

    public final static Human personAlexBabington = new Human(
        new FullName("Alex", "", "Babington"),
        LocalDate.of(2000, 5, 12),
        "Male",
        "English"
    );
    public final static Student studentAlexBabington = new Student(personAlexBabington, "Chemistry");

    public final static Human personLucyBrown = new Human(
        new FullName("Lucy", "", "Brown"),
        LocalDate.of(2000, 5, 12),
        "Female",
        "English"
    );
    public final static Student studentLucyBrown = new Student(personLucyBrown, "English Literature");

    public final static Human personLucyVirth = new Human(
        new FullName("Lucy", "", "Virth"),
        LocalDate.of(2000, 5, 12),
        "Female",
        "English"
    );
    public final static Student studentLucyVirth = new Student(personLucyVirth, "History");

    public final static Human personLucyGreen = new Human(
        new FullName("Lucy", "", "Green"),
        LocalDate.of(2000, 5, 12),
        "Female",
        "English"
    );
    public final static Student studentLucyGreen = new Student(personLucyGreen, "Phisics");

    public final static Human personArielGreen = new Human(
        new FullName("Ariel", "", "Green"),
        LocalDate.of(2000, 5, 12),
        "Female",
        "Scottish"
    );
    public final static Student studentArielGreen = new Student(personArielGreen, "Literature");


    public final static Human personAnnetBeaumarchais = new Human(
        new FullName("Annet", "", "Beaumarchez"),
        LocalDate.of(2013, 1, 11),
        "Female",
        "French"
    );

    public final static Human personNicolasBeau = new Human(
        new FullName("Nicolas", "", "Beau"),
        LocalDate.of(2013, 1, 11),
        "Male",
        "French"
    );
}
