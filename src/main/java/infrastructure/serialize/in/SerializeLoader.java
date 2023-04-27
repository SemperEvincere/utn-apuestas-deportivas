package infrastructure.serialize.in;

import domain.Usuario;
import infrastructure.database.entities.EquipoEntity;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class SerializeLoader {



  public void read(String path) {
    try {
      FileInputStream archivo = new FileInputStream(path);
      ObjectInputStream objectInputStream = new ObjectInputStream(archivo);

      Usuario usuario = (Usuario) objectInputStream.readObject();
    } catch (IOException | ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  public List<EquipoEntity> getAllEquipos() {

    try {
      FileInputStream archivo = new FileInputStream("./app/src/main/resources/csv/equipos/equipos.dat");
      ObjectInputStream objectInputStream = new ObjectInputStream(archivo);

      return (List<EquipoEntity>) objectInputStream.readObject();
    } catch (IOException | ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }
}
