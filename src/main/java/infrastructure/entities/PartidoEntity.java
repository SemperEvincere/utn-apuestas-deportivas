package infrastructure.entities;

import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class PartidoEntity {

  private UUID id;
  private LocalDate fecha;
  private String ubicacion;
  private String nombreEquipoLocal;
  private String nombreEquipoVisitante;
  private int golesLocal;
  private int golesVisitante;

  public PartidoEntity() {
    this.id = UUID.randomUUID();
  }

  @Override
  public String toString() {
    return id
        + "," + fecha
        + "," + ubicacion
        + "," + nombreEquipoLocal
        + "," + nombreEquipoVisitante
        + "," + golesLocal
        + "," + golesVisitante;
  }
}
