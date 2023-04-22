package infrastructure.persistence.port;

import domain.Ronda;
import infrastructure.entities.EquipoEntity;
import infrastructure.entities.PartidoEntity;
import infrastructure.entities.UsuarioEntity;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IPersistence {

  void save(Object object);

  Optional<EquipoEntity> findEquipoByNombre(String nombre);

  Optional<UsuarioEntity> findUsuarioByEmail(String email);

  Optional<PartidoEntity> findPartidoById(UUID idPartido);

  void readUsuarios();

  List<EquipoEntity> getAllEquiposCsv();

  void saveAll(List<EquipoEntity> equipos);

  List<EquipoEntity> getAllEquipos();

  void saveRonda(Ronda ronda);

  Ronda findRondaByNumero(int numeroRonda);
}
