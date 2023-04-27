package domain;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Equipo {

  private UUID id;
  private String nombre;
  private String ciudadOrigen;
  private Estadisticas estadisticas;

  public Equipo() {
    this.id = UUID.randomUUID();
    this.estadisticas = new Estadisticas();
  }

  @Override
  public String toString() {
    return "Equipo " + nombre + '\n' +
            "id: " + id + '\n' +
            "ciudad de origen: " + ciudadOrigen + '\n' +
            (estadisticas == null ? "estadisticas: " + estadisticas : "Este equipo aún no tiene estadisticas") + "\n";
  }
}
