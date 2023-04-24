package application.usecase.apuesta;

import domain.Apuesta;
import java.util.List;
import java.util.Optional;

public interface IApuestaFindByUsuarioEmail {

  Optional<List<Apuesta>> findApuestasByUsuarioEmail(String email);

}
