package infrastructure.database.persistence.port;

import domain.Partido;
import domain.Ronda;
import infrastructure.database.entities.*;

import java.time.LocalDate;
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

  Optional<UsuarioEntity> findByUsuarioEmail(String email);

  Optional<List<ApuestaEntity>> findApuestasByUsuarioId(UUID id);

  Optional<UsuarioEntity> findUsuarioById(UUID idUsuario);

  List<RondaEntity> getAllRondas();

  Partido findPartidoByFecha(LocalDate fechaPartido);
}
