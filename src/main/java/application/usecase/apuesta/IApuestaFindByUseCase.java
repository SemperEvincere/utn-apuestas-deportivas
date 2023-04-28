package application.usecase.apuesta;

import domain.Apuesta;
import infrastructure.database.entities.ApuestaEntity;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IApuestaFindByUseCase {

  Optional<List<Apuesta>> findApuestasByUsuarioEmail(String email);

  ApuestaEntity findApuestaById(UUID idApuesta);
}
