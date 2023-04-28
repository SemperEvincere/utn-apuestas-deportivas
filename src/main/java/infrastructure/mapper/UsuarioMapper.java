package infrastructure.mapper;

import domain.Apuesta;
import domain.Usuario;
import infrastructure.database.entities.ApuestaEntity;
import infrastructure.database.entities.UsuarioEntity;
import java.util.ArrayList;
import java.util.List;


public class UsuarioMapper {

  public UsuarioMapper() {
  }
  public UsuarioEntity toEntity(Usuario usuario) {
    UsuarioEntity usuarioEntity = new UsuarioEntity();
    usuarioEntity.setId(usuario.getId());
    usuarioEntity.setNick(usuario.getNick());
    usuarioEntity.setEmail(usuario.getEmail());
    usuarioEntity.setPassword(usuario.getPassword());
    List<Apuesta> apuestas = usuario.getApuestas();
    List<ApuestaEntity> apuestasEntity = new ArrayList<>();
    if (apuestas == null) {
      return usuarioEntity;
    }
    for (Apuesta apuesta : apuestas) {
      ApuestaEntity apuestaEntity = new ApuestaEntity();
      apuestaEntity.setId(apuesta.getId());
      apuestaEntity.setIdUsuario(apuesta.getUsuario().getId());
      apuestaEntity.setIdPartido(apuesta.getPartido().getId());
      apuestaEntity.setGolesLocalPronosticados(apuesta.getGolesLocalPronosticados());
      apuestaEntity.setGolesVisitantePronosticados(apuesta.getGolesVisitantePronosticados());
      apuestaEntity.setMontoApostado(apuesta.getMontoApostado());
      apuestasEntity.add(apuestaEntity);
    }
    usuarioEntity.setApuestas(apuestasEntity);

    return usuarioEntity;
  }

  public Usuario toDomain(UsuarioEntity usuarioEntity) {
    Usuario usuario = new Usuario();
    usuario.setId(usuarioEntity.getId());
    usuario.setNick(usuarioEntity.getNick());
    usuario.setEmail(usuarioEntity.getEmail());
    usuario.setPassword(usuarioEntity.getPassword());
    List<ApuestaEntity> apuestasEntity = usuarioEntity.getApuestas();
    List<Apuesta> apuestas = new ArrayList<>();
    for (ApuestaEntity apuestaEntity : apuestasEntity) {
      Apuesta apuesta = new Apuesta();
      apuesta.setId(apuestaEntity.getId());
      apuesta.setUsuario(usuario);
      apuesta.setPartido(null);
      apuesta.setGolesLocalPronosticados(apuestaEntity.getGolesLocalPronosticados());
      apuesta.setGolesVisitantePronosticados(apuestaEntity.getGolesVisitantePronosticados());
      apuesta.setMontoApostado(apuestaEntity.getMontoApostado());
      apuestas.add(apuesta);
    }
    usuario.setApuestas(apuestas);

    return usuario;
  }

}
