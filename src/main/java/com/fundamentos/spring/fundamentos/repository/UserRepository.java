package com.fundamentos.spring.fundamentos.repository;

import com.fundamentos.spring.fundamentos.dto.UserDto;
import com.fundamentos.spring.fundamentos.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("Select u from User u WHERE u.email =?1")
    Optional<String> findByUserEmail(String email);

    @Query("Select u from User u WHERE u.name like ?1% ")
    List<User> findAndSort(String name, Sort sort);

    List<User> findByName(String name);

    Optional<User> findByEmailAndName(String email, String name);

    List<User> findByNameLike(String name);

    List<User> findByNameOrEmail(String name, String email);

    List<User> findByBirthDateBetween(LocalDate birthDateStart, LocalDate birthDateEnd);

    List<User> findByNameOrderByIdAsc(String name);

    List<User> findByEmail(String email, Sort sort);

    @Query("Select new com.fundamentos.spring.fundamentos.dto.UserDto(u.id,u.name,u.birthDate)"
    +"From User u Where u.birthDate=:parameterDate and u.email=:parameterEmail")
    Optional<UserDto> getAllByBirthDateAndEmail(@Param("parameterDate") LocalDate date,@Param("parameterEmail") String email);

}
