package infrastructure.persistence;

import application.repository.IEquipoRepository;
import domain.Equipo;
import infrastructure.entities.EquipoEntity;
import infrastructure.mapper.EquipoMapper;
import infrastructure.persistence.implement.RepositoryFileImpl;
import infrastructure.persistence.port.IPersistence;
import java.util.Optional;

public class EquipoRepositoryImpl implements IEquipoRepository {

  private final IPersistence persistence;
  final EquipoMapper equipoMapper;

  public EquipoRepositoryImpl() {
    this.persistence = new RepositoryFileImpl();
    equipoMapper = new EquipoMapper();
  }

  @Override
  public void save(Equipo equipo) {
    persistence.save(equipo);
  }

  @Override
  public Optional<Equipo> findEquipoByNombre(String nombre) {
    Optional<EquipoEntity> equipoEntityOptional = persistence.findEquipoByNombre(nombre);
    return equipoEntityOptional.map(equipoEntity -> equipoMapper.toDomain(equipoEntity));
  }
}
