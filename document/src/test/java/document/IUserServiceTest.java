package document;

import javax.ejb.EJB;
import org.junit.Test;
import com.java.ejb.IUserService;
import com.java.model.User;


public class IUserServiceTest {
	@EJB(beanName="UserService")
	IUserService userService;
	
	@Test
	public void testUserInsert() {
		User user=new User();
		user.setEmail("cipriancus@gmail.com");
		user.setName("Ciprian");
		user.setPassword("ana");
		userService.addUser(user);
	}
}
