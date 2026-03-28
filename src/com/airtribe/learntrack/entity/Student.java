package com.airtribe.learntrack.entity;

public class Student extends Person {
    private String batch;
    private boolean active;

    public Student() {
        super();
        this.active = true;
    }

    public Student(int id, String firstName, String lastName, String email, String batch) {
        super(id, firstName, lastName, email);
        this.batch = batch;
        this.active = true;
    }
    
    // Constructor overloading example (without email)
    public Student(int id, String firstName, String lastName, String batch) {
        super(id, firstName, lastName, "N/A");
        this.batch = batch;
        this.active = true;
    }

    public String getBatch() { return batch; }
    public void setBatch(String batch) { this.batch = batch; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    @Override
    public String getDisplayName() {
        return super.getDisplayName() + " (Student - " + batch + ")";
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Name: %s | Email: %s | Batch: %s | Active: %b", 
            getId(), super.getDisplayName(), getEmail(), getBatch(), isActive());
    }
}
