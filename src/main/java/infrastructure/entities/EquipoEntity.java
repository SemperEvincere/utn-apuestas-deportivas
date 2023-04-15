package infrastructure.entities;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class EquipoEntity {

  private UUID id;
  private String nombre;
  private String ciudadOrigen;
  private List<PartidoEntity> partidosLocal;
  private List<PartidoEntity> partidosVisitante;

  public EquipoEntity() {
  }
  public EquipoEntity(UUID id, String nombre, String ciudadOrigen) {
    this.id = id;
    this.nombre = nombre;
    this.ciudadOrigen = ciudadOrigen;
  }

  @Override
  public String toString() {
    return id + "," + nombre + "," + ciudadOrigen + "," + partidosLocal.size() + "," + partidosVisitante.size();
  }
}
