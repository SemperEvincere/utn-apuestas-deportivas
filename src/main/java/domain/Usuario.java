package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements Serializable {

  private UUID id;
  private String nick;
  private String email;
  private String password;
  private List<Apuesta> apuestas;

  public Usuario(String nick, String email, String password) {
    this.nick = nick;
    this.email = email;
    this.password = password;
    this.apuestas = new ArrayList<>();
  }
  @Override
  public String toString() {
    return "Datos del usuario: \n" +
            "id: " + id + '\n' +
            "nick: " + nick + '\n' +
            "email: " + email + '\n' +
            "password: " + password + '\n' +
            (apuestas.isEmpty() ? "Este usuario aún no ha realizado apuestas" : "Apuestas: " + apuestas + '\n');
  }

}
