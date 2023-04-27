package infrastructure.database.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResultadoEntity {

  private PartidoEntity partido;
  private EquipoEntity equipo;
  private int goles;

}
