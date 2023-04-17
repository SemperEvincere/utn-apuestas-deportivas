package infrastructure.persistence.implement;

import infrastructure.entities.EquipoEntity;
import infrastructure.entities.PartidoEntity;
import infrastructure.entities.UsuarioEntity;
import infrastructure.persistence.port.IPersistence;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class RepositoryMySqlImpl implements IPersistence {

  @Override
  public void save(Object object) {
    
  }

  @Override
  public Optional<EquipoEntity> findEquipoByNombre(String nombre) {
    return Optional.empty();
  }

  @Override
  public Optional<UsuarioEntity> findUsuarioByEmail(String email) {
    return Optional.empty();
  }

  @Override
  public Optional<PartidoEntity> findPartidoById(UUID idPartido) {
    return Optional.empty();
  }

  @Override
  public void read() {

  }

  @Override
  public Set<EquipoEntity> getAllEquipos() {
    return null;
  }
}
