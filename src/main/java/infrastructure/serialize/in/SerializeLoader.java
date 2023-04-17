package infrastructure.serialize.in;

import domain.Usuario;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Optional;

public class SerializeLoader {

  String path = "./app/src/main/resources/csv/usuarios/usuarios.dat";

  public void read() {
    try {
      FileInputStream archivo = new FileInputStream(path);
      ObjectInputStream objectInputStream = new ObjectInputStream(archivo);

      Usuario usuario = (Usuario) objectInputStream.readObject();
      System.out.println(usuario.getNick());
    } catch (IOException | ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

}
