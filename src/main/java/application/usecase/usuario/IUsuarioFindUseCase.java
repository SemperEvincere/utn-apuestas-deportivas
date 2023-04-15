package application.usecase.usuario;

import domain.Usuario;
import java.util.Optional;

public interface IUsuarioFindUseCase {

  Optional<Usuario> findUsuarioByEmail(String email);

}
