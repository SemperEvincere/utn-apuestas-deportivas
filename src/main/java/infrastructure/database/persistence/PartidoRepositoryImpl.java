package infrastructure.database.persistence;

import application.repository.IPartidoRepository;
import domain.Partido;
import infrastructure.database.persistence.implement.RepositoryMySqlImpl;
import infrastructure.mapper.PartidoMapper;
import infrastructure.database.persistence.port.IPersistence;
import java.util.Optional;
import java.util.UUID;

public class PartidoRepositoryImpl implements IPartidoRepository {

  private final IPersistence persistence;
  private final PartidoMapper partidoMapper;

  public PartidoRepositoryImpl() {
    this.persistence = new RepositoryMySqlImpl();
    this.partidoMapper = new PartidoMapper();
  }

  @Override
  public void save(Partido partido) {
    persistence.save(partidoMapper.toEntity(partido));
  }

  @Override
  public Optional<Partido> findById(UUID idPartido) {
    return persistence.findPartidoById(idPartido).map(partidoMapper::toDomain);
  }
}
