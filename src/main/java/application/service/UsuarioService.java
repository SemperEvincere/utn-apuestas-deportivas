package application.service;

import application.repository.IUsuarioRepository;
import application.usecase.usuario.IUsuarioCreateUseCase;
import application.usecase.usuario.IUsuarioFindUseCase;
import domain.Usuario;
import infrastructure.persistence.UsuarioRepositoryImpl;
import java.util.Optional;

public class UsuarioService implements IUsuarioCreateUseCase, IUsuarioFindUseCase {

  private final IUsuarioRepository usuarioRepository;

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
}
