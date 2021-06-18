package com.ets.thcs.easythcsearch.model;

/**
 * Created by Manirul on 5/8/2021.
 */
public class StudentDetailsVo {

    private int id;
    private StudentAddressVo studentAddressVo;
    private StudentAcademicDetailsVo studentAcademicDetailsVo;
    private String subject;
    private String preferredMedium;
    private String preferredLocation;
    private String preferredOption;
    private String preferredMode;
    private String preferredTimeSlot;
    private String preferredFees;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StudentAddressVo getStudentAddressVo() {
        return studentAddressVo;
    }

    public void setStudentAddressVo(StudentAddressVo studentAddressVo) {
        this.studentAddressVo = studentAddressVo;
    }

    public StudentAcademicDetailsVo getStudentAcademicDetailsVo() {
        return studentAcademicDetailsVo;
    }

    public void setStudentAcademicDetailsVo(StudentAcademicDetailsVo studentAcademicDetailsVo) {
        this.studentAcademicDetailsVo = studentAcademicDetailsVo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPreferredMedium() {
        return preferredMedium;
    }

    public void setPreferredMedium(String preferredMedium) {
        this.preferredMedium = preferredMedium;
    }

    public String getPreferredLocation() {
        return preferredLocation;
    }

    public void setPreferredLocation(String preferredLocation) {
        this.preferredLocation = preferredLocation;
    }

    public String getPreferredOption() {
        return preferredOption;
    }

    public void setPreferredOption(String preferredOption) {
        this.preferredOption = preferredOption;
    }

    public String getPreferredMode() {
        return preferredMode;
    }

    public void setPreferredMode(String preferredMode) {
        this.preferredMode = preferredMode;
    }

    public String getPreferredTimeSlot() {
        return preferredTimeSlot;
    }

    public void setPreferredTimeSlot(String preferredTimeSlot) {
        this.preferredTimeSlot = preferredTimeSlot;
    }

    public String getPreferredFees() {
        return preferredFees;
    }

    public void setPreferredFees(String preferredFees) {
        this.preferredFees = preferredFees;
    }
}
