package com.webapp.craictivity;

import com.webapp.craictivity.entity.User;
import com.webapp.craictivity.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveUserTest(){
        User user = User.builder()
                .username("florent")
                .password("foreti12")
                .role("USER")
                .build();

        userRepository.save(user);

        Assertions.assertThat(user.getId()).isGreaterThan(0);
    }

}
