package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Estadisticas {

  private int partidosGanados;
  private int partidosPerdidos;
  private int partidosEmpatados;
  private int golesAFavor;
  private int golesEnContra;
}
