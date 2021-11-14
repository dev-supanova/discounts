package xyz.geniuslaec.discounts;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import xyz.geniuslaec.discounts.controllers.UsersController;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class DiscountsApplicationTests {

	@Autowired
	UsersController usersController;

	@Test
	void contextLoads() {
		Assertions.assertThat(usersController).isNotNull();
	}

}
