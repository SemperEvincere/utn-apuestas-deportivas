package infrastructure.persistence.port;

import infrastructure.entities.EquipoEntity;
import infrastructure.entities.PartidoEntity;
import infrastructure.entities.UsuarioEntity;
import java.util.Optional;
import java.util.UUID;

public interface IPersistence {

  void save(Object object);

  Optional<EquipoEntity> findEquipoByNombre(String nombre);

  Optional<UsuarioEntity> findUsuarioByEmail(String email);

  Optional<PartidoEntity> findPartidoById(UUID idPartido);
}
