package infrastructure.mapper;

import domain.Usuario;
import infrastructure.database.entities.UsuarioEntity;
import java.util.ArrayList;


public class UsuarioMapper {

  private final ApuestaMapper apuestaMapper = new ApuestaMapper();
  public UsuarioEntity toEntity(Usuario usuario) {
    UsuarioEntity usuarioEntity = new UsuarioEntity();

    usuarioEntity.setNick(usuario.getNick());
    usuarioEntity.setEmail(usuario.getEmail());
    usuarioEntity.setPassword(usuario.getPassword());
    usuarioEntity.setApuestas(usuario.getApuestas().stream().map(apuestaMapper::toEntity).toList());

    return usuarioEntity;
  }

  public Usuario toDomain(UsuarioEntity usuarioEntity) {
    Usuario usuario = new Usuario();
    usuario.setId(usuarioEntity.getId());
    usuario.setNick(usuarioEntity.getNick());
    usuario.setEmail(usuarioEntity.getEmail());
    usuario.setPassword(usuarioEntity.getPassword());
    usuario.setApuestas(new ArrayList<>());

    return usuario;
  }

}
