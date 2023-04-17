package infrastructure.persistence;

import application.repository.IRondaRepository;
import domain.Equipo;
import domain.Ronda;
import infrastructure.csv.in.CsvFileReader;
import infrastructure.csv.out.CsvFileWriter;
import infrastructure.entities.EquipoEntity;
import infrastructure.entities.PartidoEntity;
import infrastructure.mapper.EquipoMapper;
import infrastructure.mapper.PartidoMapper;
import infrastructure.persistence.implement.RepositoryFileImpl;
import infrastructure.persistence.port.IPersistence;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class RondaRepositoryImpl implements IRondaRepository {

  private final EquipoMapper equipoMapper;
  private final CsvFileReader csvFileReader;
  private final CsvFileWriter csvFileWriter;
  private final IPersistence persistence;
  private final PartidoMapper partidoMapper;

  public RondaRepositoryImpl() {
    this.csvFileReader = new CsvFileReader();
    this.equipoMapper = new EquipoMapper();
    this.csvFileWriter = new CsvFileWriter();
    this.persistence = new RepositoryFileImpl();
    this.partidoMapper = new PartidoMapper();
  }


  @Override
  public List<Ronda> create() {
    Set<EquipoEntity> equipos = persistence.getAllEquipos();
    List<EquipoEntity> equiposList = new ArrayList<>(equipos);
    List<Ronda> rondas = new ArrayList<>();
    Set<LocalDate> fechasUtilizadas = new HashSet<>();

    // Mezclar los equipos
    Collections.shuffle(equiposList);

    // calcular el numero de rondas necesarias
    int rounds = equiposList.size() - 1;

    int matchesPerRound = equiposList.size() / 2;

    for (int round = 0; round < rounds; round++) {
      List<PartidoEntity> partidos = new ArrayList<>();

      for (int match = 0; match < matchesPerRound; match++) {
        int localIndex = (round + match) % (equiposList.size() - 1);
        int visitanteIndex = (equiposList.size() - 1 - match + round) % (equiposList.size() - 1);

        EquipoEntity equipoLocal = equiposList.get(localIndex);
        EquipoEntity equipoVisitante = equiposList.get(visitanteIndex);

        PartidoEntity partido = new PartidoEntity();
        partido.setUbicacion(equipoLocal.getCiudadOrigen());
        partido.setFecha(this.establecerFecha(fechasUtilizadas));
        partido.setNombreEquipoLocal(equipoLocal.getNombre());
        partido.setNombreEquipoVisitante(equipoVisitante.getNombre());
        partido.setGolesLocal(0);
        partido.setGolesVisitante(0);

        partidos.add(partido);
      }

      Ronda ronda = new Ronda();
      // todo: hacer una llamada a un método para guardar la ronda en la base de datos
      ronda.setNumero(1);
      ronda.setPartidos(partidos.stream().map(partidoMapper::toDomain).collect(Collectors.toList()));
      rondas.add(ronda);
    }

    return rondas;
  }

  private LocalDate establecerFecha(Set<LocalDate> fechasUtilizadas) {
    // Calcula la diferencia en milisegundos entre las dos fechas
    LocalDate fechaInicio = LocalDate.of(2023,10,1);
    LocalDate fechaFin = LocalDate.of(2023,11,1);
    long diferencia = ChronoUnit.DAYS.between(fechaInicio, fechaFin);

    // Crea un objeto Random para generar números aleatorios
    Random random = new Random();

    // Genera un número aleatorio en el rango de la diferencia en días
    long offset = random.nextLong() % diferencia;

    // Crea una fecha a partir de la fecha de inicio y el offset aleatorio
    LocalDate fechaAleatoria = fechaInicio.plusDays(offset);

    // Verifica si la fecha ya ha sido utilizada anteriormente
    if (fechasUtilizadas.contains(fechaAleatoria)) {
      // Si ya ha sido utilizada, llama recursivamente al método para generar otra fecha aleatoria
      return establecerFecha(fechasUtilizadas);
    } else {
      // Si no ha sido utilizada, agrega la fecha a la lista de fechas utilizadas y devuelve la fecha aleatoria generada
      fechasUtilizadas.add(fechaAleatoria);
      return fechaAleatoria;
    }
  }
}