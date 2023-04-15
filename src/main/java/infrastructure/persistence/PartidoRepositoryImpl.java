package infrastructure.persistence;

import application.repository.IPartidoRepository;
import domain.Partido;
import infrastructure.mapper.PartidoMapper;
import infrastructure.persistence.implement.RepositoryFileImpl;
import infrastructure.persistence.port.IPersistence;
import java.util.Optional;
import java.util.UUID;

public class PartidoRepositoryImpl implements IPartidoRepository {

  private final IPersistence persistence;
  private final PartidoMapper partidoMapper;

  public PartidoRepositoryImpl() {
    this.persistence = new RepositoryFileImpl();
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
