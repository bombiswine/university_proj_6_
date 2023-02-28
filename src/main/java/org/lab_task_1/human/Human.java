package org.lab_task_1.human;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class Human implements Comparable<Human> {
    protected FullName    fullName;
    protected LocalDate   birthDate;
    protected HumanGender gender;
    protected String      nationality;
    protected int         height;
    protected int         weight;

    public Human(
        final FullName  fullName,
        final LocalDate birthDate,
        final String    gender,
        final String    nationality,
        final int       height,
        final int       weight
    ) {
        if (fullName == null) {
            throw new IllegalArgumentException(
                "The null passed into Human's constructor as full name argument"
            );
        }
        if (birthDate == null) {
            throw new IllegalArgumentException(
                "The null passed into Human's constructor as birth date argument"
            );
        }
        if (gender == null) {
            throw new IllegalArgumentException(
                "The null passed into Human's constructor as human gender argument"
            );
        }
        if (nationality == null || nationality.isEmpty()) {
            throw new IllegalArgumentException(
                "The null passed into Human's constructor as nationality argument"
            );
        }
        if (height <= 0) {
            throw new IllegalArgumentException(
                "A negative height value passed into Human's constructor"
            );
        }
        if (weight <= 0) {
            throw new IllegalArgumentException(
                "A negative weight value passed into Human's constructor"
            );
        }

        this.fullName    = fullName;
        this.birthDate   = birthDate;
        this.gender      = HumanGender.getGenderByValue(gender);
        this.height      = height;
        this.weight      = weight;
        this.nationality = nationality;
    }

    public Human(final Human person) {
        if (person == null) {
            throw new IllegalArgumentException(
                "The null-ref passed as Human argument into Human's copy constructor"
            );
        }

        fullName    = new FullName(person.getFullName());
        birthDate   = person.getBirthDate();
        gender      = person.getGender();
        height      = person.getHeight();
        weight      = person.getWeight();
        nationality = person.getNationality();
    }

    public FullName getFullName() {
        return fullName;
    }

    public String getName() {
        return fullName.getFirstName();
    }

    public String getSurname() {
        return fullName.getSurname();
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public HumanGender getGender() {
        return gender;
    }

    public String getNationality() {
        return nationality;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Human human)) {
            return false;
        }

        return getHeight() == human.getHeight()
                   && getWeight() == human.getWeight()
                   && getFullName().equals(human.getFullName())
                   && getBirthDate().equals(human.getBirthDate())
                   && getGender() == human.getGender()
                   && getNationality().equals(human.getNationality());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            getFullName(), getBirthDate(), getGender(),
            getNationality(), getHeight(), getWeight()
        );
    }

    @Override
    public String toString() {
        StringBuilder humanInfoFormatString = new StringBuilder();
        humanInfoFormatString.
            append("full name: %s\n").
            append("date of birth: %s\n").
            append("sex: %s\n").
            append("nationality: %s\n").
            append("height: %s\n").
            append("weight: %s\n");

        return String.format(
            humanInfoFormatString.toString(),
            fullName, birthDate, gender, nationality, height, weight
        );
    }

    public int getAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public boolean isAdult() {
        final int ADULT_AGE = 18;
        return getAge() >= ADULT_AGE;
    }

    @Override
    public int compareTo(final Human otherPerson) {
        return Integer.compare(getAge(), otherPerson.getAge());
    }
}