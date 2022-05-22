package com.personnel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.personnel.repository.EmployeeRepository;
import com.personnel.validation.EnumValidator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import javax.persistence.*;

@Entity
public class Employee {

    @Autowired
    static EmployeeRepository employeeRepository;

    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @NotNull
    @Size(min = 1,max = 40)
    @Column(nullable = false)
    private String firstName;

    @NotNull
    @Size(min = 1,max = 40)
    @Column(nullable = false)
    private String lastName;

    @NotNull
    @Pattern(
        regexp = "^(3[01]|[12][0-9]|0[1-9]).(1[0-2]|0[1-9]).[0-9]{4}$",
        message = "Birthday Format must be 'dd.mm.yyyy'"
    )
    @Column(nullable = false)
    private String birthday;

    @NotNull
    @Column(nullable = false)
    @EnumValidator(enumClass = DepartmentType.class)
    private String department;

    public Employee(String firstName, String lastName, String birthday, DepartmentType department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.department = department.getValue();
    }

    public Employee() {}


    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentType department) {
        this.department = department.getValue();
    }
}
