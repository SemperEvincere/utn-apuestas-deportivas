package application.repository;

import domain.Usuario;
import java.util.Optional;

public interface IUsuarioRepository {

  void save(Usuario usuario);

  Optional<Usuario> findUsuarioByEmail(String email);
}
