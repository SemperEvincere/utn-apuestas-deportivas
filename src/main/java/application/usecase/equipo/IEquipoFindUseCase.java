package application.usecase.equipo;

import domain.Equipo;
import java.util.Optional;

public interface IEquipoFindUseCase {

  Optional<Equipo> findEquipoByNombre(String nombre);

}
