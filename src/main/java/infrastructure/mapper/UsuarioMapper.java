package infrastructure.mapper;

import domain.Usuario;
import infrastructure.database.entities.ApuestaEntity;
import infrastructure.database.entities.UsuarioEntity;
import java.util.ArrayList;


public class UsuarioMapper {

  public UsuarioMapper() {
  }
  public UsuarioEntity toEntity(Usuario usuario) {
    UsuarioEntity usuarioEntity = new UsuarioEntity();
    usuarioEntity.setNick(usuario.getNick());
    usuarioEntity.setEmail(usuario.getEmail());
    usuarioEntity.setPassword(usuario.getPassword());

    usuarioEntity.setApuestas(usuario.getApuestas().stream().map(apuesta -> {
      ApuestaEntity apuestaEntity = new ApuestaEntity();
      apuestaEntity.setId(apuesta.getId());
      apuestaEntity.setIdUsuario(apuesta.getUsuario().getId());
      apuestaEntity.setIdPartido(apuesta.getPartido().getId());
      apuestaEntity.setGolesLocalPronosticados(apuesta.getGolesLocalPronosticados());
      apuestaEntity.setGolesVisitantePronosticados(apuesta.getGolesVisitantePronosticados());
      apuestaEntity.setMontoApostado(apuesta.getMontoApostado());
      return apuestaEntity;
    }).toList());

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
