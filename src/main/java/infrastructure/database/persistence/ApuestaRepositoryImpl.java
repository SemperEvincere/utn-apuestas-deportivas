package infrastructure.database.persistence;

import application.repository.IApuestaRepository;
import application.service.ApuestaService;
import domain.Apuesta;
import infrastructure.database.persistence.implement.RepositoryMySqlImpl;
import infrastructure.database.entities.ApuestaEntity;
import infrastructure.database.entities.UsuarioEntity;
import infrastructure.mapper.ApuestaMapper;
import infrastructure.database.persistence.port.IPersistence;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ApuestaRepositoryImpl implements IApuestaRepository {

  private final IPersistence persistence;
  private final ApuestaMapper apuestaMapper;

  public ApuestaRepositoryImpl() {
    this.persistence = new RepositoryMySqlImpl();
    this.apuestaMapper = new ApuestaMapper();
  }


  @Override
  public void save(Apuesta apuesta) {
    ApuestaEntity apuestaEntity = apuestaMapper.toEntity(apuesta);
    persistence.save(apuestaEntity);
  }

  @Override
  public Optional<List<Apuesta>> findApuestasByUsuarioEmail(String email) {
    Optional<UsuarioEntity> usuarioEntity = persistence.findByUsuarioEmail(email);
    if(usuarioEntity.isPresent()) {
      List<ApuestaEntity> apuestasEntities = persistence.findApuestasByUsuarioId(usuarioEntity.get().getId()).orElseThrow();
      List<Apuesta> apuestas = apuestaMapper.toDomain(apuestasEntities);
      return Optional.of(apuestas);
    }

    return Optional.empty();
  }

  @Override
  public ApuestaEntity findApuestaById(UUID idApuesta) {
    return persistence.findApuestaById(idApuesta);
  }
}
