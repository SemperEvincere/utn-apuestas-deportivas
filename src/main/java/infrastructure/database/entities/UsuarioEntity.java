package infrastructure.database.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UsuarioEntity implements Serializable {

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

  public List<ApuestaEntity> getApuestas() {
    return apuestas;
  }

  public void setApuestas(List<ApuestaEntity> apuestas) {
    this.apuestas = apuestas;
  }
}
