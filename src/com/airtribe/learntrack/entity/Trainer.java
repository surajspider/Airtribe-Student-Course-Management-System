package com.airtribe.learntrack.entity;

public class Trainer extends Person {
    private String specialization;

    // Default constructor using super()
    public Trainer() {
        super();
    }

    // Parameterized constructor using super(id, firstName, lastName, email)
    public Trainer(int id, String firstName, String lastName, String email, String specialization) {
        super(id, firstName, lastName, email);
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    // Method overriding to show specialized behavior
    @Override
    public String getDisplayName() {
        return "Trainer " + super.getDisplayName() + " - Expert: " + specialization;
    }
}
