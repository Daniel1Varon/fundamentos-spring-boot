package com.fundamentos.spring.fundamentos;

import com.fundamentos.spring.fundamentos.bean.MyBean;
import com.fundamentos.spring.fundamentos.bean.MyBeanWithDependency;
import com.fundamentos.spring.fundamentos.bean.MyBeanWithProperties;
import com.fundamentos.spring.fundamentos.component.ComponentDependency;
import com.fundamentos.spring.fundamentos.entity.User;
import com.fundamentos.spring.fundamentos.pojo.UserPojo;
import com.fundamentos.spring.fundamentos.repository.UserRepository;
import com.fundamentos.spring.fundamentos.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);
	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;
	private MyBeanWithProperties myBeanWithProperties;
	private UserPojo userPojo;
	private UserRepository userRepository;
	private UserService userService;

	public FundamentosApplication(@Qualifier("componentImplement") ComponentDependency componentDependency, MyBean myBean
	, MyBeanWithDependency myBeanWithDependency,MyBeanWithProperties myBeanWithProperties,UserPojo userPojo,UserRepository userRepository,UserService userService) {
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency=myBeanWithDependency;
		this.myBeanWithProperties=myBeanWithProperties;
		this.userPojo=userPojo;
		this.userRepository=userRepository;
		this.userService=userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//clasesAnteriores();
		saveUsersInDataBase();
		getInformationJpqlFromUser();
		saveWithErrorTransactional();
	}

	private void saveWithErrorTransactional(){
		User test1 = new User("TestTransactional1", "TestTransactional1@domain.com", LocalDate.now());
		User test2 = new User("TestTransactional2", "TestTransactional2@domain.com", LocalDate.now());
		User test3 = new User("TestTransactional3", "TestTransactional1@domain.com", LocalDate.now());
		User test4 = new User("TestTransactional4", "TestTransactional4@domain.com", LocalDate.now());

		List<User> users = Arrays.asList(test1,test2,test3,test4);
		try {
			userService.saveTransactional(users);
		}catch (Exception e) {
			LOGGER.error("Esta es una excepcion dentro del metodo transaccional"+e.getMessage(), e);
		}
		userService.getUsers().stream().forEach(LOGGER::info);

	}

	private void getInformationJpqlFromUser(){
		LOGGER.info("User with method find by email: "+
				userRepository.findByUserEmail("john@domain.com"));

		userRepository.findAndSort("Karen", Sort.by("id").descending())
				.stream().forEach(user -> LOGGER.info("User with method sort"+user));

		userRepository.findByName("Daniela").stream().forEach(user -> LOGGER.info("User with query method: "+user));
		LOGGER.info("Usuario con query method: "+userRepository.findByEmailAndName("daniela@domain.com","Daniela"));
		userRepository.findByNameLike("Karen%").stream().forEach(user -> LOGGER.info("User with method like"+user));
		userRepository.findByNameOrEmail(null,"john@domain.com").stream().forEach(user -> LOGGER.info("User with method or: "+user));
		LOGGER.info("User with parameters: "+userRepository.getAllByBirthDateAndEmail(LocalDate.of(2021, 3, 13),"john@domain.com"));
	}

	private void saveUsersInDataBase(){
		User user1 = new User("John", "john@domain.com", LocalDate.of(2021, 3, 13));
		User user2 = new User("Marco", "marco@domain.com", LocalDate.of(2021, 12, 8));
		User user3 = new User("Daniela", "daniela@domain.com", LocalDate.of(2021, 9, 8));
		User user4 = new User("Daniela", "marisol@domain.com", LocalDate.of(2021, 6, 18));
		User user5 = new User("Karen", "karen@domain.com", LocalDate.of(2021, 1, 1));
		User user6 = new User("Karen2", "carlos@domain.com", LocalDate.of(2021, 7, 7));
		User user7 = new User("Karen3", "enrique@domain.com", LocalDate.of(2021, 11, 12));
		User user8 = new User("Luis", "luis@domain.com", LocalDate.of(2021, 2, 27));
		User user9 = new User("Paola", "paola@domain.com", LocalDate.of(2021, 4, 10));
		List<User> list = Arrays.asList(user1,user2,user3,user4,user5,user6,user7,user8,user9);
		list.stream().forEach(userRepository::save);
	}

	private void clasesAnteriores(){
		componentDependency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();
		System.out.println(myBeanWithProperties.funcion());
		System.out.println(userPojo.getEmail()+"-"+userPojo.getPassword());
		LOGGER.error("Esto es un error del aplicativo");
	}
}
