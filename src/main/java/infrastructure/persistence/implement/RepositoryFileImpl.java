package infrastructure.persistence.implement;

import infrastructure.csv.in.CsvLoadFileReader;
import infrastructure.csv.out.CsvSaveFileWriter;
import infrastructure.entities.EquipoEntity;
import infrastructure.entities.PartidoEntity;
import infrastructure.entities.UsuarioEntity;
import infrastructure.persistence.port.IPersistence;
import java.util.Optional;
import java.util.UUID;

public class RepositoryFileImpl implements IPersistence {

  private final CsvSaveFileWriter csvSaveFileWriter;
  private final CsvLoadFileReader csvLoadFileReader;

  public RepositoryFileImpl () {
    csvSaveFileWriter = new CsvSaveFileWriter();
    csvLoadFileReader = new CsvLoadFileReader();
  }


  @Override
  public void save(Object object) {
    csvSaveFileWriter.save(object);
  }

  @Override
  public Optional<EquipoEntity> findEquipoByNombre(String nombre) {
    return csvLoadFileReader.findEquipoByNombre(nombre);
  }

  @Override
  public Optional<UsuarioEntity> findUsuarioByEmail(String email) {
    return csvLoadFileReader.findUsuarioByEmail(email);
  }

  @Override
  public Optional<PartidoEntity> findPartidoById(UUID idPartido) {
    return csvLoadFileReader.findPartidoById(idPartido);
  }
}
