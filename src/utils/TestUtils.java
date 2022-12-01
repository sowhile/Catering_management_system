package utils;

import org.junit.jupiter.api.Test;

/**
 * @author sowhile
 * <p>
 * 2022/12/1 0:35
 */
public class TestUtils {
    @Test
    public void passwdTest() {
        String password = "1";
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt(5));
        System.out.println(hashed);
        String candidate = "1";
        if (BCrypt.checkpw(candidate, hashed))
            System.out.println("It matches");
        else
            System.out.println("It does not match");
    }
}
