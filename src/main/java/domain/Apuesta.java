package domain;

import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Apuesta {

  private UUID id;
  private Usuario usuario;
  private Partido partido;
  private int golesLocalPronosticados;
  private int golesVisitantePronosticados;
  private double montoApostado;

  public Apuesta(Usuario usuario, Partido partido, int golesLocalPronosticados, int golesVisitantePronosticados, double montoApostado) {
    this.id = UUID.randomUUID(); // todo: ver si va aca o en la entidad
    this.usuario = usuario;
    this.partido = partido;
    this.golesLocalPronosticados = golesLocalPronosticados;
    this.golesVisitantePronosticados = golesVisitantePronosticados;
    this.montoApostado = montoApostado;
  }
}
