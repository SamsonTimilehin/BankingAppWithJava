package entity;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private long bvn;
    private String email;
    private String firstName;
    private String surname;
    private String password;
    private String phone;
    private List<Account> account = new ArrayList<>();

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

    @Override
    public String toString() {
        return String.format("Customer%nBvn: %d%nemail: %s%nfirstName: %s%nsurname: %s%npassword: %s%nphone: %s%naccount%s%n", bvn,email,firstName,surname,password,phone,account);

    }
}
