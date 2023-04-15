package application.repository;

import domain.Equipo;
import java.util.Optional;

public interface IEquipoRepository {

  void save(Equipo equipo);

  Optional<Equipo> findEquipoByNombre(String nombre);
}
