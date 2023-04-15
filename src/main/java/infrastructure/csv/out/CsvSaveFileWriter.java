package infrastructure.csv.out;

import domain.Equipo;
import domain.Usuario;
import infrastructure.entities.ApuestaEntity;
import infrastructure.entities.EquipoEntity;
import infrastructure.entities.PartidoEntity;
import infrastructure.entities.UsuarioEntity;
import infrastructure.mapper.EquipoMapper;
import infrastructure.mapper.UsuarioMapper;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CsvSaveFileWriter {

  private final UsuarioMapper usuarioMapper;
  private final EquipoMapper equipoMapper;

  public CsvSaveFileWriter() {
    this.usuarioMapper = new UsuarioMapper();
    this.equipoMapper = new EquipoMapper();
  }

  public void save(Object entity) {
    if (entity instanceof Usuario) {
      saveUsuario((Usuario) entity);
    } else if (entity instanceof Equipo) {
      saveEquipo((Equipo) entity);
    } else if (entity instanceof ApuestaEntity) {
      saveApuesta((ApuestaEntity) entity);
    } else if (entity instanceof PartidoEntity) {
      savePartido((PartidoEntity) entity);
    }
  }


  private void saveUsuario(Usuario usuario) {
    File file = new File("./app/src/main/resources/csv/usuarios/usuarios.csv");
    if (!file.exists()) {
      try {
        file.createNewFile();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }

    UsuarioEntity usuarioEntity = usuarioMapper.toEntity(usuario);
    try {
      FileWriter fileWriter = new FileWriter("./app/src/main/resources/csv/usuarios/usuarios.csv", true);
      fileWriter.append(usuarioEntity.toString());
      fileWriter.append("\n");
      fileWriter.flush();
      fileWriter.close();

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private void saveEquipo(Equipo equipo) {
    File file = new File("./app/src/main/resources/csv/usuarios/equipos.csv");
    if (!file.exists()) {
      try {
        file.createNewFile();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }

    EquipoEntity equipoEntity = equipoMapper.toEntity(equipo);

    try {
      FileWriter fileWriter = new FileWriter("./app/src/main/resources/csv/usuarios/equipos.csv", true);
      fileWriter.append(equipoEntity.toString());
      fileWriter.append("\n");
      fileWriter.flush();
      fileWriter.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private void savePartido(PartidoEntity partidoEntity) {
    File file = new File("./app/src/main/resources/csv/partidos/partidos.csv");
    if (!file.exists()) {
      try {
        file.createNewFile();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }

    try {
      FileWriter fileWriter = new FileWriter("./app/src/main/resources/csv/partidos/partidos.csv", true);
      fileWriter.append(partidoEntity.toString());
      fileWriter.append("\n");
      fileWriter.flush();
      fileWriter.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private void saveApuesta(ApuestaEntity apuestaEntity) {
    File file = new File("./app/src/main/resources/csv/apuestas/apuestas.csv");
    if (!file.exists()) {
      try {
        file.createNewFile();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }

    try {
      FileWriter fileWriter = new FileWriter(file, true);
      fileWriter.append(apuestaEntity.toString());
      fileWriter.append("\n");
      fileWriter.flush();
      fileWriter.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
