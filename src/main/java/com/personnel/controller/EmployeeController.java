package com.personnel.controller;

import com.personnel.model.DepartmentType;
import com.personnel.model.Employee;
import com.personnel.repository.EmployeeRepository;
import com.personnel.request.EmployeePatchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @PostMapping("")
    public ResponseEntity<Employee> create(@Valid @RequestBody Employee employee) {

        return ResponseEntity.ok(employeeRepository.save(employee));
    }

    @GetMapping("")
    public ResponseEntity<Iterable<Employee>> list() {

        return ResponseEntity.ok(employeeRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Employee>> view(@PathVariable Long id) {

        return ResponseEntity.ok(employeeRepository.findById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable Long id, @Valid @RequestBody EmployeePatchRequest patchRequest) {

        try {

            Employee employee = employeeRepository.findById(id).orElseThrow(EntityNotFoundException::new);
            Employee employeUpdated = update(patchRequest, employee);

            return ResponseEntity.ok(employeUpdated);
        } catch (EntityNotFoundException e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> delete(@PathVariable Long id) {

        employeeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private Employee update(EmployeePatchRequest patchRequest, Employee employee) {

        patchRequest.lastName.ifPresent(employee::setLastName);
        patchRequest.firstName.ifPresent(employee::setFirstName);
        patchRequest.birthday.ifPresent(employee::setBirthday);
        patchRequest.department.ifPresent(s -> employee.setDepartment(DepartmentType.getFromValue(s)));

        employeeRepository.save(employee);
        return employee;
    }
}
