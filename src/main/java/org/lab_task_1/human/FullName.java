package org.lab_task_1.human;

import java.util.Objects;

public class FullName {
    private final String firstName;
    private final String middleName;
    private final String surname;

    private enum FullNameFormats {
        SURNAME_FIRSTNAME_MIDDLENAME("SFM"),
        FIRSTNAME_MIDDLENAME_SURNAME("FMS");

        private final String fullNameFormat;

        FullNameFormats(final String fullNameFormat) {
            this.fullNameFormat = fullNameFormat;
        }
    }

    public FullName(
        final String firstName,
        final String middleName,
        final String surname
    ) {
        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException(
                "null or empty string passed into " +
                    "FullName's constructor as first name"
            );
        }
        if (middleName == null) {
            throw new IllegalArgumentException(
                "null passed into FullName's constructor as middle name"
            );
        }
        if (surname == null || surname.isEmpty()) {
            throw new IllegalArgumentException(
                "null or empty string passed into " +
                    "FullName's constructor as surname"
            );
        }

        this.firstName  = firstName;
        this.middleName = middleName;
        this.surname    = surname;
    }

    public FullName(final FullName anotherFullName) {
        if (anotherFullName == null) {
            throw new IllegalArgumentException(
                "null-ref passed into FullName's copying constructor"
            );
        }
        firstName  = anotherFullName.getFirstName();
        middleName = anotherFullName.getMiddleName();
        surname    = anotherFullName.getSurname();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getSurname() {
        return surname;
    }

    public String getFullName(final FullNameFormats format) {
        StringBuffer fullName = new StringBuffer();
        switch (format) {
            case SURNAME_FIRSTNAME_MIDDLENAME:
                fullName.append(surname).
                    append(" ").
                    append(middleName).
                    append(" ").
                    append(firstName);
                break;
            case FIRSTNAME_MIDDLENAME_SURNAME:
                fullName.append(firstName).
                    append(" ").
                    append(middleName).
                    append(" ").
                    append(surname);
                break;
        }

        return fullName.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FullName fullName)) {
            return false;
        }

        return getFirstName().equals(fullName.getFirstName())
                   && getMiddleName().equals(fullName.getMiddleName())
                   && getSurname().equals(fullName.getSurname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            getFirstName(), getMiddleName(), getSurname()
        );
    }

    @Override
    public String toString() {
        return getFullName(FullNameFormats.FIRSTNAME_MIDDLENAME_SURNAME);
    }
}
