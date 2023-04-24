package application.repository;

import domain.Apuesta;
import java.util.List;
import java.util.Optional;

public interface IApuestaRepository {

  void save(Apuesta apuesta);

  Optional<List<Apuesta>> findApuestasByUsuarioEmail(String email);
}
