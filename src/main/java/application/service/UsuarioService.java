package application.service;

import application.repository.IUsuarioRepository;
import application.usecase.usuario.IUsuarioCreateUseCase;
import application.usecase.usuario.IUsuarioFindUseCase;
import application.usecase.usuario.IUsuarioUpdateUseCase;
import domain.Apuesta;
import domain.Usuario;
import infrastructure.database.persistence.UsuarioRepositoryImpl;
import java.util.Optional;
import java.util.UUID;

public class UsuarioService implements IUsuarioCreateUseCase, IUsuarioFindUseCase, IUsuarioUpdateUseCase {

  private IUsuarioRepository usuarioRepository;

  public UsuarioService() {
    this.usuarioRepository = new UsuarioRepositoryImpl();
  }

  @Override
  public void create(Usuario usuario) {
    if(usuario.getNick().isEmpty() || usuario.getEmail().isEmpty() || usuario.getPassword().isEmpty()) {
      throw new IllegalArgumentException("Todos los campos son obligatorios");
    }
    if(usuario.getNick().length() < 5) {
      throw new IllegalArgumentException("El nick debe tener al menos 5 caracteres");
    }
    if(usuario.getPassword().length() < 6) {
      throw new IllegalArgumentException("La contraseña debe tener al menos 6 caracteres");
    }
    if(existeUsuario(usuario)) {
      throw new IllegalArgumentException("El usuario ya existe");
    }
    usuarioRepository.save(usuario);
  }

  private boolean existeUsuario(Usuario usuario) {
    return this.findUsuarioByEmail(usuario.getEmail()).isPresent();
  }


  @Override
  public Optional<Usuario> findUsuarioByEmail(String email) {
      return usuarioRepository.findUsuarioByEmail(email);
  }

  @Override
  public Optional<Usuario> findUsuarioById(UUID idUsuario) {
    return usuarioRepository.findUsuarioById(idUsuario);
  }

  public void read() {
    usuarioRepository.read();
  }

  @Override
  public void updateUser(Usuario usuario, UUID idApuesta, ApuestaService apuestaService) {
    usuarioRepository.updateUser(usuario, idApuesta, apuestaService);
  }
}
