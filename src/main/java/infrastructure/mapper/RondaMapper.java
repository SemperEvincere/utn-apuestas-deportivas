package infrastructure.mapper;

import domain.Ronda;
import infrastructure.database.entities.PartidoEntity;
import infrastructure.database.entities.RondaEntity;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class RondaMapper {

  public RondaEntity toEntity(Ronda ronda) {
    RondaEntity rondaEntity = new RondaEntity();
    rondaEntity.setNumeroRonda(ronda.getNumero());
    rondaEntity.setPartidos(ronda.getPartidos().stream().map(partido -> {
      PartidoMapper partidoMapper = new PartidoMapper();
      return partidoMapper.toEntity(partido);
    }).collect(Collectors.toList()));
    return rondaEntity;
  }

  public Ronda toDomain(UUID uuid,
      String nombreRonda,
      Set<PartidoEntity> partidos) {
    Ronda ronda = new Ronda();
    ronda.setId(uuid);
    ronda.setNumero(Integer.parseInt(nombreRonda));
    ronda.setPartidos(partidos.stream().map(partido -> {
      PartidoMapper partidoMapper = new PartidoMapper();
      return partidoMapper.toDomain(partido);
    }).collect(Collectors.toList()));
    return ronda;

  }
}
