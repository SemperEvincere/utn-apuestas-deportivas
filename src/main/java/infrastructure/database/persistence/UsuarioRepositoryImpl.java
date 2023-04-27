package infrastructure.database.persistence;

import application.repository.IUsuarioRepository;
import domain.Usuario;
import infrastructure.database.persistence.implement.RepositoryMySqlImpl;
import infrastructure.database.persistence.port.IPersistence;
import infrastructure.database.entities.UsuarioEntity;
import infrastructure.mapper.UsuarioMapper;
import java.util.Optional;
import java.util.UUID;

public class UsuarioRepositoryImpl implements IUsuarioRepository {

  private final IPersistence persistence;
  private final UsuarioMapper usuarioMapper;

  public UsuarioRepositoryImpl() {
//    this.persistence = new RepositoryFileImpl();
//    this.persistence = new RepositorySerializeImpl();
    this.persistence = new RepositoryMySqlImpl();
    usuarioMapper = new UsuarioMapper();
  }
  @Override
  public void save(Usuario usuario) {
    UsuarioEntity usuarioEntity = usuarioMapper.toEntity(usuario);
    persistence.save(usuarioEntity);
  }

  @Override
  public Optional<Usuario> findUsuarioByEmail(String email) {
    Optional<UsuarioEntity> usuarioEntity = persistence.findUsuarioByEmail(email);
    return usuarioEntity.map(usuarioMapper::toDomain);
  }

  @Override
  public void read() {
    persistence.readUsuarios();
  }

  @Override
  public Optional<Usuario> findUsuarioById(UUID idUsuario) {
    return persistence.findUsuarioById(idUsuario).map(usuarioMapper::toDomain);
  }
}
