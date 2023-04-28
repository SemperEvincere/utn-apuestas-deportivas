package infrastructure.database.persistence;

import application.repository.IUsuarioRepository;
import application.service.ApuestaService;
import application.service.UsuarioService;
import application.usecase.apuesta.IApuestaFindByUseCase;
import domain.Usuario;
import infrastructure.database.entities.ApuestaEntity;
import infrastructure.database.persistence.implement.RepositoryMySqlImpl;
import infrastructure.database.persistence.port.IPersistence;
import infrastructure.database.entities.UsuarioEntity;
import infrastructure.mapper.UsuarioMapper;
import java.util.Optional;
import java.util.UUID;

public class UsuarioRepositoryImpl implements IUsuarioRepository {

  private IPersistence persistence;
  private UsuarioMapper usuarioMapper;

  public UsuarioRepositoryImpl() {
//    this.persistence = new RepositoryFileImpl();
//    this.persistence = new RepositorySerializeImpl();
    this.persistence = new RepositoryMySqlImpl();
    this.usuarioMapper = new UsuarioMapper();
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

  @Override
  public void updateUser(Usuario usuario, UUID idApuesta, ApuestaService apuestaService) {
    UsuarioEntity usuarioEntity = usuarioMapper.toEntity(usuario);
    ApuestaEntity apuestaEntity = apuestaService.findApuestaById(idApuesta);
    usuarioEntity.getApuestas().add(apuestaEntity);
    persistence.updateUser(usuarioEntity);
  }
}
