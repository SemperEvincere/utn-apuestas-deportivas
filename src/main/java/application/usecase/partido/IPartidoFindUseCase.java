package application.usecase.partido;

import domain.Partido;
import java.util.Optional;
import java.util.UUID;

public interface IPartidoFindUseCase {

  Optional<Partido> findPartidoById(UUID idPartido);
}
