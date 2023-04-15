package infrastructure.entities;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UsuarioEntity {

  private UUID id;
  private String nick;
  private String email;
  private String password;
  private List<ApuestaEntity> apuestas;

  public UsuarioEntity() {
    this.id = UUID.randomUUID();
  }

  @Override
  public String toString() {
    return id + "," + nick + "," + email + "," + password + "," + apuestas.size();
  }
}
