package infrastructure.persistence;

import application.repository.IApuestaRepository;
import domain.Apuesta;
import infrastructure.entities.ApuestaEntity;
import infrastructure.mapper.ApuestaMapper;
import infrastructure.persistence.implement.RepositoryFileImpl;
import infrastructure.persistence.port.IPersistence;

public class ApuestaRepositoryImpl implements IApuestaRepository {

  private final IPersistence persistence;
  private final ApuestaMapper apuestaMapper;

  public ApuestaRepositoryImpl() {
    this.persistence = new RepositoryFileImpl();
    this.apuestaMapper = new ApuestaMapper();
  }


  @Override
  public void save(Apuesta apuesta) {
    ApuestaEntity apuestaEntity = apuestaMapper.toEntity(apuesta);
    persistence.save(apuestaEntity);
  }
}
