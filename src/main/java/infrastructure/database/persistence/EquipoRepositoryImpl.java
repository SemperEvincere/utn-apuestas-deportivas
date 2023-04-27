package infrastructure.database.persistence;

import application.repository.IEquipoRepository;
import domain.Equipo;
import infrastructure.csv.in.CsvFileReader;
import infrastructure.csv.out.CsvFileWriter;
import infrastructure.database.entities.EquipoEntity;
import infrastructure.mapper.EquipoMapper;
import infrastructure.database.persistence.implement.RepositoryMySqlImpl;
import infrastructure.database.persistence.port.IPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EquipoRepositoryImpl implements IEquipoRepository {

  private final IPersistence persistence;
  private final EquipoMapper equipoMapper;
  private final CsvFileWriter csvFileWriter;
  private final CsvFileReader csvFileReader;

  public EquipoRepositoryImpl() {
    this.persistence = new RepositoryMySqlImpl();
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
    return equipoEntityOptional.map(equipoMapper::toDomain);
  }

  @Override
  public void createEquiposCsv() {
    List<EquipoEntity> equipoEntitiesSaved = persistence.getAllEquipos();
    if (equipoEntitiesSaved.isEmpty()) {
      List<EquipoEntity> equipos = persistence.getAllEquiposCsv();
      persistence.saveAll(equipos);
    } else {
      System.out.println("Los equipos ya se encuentran cargados en la BD");
    }
  }

  @Override
  public List<Equipo> getAllEquipos() {
    return persistence.getAllEquipos().stream().map(equipoMapper::toDomain).collect(Collectors.toList());
  }

  public void readEquiposCsv() {
    List<EquipoEntity> equipos = csvFileReader.getAll();
    for (EquipoEntity entity : equipos) {
      entity.setPartidosVisitante(new ArrayList<>());
      entity.setPartidosLocal(new ArrayList<>());
      csvFileWriter.save(entity);
    }
  }
}
