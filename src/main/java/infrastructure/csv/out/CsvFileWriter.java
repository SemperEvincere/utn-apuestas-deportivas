package infrastructure.csv.out;

import domain.Usuario;
import infrastructure.database.entities.ApuestaEntity;
import infrastructure.database.entities.EquipoEntity;
import infrastructure.database.entities.PartidoEntity;
import infrastructure.database.entities.UsuarioEntity;
import infrastructure.mapper.EquipoMapper;
import infrastructure.mapper.UsuarioMapper;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CsvFileWriter {

  private final UsuarioMapper usuarioMapper;
  private final EquipoMapper equipoMapper;

  public CsvFileWriter() {
    this.usuarioMapper = new UsuarioMapper();
    this.equipoMapper = new EquipoMapper();
  }

  public void save(Object entity) {
    if (entity instanceof Usuario) {
      saveUsuario((Usuario) entity);
    } else if (entity instanceof EquipoEntity) {
      saveEquipo((EquipoEntity) entity);
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

  private void saveEquipo(EquipoEntity equipoEntity) {
    File file = new File("./app/src/main/resources/csv/equipos/out/equipos.csv");
    if (!file.exists()) {
      try {
        file.createNewFile();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }

    try {
      FileWriter fileWriter = new FileWriter(file, true);
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
