package infrastructure.entities;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RondaEntity {

  private UUID id;
  private int numeroRonda;
  private List<PartidoEntity> partidos;

  public RondaEntity() {
    this.id = UUID.randomUUID();
  }

}
