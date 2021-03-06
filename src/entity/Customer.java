package entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class Customer {
    private long bvn;
    private String email;
    private String firstName;
    private String surname;
    private String password;
    private String phone;
    private LocalDate dateOfBirth;
    private LocalDateTime relationshipStartDate;
    private List<Account> account = new ArrayList<>();
    private SortedSet<Employment> employmentHistory = new TreeSet<>();

    public Customer() {
    }

    public long getBvn() {
        return bvn;
    }

    public void setBvn(long bvn) {
        this.bvn = bvn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Account> getAccount() {
        return account;
    }

    public void setAccount(List<Account> account) {
        this.account = account;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDateTime getRelationshipStartDate() {
        return relationshipStartDate;
    }

    public SortedSet<Employment> getEmploymentHistory() {
        return employmentHistory;
    }

    public void setEmploymentHistory(SortedSet<Employment> employmentHistory) {
        this.employmentHistory = employmentHistory;
    }

    public void setRelationshipStartDate(LocalDateTime relationshipStartDate) {
        this.relationshipStartDate = relationshipStartDate;
    }

    @Override
    public String toString() {
        return String.format("Customer%nBvn: %d%nemail: %s%nfirstName: %s%nsurname: %s%npassword: %s%nphone: %s%naccount%s%n", bvn,email,firstName,surname,password,phone,account);

    }
}
