package domain;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Ronda {

  private UUID id;
  private int numero;
  private List<Partido> partidos;


  @Override
  public String toString() {
    return "Ronda{" + "partidos=" + partidos.toString() + ", numero=" + numero + '}';
  }
}
