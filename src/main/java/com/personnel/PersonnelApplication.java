package com.personnel;

import com.personnel.model.DepartmentType;
import com.personnel.model.Employee;
import com.personnel.repository.EmployeeRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PersonnelApplication {

	@Autowired
	private EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(PersonnelApplication.class, args);
	}

	@Bean
	InitializingBean sendDatabase() {
		return () -> {
			employeeRepository.save(new Employee("Pam", "Beasly", "01.01.1980", DepartmentType.HR));
			employeeRepository.save(new Employee("Jim", "Halpert", "01.01.1980", DepartmentType.SALES));
			employeeRepository.save(new Employee("Michael", "Scott", "01.01.1980", DepartmentType.HR));
			employeeRepository.save(new Employee("Dwight", "Shroot", "01.01.1980", DepartmentType.SALES));
			employeeRepository.save(new Employee("Oscar", "Martinez", "01.01.1980", DepartmentType.IT));
		};
	}
}
