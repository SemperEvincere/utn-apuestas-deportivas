package infrastructure.mapper;

import domain.Usuario;
import infrastructure.entities.UsuarioEntity;
import java.util.ArrayList;


public class UsuarioMapper {

  public UsuarioEntity toEntity(Usuario usuario) {
    UsuarioEntity usuarioEntity = new UsuarioEntity();

    usuarioEntity.setNick(usuario.getNick());
    usuarioEntity.setEmail(usuario.getEmail());
    usuarioEntity.setPassword(usuario.getPassword());
    usuarioEntity.setApuestas(new ArrayList<>());

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
