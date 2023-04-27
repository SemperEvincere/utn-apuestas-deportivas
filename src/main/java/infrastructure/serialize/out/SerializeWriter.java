package infrastructure.serialize.out;

import domain.Usuario;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerializeWriter {

  public void save(Object object) {
    Usuario usuario = (Usuario) object;
    String path = "./app/src/main/resources/csv/usuarios/usuarios.dat";
    try {
      FileOutputStream archivo = new FileOutputStream(path);
      ObjectOutputStream outputStream = new ObjectOutputStream(archivo);
      outputStream.writeObject(usuario);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }

}
