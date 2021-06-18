package com.ets.thcs.easythcsearch.model;

/**
 * Created by Manirul on 5/8/2021.
 */
public class StudentAcademicDetailsVo {

    private int id;
    private String academicDetailsType;
    private String category;
    private String institutionType;
    private String boardOrUniversity;
    private String institutionName;
    private String standard;
    private String course;
    private String year;
    private String semester;
    private String department;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAcademicDetailsType() {
        return academicDetailsType;
    }

    public void setAcademicDetailsType(String academicDetailsType) {
        this.academicDetailsType = academicDetailsType;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getInstitutionType() {
        return institutionType;
    }

    public void setInstitutionType(String institutionType) {
        this.institutionType = institutionType;
    }

    public String getBoardOrUniversity() {
        return boardOrUniversity;
    }

    public void setBoardOrUniversity(String boardOrUniversity) {
        this.boardOrUniversity = boardOrUniversity;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
