package infrastructure.persistence;

import application.repository.IEquipoRepository;
import domain.Equipo;
import infrastructure.csv.in.CsvFileReader;
import infrastructure.csv.out.CsvFileWriter;
import infrastructure.entities.EquipoEntity;
import infrastructure.mapper.EquipoMapper;
import infrastructure.persistence.implement.RepositoryFileImpl;
import infrastructure.persistence.port.IPersistence;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

public class EquipoRepositoryImpl implements IEquipoRepository {

  private final IPersistence persistence;
  private final EquipoMapper equipoMapper;
  private final CsvFileWriter csvFileWriter;
  private final CsvFileReader csvFileReader;

  public EquipoRepositoryImpl() {
    this.persistence = new RepositoryFileImpl();
    this.equipoMapper = new EquipoMapper();
    this.csvFileReader = new CsvFileReader();
    this.csvFileWriter = new CsvFileWriter();
  }

  @Override
  public void save(Equipo equipo) {
    persistence.save(equipo);
  }

  @Override
  public Optional<Equipo> findEquipoByNombre(String nombre) {
    Optional<EquipoEntity> equipoEntityOptional = persistence.findEquipoByNombre(nombre);
    return equipoEntityOptional.map(equipoEntity -> equipoMapper.toDomain(equipoEntity));
  }

  public void readEquiposCsv() {
    Set<EquipoEntity> equipos = csvFileReader.getAll();

    for (EquipoEntity entity : equipos) {
      entity.setPartidosVisitante(new ArrayList<>());
      entity.setPartidosLocal(new ArrayList<>());
      csvFileWriter.save(entity);
    }
  }
}
