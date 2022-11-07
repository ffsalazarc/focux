package biz.intelix.focuX;

import biz.intelix.focuX.followup.model.Users;
import biz.intelix.focuX.followup.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest
class FocuXApplicationTests {

	@Autowired
	private UserRepository userRepository;

	/*@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;*/

	@Test
	void CrearUsuarios() {

		/*Users user = new Users();
		user.setUsername("admin");
		user.setPassword(bCryptPasswordEncoder.encode("123"));
		user.setIsActive(1);

		Users ureturn = userRepository.save(user);

		assertTrue(ureturn.getPassword().equalsIgnoreCase(user.getPassword()));*/
	}

}
