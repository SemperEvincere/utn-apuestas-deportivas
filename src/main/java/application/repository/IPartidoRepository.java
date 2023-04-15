package application.repository;

import domain.Partido;
import java.util.Optional;
import java.util.UUID;

public interface IPartidoRepository {

  void save(Partido partido);

  Optional<Partido> findById(UUID idPartido);
}
