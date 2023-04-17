package domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

public class UsuarioTest {

    @Test
    public void testEquals() {
      Usuario usuario1 = new Usuario();
      usuario1.setNick("usuario1");
      usuario1.setEmail("elsemper@gmail.com");
      usuario1.setPassword("1234");
      assertEquals(usuario1.getEmail(),"elsemper@gmail.com");
      assertEquals(usuario1.getPassword(),"1234");
    }

}