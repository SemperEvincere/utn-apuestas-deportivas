package infrastructure.persistence;

import application.repository.IUsuarioRepository;
import domain.Usuario;
import infrastructure.entities.UsuarioEntity;
import infrastructure.mapper.UsuarioMapper;
import infrastructure.persistence.implement.RepositoryFileImpl;
import infrastructure.persistence.port.IPersistence;
import java.util.Optional;

public class UsuarioRepositoryImpl implements IUsuarioRepository {

  private final IPersistence persistence;
  private final UsuarioMapper usuarioMapper;

  public UsuarioRepositoryImpl() {
    this.persistence = new RepositoryFileImpl();
    usuarioMapper = new UsuarioMapper();
  }
  @Override
  public void save(Usuario usuario) {
    persistence.save(usuario);
  }

  @Override
  public Optional<Usuario> findUsuarioByEmail(String email) {
    Optional<UsuarioEntity> usuarioEntity = persistence.findUsuarioByEmail(email);
    return usuarioEntity.map(usuarioMapper::toDomain);
  }
}
