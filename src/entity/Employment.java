package entity;

import datastore.EmploymentLevel;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Employment implements Comparable<Employment>{

    private Company employer;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal annualSalary;
    private EmploymentLevel level;
    private String title;

    public Employment(Company employer, LocalDate start, BigDecimal income) {
        this.employer = employer;
        startDate = start;
        annualSalary = income;
    }
    public Employment(Company employer, LocalDate start, LocalDate end, BigDecimal income) {
        this.employer = employer;
        startDate = start;
        annualSalary = income;
        endDate  = end;
    }

    public Company getEmployer() {
        return employer;
    }

    public void setEmployer(Company employer) {
        this.employer = employer;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getSalary() {
        return annualSalary;
    }

    public void setSalary(BigDecimal salary) {
        this.annualSalary = salary;
    }

    public EmploymentLevel getLevel() {
        return level;
    }

    public void setLevel(EmploymentLevel level) {
        this.level = level;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getAnnualSalary() {
        return annualSalary;
    }

    public void setAnnualSalary(BigDecimal annualSalary) {
        this.annualSalary = annualSalary;
    }

    @Override
    public int compareTo(Employment employment) {
        return getStartDate().compareTo(employment.getStartDate());
    }
}
