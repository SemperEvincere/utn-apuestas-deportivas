package infrastructure.database.entities;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ApuestaEntity {

  private UUID id;
  private UUID idUsuario;
  private UUID idPartido;
  private int golesLocalPronosticados;
  private int golesVisitantePronosticados;
  private double montoApostado;

  public ApuestaEntity() {
    if(this.id == null) {
      this.id = UUID.randomUUID();
    }
  }

  @Override
  public String toString() {
    return id + "," + idUsuario + "," + idPartido + "," + golesLocalPronosticados + "," + golesVisitantePronosticados + "," + montoApostado;
  }
}
