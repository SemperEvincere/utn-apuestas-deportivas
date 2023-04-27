package infrastructure.database.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EstadisticasEntity {

  private EquipoEntity equipo;
  private int partidosJugados;
  private int partidosGanados;
  private int partidosEmpatados;
  private int partidosPerdidos;
  private int golesFavor;
  private int golesContra;

}
