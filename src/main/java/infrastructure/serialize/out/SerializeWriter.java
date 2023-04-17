package infrastructure.serialize.out;

import domain.Usuario;
import infrastructure.entities.EquipoEntity;
import infrastructure.entities.PartidoEntity;
import infrastructure.entities.UsuarioEntity;
import infrastructure.persistence.port.IPersistence;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Optional;
import java.util.UUID;

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
