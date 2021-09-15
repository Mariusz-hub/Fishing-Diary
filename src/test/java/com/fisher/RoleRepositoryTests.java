package com.fisher;
import static org.assertj.core.api.Assertions.assertThat;
import com.fisher.domain.UserRole;
import com.fisher.repository.UserRoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class RoleRepositoryTests {
    @Autowired
    UserRoleRepository userRoleRepository;

    @Test
    public void testCreateRoles(){
        UserRole admin = new UserRole("ADMIN");
        UserRole user = new UserRole("USER");
        userRoleRepository.saveAll(List.of(admin,user));
        List<UserRole> listRoles = userRoleRepository.findAll();
        assertThat(listRoles.size()).isEqualTo(2);
    }

}
