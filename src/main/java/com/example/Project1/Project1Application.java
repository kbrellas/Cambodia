package com.example.Project1;

import com.example.Project1.models.*;
import com.example.Project1.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class Project1Application implements CommandLineRunner {

	@Autowired
	CompanyRepository companyRepository;
	@Autowired
	BusinessUnitRepository businessUnitRepository;
	@Autowired
	DepartmentRepository departmentRepository;
	@Autowired
	UnitRepository unitRepository;
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	TaskRepository taskRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder passwordEncoder;


	public static void main(String[] args) {
		SpringApplication.run(Project1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Company com1 = new Company("Unisystems");
		Company com2 = new Company("InfoQuest");

		companyRepository.save(com1);
		companyRepository.save(com2);

		BusinessUnit bus1= new BusinessUnit("Business Unit 1");
		BusinessUnit bus2= new BusinessUnit("Business Unit 2");

		BusinessUnit bus3= new BusinessUnit("Business Unit 3");
		BusinessUnit bus4= new BusinessUnit("Business Unit 4");

		bus1.setCompany(com1);
		bus2.setCompany(com1);

		bus3.setCompany(com2);
		bus4.setCompany(com2);

		businessUnitRepository.save(bus1);
		businessUnitRepository.save(bus2);
		businessUnitRepository.save(bus3);
		businessUnitRepository.save(bus4);

		Department hr1= new Department("HR1");
		Department hr2= new Department("HR2");
		Department hr3= new Department("HR3");
		Department hr4= new Department("HR4");
		Department rnd1= new Department("RnD1");
		Department rnd2= new Department("RnD2");
		Department rnd3= new Department("RnD3");
		Department rnd4= new Department("RnD4");


		hr1.setBusinessUnit(bus1);
		hr2.setBusinessUnit(bus2);
		hr3.setBusinessUnit(bus3);
		hr4.setBusinessUnit(bus4);

		rnd1.setBusinessUnit(bus1);
		rnd2.setBusinessUnit(bus2);
		rnd3.setBusinessUnit(bus3);
		rnd4.setBusinessUnit(bus4);



		departmentRepository.save(hr1);
		departmentRepository.save(hr2);
		departmentRepository.save(hr3);
		departmentRepository.save(hr4);
		departmentRepository.save(rnd1);
		departmentRepository.save(rnd2);
		departmentRepository.save(rnd3);
		departmentRepository.save(rnd4);


		Unit un1= new Unit("Unit 1");
		Unit un2= new Unit("Unit 2");
		Unit un3= new Unit("Unit 3");
		Unit un4= new Unit("Unit 4");
		Unit un5= new Unit("Unit 5");
		Unit un6= new Unit("Unit 6");
		Unit un7= new Unit("Unit 7");
		Unit un8= new Unit("Unit 8");
		Unit un9= new Unit("Unit 9");
		Unit un10= new Unit("Unit 10");
		Unit un11= new Unit("Unit 11");
		Unit un12= new Unit("Unit 12");
		Unit un13= new Unit("Unit 13");
		Unit un14= new Unit("Unit 14");
		Unit un15= new Unit("Unit 15");
		Unit un16= new Unit("Unit 16");

		un1.setDepartment(hr1);
		un2.setDepartment(hr1);
		un3.setDepartment(hr2);
		un4.setDepartment(hr2);
		un5.setDepartment(hr3);
		un6.setDepartment(hr3);
		un7.setDepartment(hr4);
		un8.setDepartment(hr4);
		un9.setDepartment(rnd1);
		un10.setDepartment(rnd1);
		un11.setDepartment(rnd2);
		un12.setDepartment(rnd2);
		un13.setDepartment(rnd3);
		un14.setDepartment(rnd3);
		un15.setDepartment(rnd4);
		un16.setDepartment(rnd4);

		unitRepository.save(un1);
		unitRepository.save(un2);
		unitRepository.save(un3);
		unitRepository.save(un4);
		unitRepository.save(un5);
		unitRepository.save(un6);
		unitRepository.save(un7);
		unitRepository.save(un8);
		unitRepository.save(un9);
		unitRepository.save(un10);
		unitRepository.save(un11);
		unitRepository.save(un12);
		unitRepository.save(un13);
		unitRepository.save(un14);
		unitRepository.save(un15);
		unitRepository.save(un16);

		Employee emp1= new Employee( 12345,"Brown", "James", "Main St. 134", "6965455545" , LocalDate.of(2018,5,2),LocalDate.of(2019,2,23),Status.INACTIVE ,ContractType.UNISYSTEMS,un3,"Department Manager");
		Employee emp2= new Employee(53532,"Jameson", "Rick", "Second St. 75", "6953532311" , LocalDate.of(2015,2,13),null,Status.ACTIVE ,ContractType.EXTERNAL,un7,"HR Manager");
		Employee emp3= new Employee(15463,"Stuart", "Martha", "Palomino St. 23", "6974243585" , LocalDate.of(2016,7,12),null,Status.ACTIVE ,ContractType.UNISYSTEMS,un9,"Mid Software Engineer");
		Employee emp4= new Employee(22341,"Dickinson", "Joy", "White St. 543", "6972234576" , LocalDate.of(2017,4,21),LocalDate.of(2019,2,1),Status.INACTIVE ,ContractType.EXTERNAL,un14,"Junior Software Engineer");
		Employee emp5= new Employee(14142,"Rayleigh", "Roger", "Blue Lagoon St. 127", "6972236587" , LocalDate.of(2013,6,15),null,Status.ACTIVE ,ContractType.UNISYSTEMS,un10,"Junior Software Developer");
        Employee emp6= new Employee(14144,"Monkey", "Luffy", "Blue Lagoon St. 122", "6972234447" , LocalDate.of(2013,6,15),null,Status.ACTIVE ,ContractType.UNISYSTEMS,un3,"Junior Software Developer");
        Employee emp7= new Employee(12323,"Roronoa", "Zoro", "Blue Lagoon St. 121", "6972235557" , LocalDate.of(2013,6,15),null,Status.ACTIVE ,ContractType.UNISYSTEMS,un3,"Senior Software Developer");
        Employee emp8= new Employee(98742,"HawkEye", "Mihawk", "Blue Lagoon St. 123", "6972666587" , LocalDate.of(2013,6,15),null,Status.ACTIVE ,ContractType.UNISYSTEMS,un3,"Master Software Developer");

        employeeRepository.save(emp1);
		employeeRepository.save(emp2);
		employeeRepository.save(emp3);
		employeeRepository.save(emp4);
		employeeRepository.save(emp5);
        employeeRepository.save(emp6);
        employeeRepository.save(emp7);
        employeeRepository.save(emp8);

		List<Employee> SomeEmployees = new ArrayList<>();
		SomeEmployees.add(emp1);
		List<Employee> SomeEmployees2 = new ArrayList<>();

		SomeEmployees2.add(emp2);

		List<Employee> SomeEmployees3 = new ArrayList<>();

		SomeEmployees3.add(emp3);
		List<Employee> SomeEmployees4 = new ArrayList<>();

		SomeEmployees4.add(emp4);
		List<Employee> SomeEmployees5 = new ArrayList<>();
		SomeEmployees5.add(emp4);
		SomeEmployees5.add(emp5);

		List<String> Updates = new ArrayList<>();
		Updates.add("update 1");
		Updates.add("update 2");
		Updates.add("update 3");
		Updates.add("update 4");
		Updates.add("update 5");
		Updates.add("update 6");
		Updates.add("update 7");
		Updates.add("update 8");
		Updates.add("update 9");
		Updates.add("update 10");

		Task task1 = new Task("Task1","Το πρώτο Task",4,5,1,TaskStatus.NEW,Updates,SomeEmployees);
		Task task2 = new Task("Task2","Το δεύτερο Task",4,5,1,TaskStatus.NEW,Updates,null);
		Task task3 = new Task("Task3","Το τρίτο Task",4,5,1,TaskStatus.NEW,Updates,SomeEmployees2);
		Task task4 = new Task("Task4","Το τέταρτο Task",4,5,1,TaskStatus.NEW,null,null);
		Task task5 = new Task("Task5","Το πέμπτο Task",4,5,1,TaskStatus.NEW,null,null);
		Task task6 = new Task("Task6","Το έκτο Task",4,5,1,TaskStatus.NEW,Updates,SomeEmployees3);
		Task task7 = new Task("Task7","Το έβδομο Task",4,5,1,TaskStatus.NEW,null,SomeEmployees4);
		Task task8 = new Task("Task8","Το ογδοο Task",4,5,1,TaskStatus.NEW,null,null);
		Task task9 = new Task("Task9","Το ένωατο Task",4,5,1,TaskStatus.NEW,null,SomeEmployees5);
		Task task10 = new Task("Task10","Το δέκατο Task",4,5,1,TaskStatus.NEW,null,null);

		taskRepository.save(task1);
		taskRepository.save(task2);
		taskRepository.save(task3);
		taskRepository.save(task4);
		taskRepository.save(task7);
		taskRepository.save(task6);
		taskRepository.save(task7);
		taskRepository.save(task8);
		taskRepository.save(task9);
		taskRepository.save(task10);


		CustomUser admin = new CustomUser("admin",passwordEncoder.encode("admin"),"admin","");
		CustomUser cm = new CustomUser("companyManager",passwordEncoder.encode("companyManager"),"companyManager","");
		CustomUser bm = new CustomUser("businessUnitManager",passwordEncoder.encode("businessUnitManager"),"businessUnitManager","");
		CustomUser dm = new CustomUser("departmentManager",passwordEncoder.encode("departmentManager"),"departmentManager","");
		CustomUser um = new CustomUser("unitManager",passwordEncoder.encode("unitManager"),"unitManager","");
		CustomUser em = new CustomUser("employee",passwordEncoder.encode("employee"),"employee","");

		List<CustomUser> users = Arrays.asList(admin,cm,dm,um,bm,em);


		userRepository.saveAll(users);


	}
}
