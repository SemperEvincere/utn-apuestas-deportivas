package infrastructure.persistence.implement;

import domain.Usuario;
import infrastructure.entities.EquipoEntity;
import infrastructure.entities.PartidoEntity;
import infrastructure.entities.UsuarioEntity;
import infrastructure.persistence.port.IPersistence;
import infrastructure.serialize.in.SerializeLoader;
import infrastructure.serialize.out.SerializeWriter;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class RepositorySerializeImpl implements IPersistence {

  private SerializeWriter serializeWriter;
  private SerializeLoader serializeLoader;

  public RepositorySerializeImpl() {
    this.serializeWriter = new SerializeWriter();
    this.serializeLoader = new SerializeLoader();
  }
  @Override
  public void save(Object object) {
    serializeWriter.save(object);
  }

  @Override
  public void read() {
    serializeLoader.read();
  }

  @Override
  public Set<EquipoEntity> getAllEquipos() {
    return null;
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
}
