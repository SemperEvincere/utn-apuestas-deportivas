package infrastructure.mapper;

import domain.Equipo;
import domain.Estadisticas;
import infrastructure.database.entities.EquipoEntity;
import java.util.ArrayList;

public class EquipoMapper {

  public EquipoEntity toEntity(Equipo equipo) {
    EquipoEntity equipoEntity = new EquipoEntity();

    equipoEntity.setNombre(equipo.getNombre());
    equipoEntity.setCiudadOrigen(equipo.getCiudadOrigen());
    equipoEntity.setPartidosLocal(new ArrayList<>());
    equipoEntity.setPartidosVisitante(new ArrayList<>());
    return equipoEntity;
  }

  public Equipo toDomain(EquipoEntity equipoEntity) {
    Equipo equipo = new Equipo();
    equipo.setId(equipoEntity.getId());
    equipo.setNombre(equipoEntity.getNombre());
    equipo.setCiudadOrigen(equipoEntity.getCiudadOrigen());
    equipo.setEstadisticas(new Estadisticas());
    return equipo;
  }
}
