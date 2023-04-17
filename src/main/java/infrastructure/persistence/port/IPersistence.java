package infrastructure.persistence.port;

import domain.Equipo;
import infrastructure.entities.EquipoEntity;
import infrastructure.entities.PartidoEntity;
import infrastructure.entities.UsuarioEntity;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface IPersistence {

  void save(Object object);

  Optional<EquipoEntity> findEquipoByNombre(String nombre);

  Optional<UsuarioEntity> findUsuarioByEmail(String email);

  Optional<PartidoEntity> findPartidoById(UUID idPartido);

  void read();

  Set<EquipoEntity> getAllEquipos();
}
