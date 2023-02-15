package org.example.human;

public enum HumanGender {
    MALE("male"),
    FEMALE("female");

    private final String gender;

    HumanGender(final String gender) {
        this.gender = gender;
    }

    public static HumanGender getGenderByValue(String gender) {
        final String lowCaseGenderStr = gender.toLowerCase();
        if (MALE.gender.equals(lowCaseGenderStr)) {
            return MALE;
        } else if (FEMALE.gender.equals(lowCaseGenderStr)) {
            return FEMALE;
        } else throw new IllegalArgumentException("Not a gender: " + gender);
    }

    @Override
    public String toString() {
        return gender;
    }
}
