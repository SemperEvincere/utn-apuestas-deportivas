package infrastructure.mapper;

import application.service.EquipoService;
import domain.Partido;
import infrastructure.database.entities.PartidoEntity;

public class PartidoMapper {

  private final EquipoMapper equipoMapper;
  private final EquipoService equipoService;

  public PartidoMapper() {
    this.equipoMapper = new EquipoMapper();
    this.equipoService = new EquipoService();
  }

  public PartidoEntity toEntity(Partido partido) {
    PartidoEntity partidoEntity = new PartidoEntity();
    partidoEntity.setFecha(partido.getFecha());
    partidoEntity.setUbicacion(partido.getUbicacion());
    partidoEntity.setNombreEquipoLocal(partido.getEquipoLocal().getNombre());
    partidoEntity.setNombreEquipoVisitante(partido.getEquipoVisitante().getNombre());
    partidoEntity.setGolesLocal(partido.getGolesLocal());
    partidoEntity.setGolesVisitante(partido.getGolesVisitante());
    return partidoEntity;
  }

  public Partido toDomain(PartidoEntity partidoEntity) {
    Partido partido = new Partido();
    partido.setId(partidoEntity.getId());
    partido.setEquipoLocal(equipoService.findEquipoByNombre(partidoEntity.getNombreEquipoLocal()).get());
    partido.setEquipoVisitante(equipoService.findEquipoByNombre(partidoEntity.getNombreEquipoVisitante()).get());
    partido.setGolesLocal(partidoEntity.getGolesLocal());
    partido.setGolesVisitante(partidoEntity.getGolesVisitante());
    partido.setFecha(partidoEntity.getFecha());
    partido.setUbicacion(partidoEntity.getUbicacion());

    return partido;
  }
}
