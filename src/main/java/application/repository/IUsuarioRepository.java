package application.repository;

import domain.Usuario;
import java.util.Optional;
import java.util.UUID;

public interface IUsuarioRepository {

  void save(Usuario usuario);

  Optional<Usuario> findUsuarioByEmail(String email);

  void read();

  Optional<Usuario> findUsuarioById(UUID idUsuario);

  void updateUser(Usuario usuario);
}
