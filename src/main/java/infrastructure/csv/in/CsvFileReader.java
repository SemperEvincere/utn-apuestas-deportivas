package infrastructure.csv.in;

import domain.Equipo;
import infrastructure.entities.EquipoEntity;
import infrastructure.entities.PartidoEntity;
import infrastructure.entities.UsuarioEntity;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class CsvFileReader {

  public Optional<UsuarioEntity> findUsuarioByEmail(String email) {
    try {
      FileReader fileReader = new FileReader("./app/src/main/resources/csv/usuarios/usuarios.csv");

      BufferedReader bufferedReader = new BufferedReader(fileReader);
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        String[] usuario = line.split(",");
        if (usuario[2].equals(email)) {
          UsuarioEntity usuarioEntity = new UsuarioEntity();
          usuarioEntity.setId(UUID.fromString(usuario[0]));
          usuarioEntity.setNick(usuario[1]);
          usuarioEntity.setEmail(usuario[2]);
          usuarioEntity.setPassword(usuario[3]);
          usuarioEntity.setApuestas(new ArrayList<>());
          return Optional.of(usuarioEntity);
        }
      }

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return Optional.empty();
  }

  public Optional<EquipoEntity> findEquipoByNombre(String nombre) {
    File file = new File("./app/src/main/resources/csv/equipos/out/equipos.csv");
    if (!file.exists()) {
      try {
        file.createNewFile();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
    try {
      FileReader fileReader = new FileReader(file);

      BufferedReader bufferedReader = new BufferedReader(fileReader);
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        String[] equipo = line.split(",");
        if (equipo[1].equals(nombre)) {
          EquipoEntity equipoEntity = new EquipoEntity();
          equipoEntity.setId(UUID.fromString(equipo[0]));
          equipoEntity.setNombre(equipo[1]);
          equipoEntity.setCiudadOrigen(equipo[2]);
          equipoEntity.setPartidosLocal(new ArrayList<>());
          equipoEntity.setPartidosVisitante(new ArrayList<>());

          return Optional.of(equipoEntity);
        }
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return Optional.empty();
  }


  public Optional<PartidoEntity> findPartidoById(UUID idPartido) {
    File file = new File("./app/src/main/resources/csv/partidos/partidos.csv");
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
      return bufferedReader.lines()
          .map(line -> line.split(","))
          .filter(partido -> partido[0].equals(idPartido.toString()))
          .map(partido -> {
            PartidoEntity partidoEntity = new PartidoEntity();
            partidoEntity.setId(UUID.fromString(partido[0]));
            partidoEntity.setFecha(LocalDate.parse(partido[1]));
            partidoEntity.setUbicacion(partido[2]);
            partidoEntity.setNombreEquipoLocal(partido[3]);
            partidoEntity.setNombreEquipoVisitante(partido[4]);
            partidoEntity.setGolesLocal(Integer.parseInt(partido[5]));
            partidoEntity.setGolesVisitante(Integer.parseInt(partido[6]));
            return partidoEntity;
          })
          .findFirst();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public Set<EquipoEntity> getAll() {
    File file = new File("./app/src/main/resources/csv/equipos/in/equipos.csv");
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
      return bufferedReader.lines().map(line -> line.split(",")).map(equipo -> {
        EquipoEntity equipoEntity = new EquipoEntity();
        equipoEntity.setNombre(equipo[0]);
        equipoEntity.setCiudadOrigen(equipo[1]);
        return equipoEntity;
      })
          .collect(Collectors.toSet());
    } catch (IOException e) {
      throw new RuntimeException();

    }
  }
}
