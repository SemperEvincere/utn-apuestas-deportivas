package application.usecase.usuario;

import domain.Usuario;
import java.util.Optional;
import java.util.UUID;

public interface IUsuarioFindUseCase {

  Optional<Usuario> findUsuarioByEmail(String email);

  Optional<Usuario> findUsuarioById(UUID idUsuario);
}
