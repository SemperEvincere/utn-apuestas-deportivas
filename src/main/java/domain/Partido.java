package domain;

import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Partido {

  private UUID id;
  private Equipo equipoLocal;
  private Equipo equipoVisitante;
  private int golesLocal;
  private int golesVisitante;
  private LocalDate fecha;
  private String ubicacion;


  public Partido(Equipo equipoUno, Equipo equipoDos, LocalDate fecha, String ubicacion) {
    this.ubicacion = ubicacion;
    this.id = UUID.randomUUID(); // todo: ver si va aca o en la entidad
    this.golesLocal = 0;
    this.golesVisitante = 0;
    this.fecha = fecha;
    this.estableceEquipoLocal(equipoUno, equipoDos, ubicacion);

  }

  private void estableceEquipoLocal(Equipo equipoUno,
      Equipo equipoDos,
      String ubicacion) {
    if (ubicacion.equals(equipoUno.getCiudadOrigen())) {
      this.equipoLocal = equipoUno;
      this.equipoVisitante = equipoDos;
    } else if(ubicacion.equals(equipoDos.getCiudadOrigen())) {
      this.equipoLocal = equipoDos;
      this.equipoVisitante = equipoUno;
    } else {
      throw new RuntimeException("No se puede establecer el equipo local");
    }
  }

}
