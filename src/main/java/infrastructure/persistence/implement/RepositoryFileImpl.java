package infrastructure.persistence.implement;

import domain.Ronda;
import infrastructure.csv.in.CsvFileReader;
import infrastructure.csv.out.CsvFileWriter;
import infrastructure.entities.ApuestaEntity;
import infrastructure.entities.EquipoEntity;
import infrastructure.entities.PartidoEntity;
import infrastructure.entities.UsuarioEntity;
import infrastructure.persistence.port.IPersistence;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class RepositoryFileImpl implements IPersistence {

  private final CsvFileWriter csvFileWriter;
  private final CsvFileReader csvFileReader;

  public RepositoryFileImpl () {
    csvFileWriter = new CsvFileWriter();
    csvFileReader = new CsvFileReader();
  }


  @Override
  public void save(Object object) {
    csvFileWriter.save(object);
  }

  @Override
  public Optional<EquipoEntity> findEquipoByNombre(String nombre) {
    return csvFileReader.findEquipoByNombre(nombre);
  }

  @Override
  public Optional<UsuarioEntity> findUsuarioByEmail(String email) {
    return csvFileReader.findUsuarioByEmail(email);
  }

  @Override
  public Optional<PartidoEntity> findPartidoById(UUID idPartido) {
    return csvFileReader.findPartidoById(idPartido);
  }

  @Override
  public void readUsuarios() {
  }

  @Override
  public List<EquipoEntity> getAllEquiposCsv() {
    return csvFileReader.getAll();
  }


  @Override
  public void saveAll(List<EquipoEntity> equipos) {
    for (EquipoEntity equipo : equipos) {

      csvFileWriter.save(equipo);
    }
  }

  @Override
  public List<EquipoEntity> getAllEquipos() {
    return csvFileReader.getAll();
  }

  @Override
  public void saveRonda(Ronda ronda) {

  }

  @Override
  public Ronda findRondaByNumero(int numeroRonda) {
    return null;
  }

  @Override
  public Optional<UsuarioEntity> findByUsuarioEmail(String email) {
    return Optional.empty();
  }

  @Override
  public Optional<List<ApuestaEntity>> findApuestasByUsuarioId(UUID id) {
    return Optional.empty();
  }

  @Override
  public Optional<UsuarioEntity> findUsuarioById(UUID idUsuario) {
    return Optional.empty();
  }
}
