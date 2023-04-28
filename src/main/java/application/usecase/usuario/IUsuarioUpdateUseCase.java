package application.usecase.usuario;

import application.service.ApuestaService;
import domain.Apuesta;
import domain.Usuario;
import java.util.UUID;

public interface IUsuarioUpdateUseCase {

  void updateUser(Usuario usuario, UUID idApuesta, ApuestaService apuestaService);
}
