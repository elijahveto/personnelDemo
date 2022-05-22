package com.personnel.request;

import com.personnel.model.DepartmentType;
import com.personnel.validation.EnumValidator;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Optional;

public class EmployeePatchRequest {

    public Optional<
            @Size(max = 40)
            String
            > firstName;

    public Optional<
            @Size(max = 40)
            String
            > lastName;

    public Optional<
            @Pattern(
            regexp = "^(3[01]|[12][0-9]|0[1-9]).(1[0-2]|0[1-9]).[0-9]{4}$",
            message = "Birthday Format must be 'dd.mm.yyyy'")
            String
            > birthday;

    public Optional<
            @EnumValidator(enumClass = DepartmentType.class)
            String
            > department;

    public EmployeePatchRequest(String firstName, String lastName, String birthday, String department) {
        this.firstName = Optional.ofNullable(firstName);
        this.lastName = Optional.ofNullable(lastName);
        this.birthday = Optional.ofNullable(birthday);
        this.department = Optional.ofNullable(department);
    }
}
