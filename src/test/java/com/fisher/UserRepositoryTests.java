package com.fisher;

import static org.assertj.core.api.Assertions.assertThat;

import com.fisher.domain.User;
import com.fisher.repository.UserRepository;
import com.fisher.domain.UserRole;
import com.fisher.repository.UserRoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.Date;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private TestEntityManager testEntityManager;


    @Test
    public void testCreateRole(){
        UserRole admin = new UserRole("ADMIN");
        UserRole user = new UserRole("USER");
        userRoleRepository.saveAll(List.of(admin,user));
        List<UserRole> listRoles = userRoleRepository.findAll();
        assertThat(listRoles.size()).isEqualTo(2);
    }

    @Test
    public void testCreateNewUser(){
        User user = new User();
        user.setEmail("arek@tlen.pl");
        user.setPassword("miau123");
        user.setFirstName("Michał");
        user.setLastName("Kowalski");
        user.setPhoneNumber("100100100");
        user.setRegistrationDate(new Date());

       User savedUser =  userRepository.save(user);
       User existsUser= testEntityManager.find(User.class,savedUser.getId());
       assertThat(existsUser.getEmail().equals(user.getEmail()));

    }

    @Test
    public void testAddRoleToNewUser(){
        User user = new User();
        user.setEmail("tomek@tlen.pl");
        user.setPassword("Piesek123");
        user.setFirstName("Tomek");
        user.setLastName("Merger");

        UserRole userRole = userRoleRepository.findByName("USER");
        user.addRole(userRole);
        User savedUser = userRepository.save(user);

        assertThat(savedUser.getRoles().size()).isEqualTo(2);
    }

    @Test
    public void addRolesToExistingUser(){
      User user =  userRepository.findById(1l).get();

        UserRole userRole = userRoleRepository.findByName("USER");
        user.addRole(userRole);

        UserRole roleAdmin =  userRoleRepository.findByName("ADMIN"); // lub new UserRole(1);
        user.addRole(roleAdmin);

        User savedUser = userRepository.save(user);

        assertThat(savedUser.getRoles().size()).isEqualTo(2);


    }

    @Test
    public void findUserByEmail(){
        String email = "mjakmario@tlen.pl";
        User user = userRepository.findByEmail(email);
        assertThat(user).isNotNull();

    }

    @Test
    public void activateUser(){
        String email = "marian@o2.pl";
        User user = userRepository.findByEmail(email);
        user.setEnabled(true);
        User savedUser = userRepository.save(user);
        assertThat(savedUser.isEnabled());

    }

    @Test
    public void  deleteUser(){
        Long id = 1l;
        userRepository.deleteById(id);
    }
}
