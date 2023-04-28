package application.repository;

import domain.Apuesta;
import infrastructure.database.entities.ApuestaEntity;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IApuestaRepository {

  void save(Apuesta apuesta);

  Optional<List<Apuesta>> findApuestasByUsuarioEmail(String email);

  ApuestaEntity findApuestaById(UUID idApuesta);
}
