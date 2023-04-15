package infrastructure.mapper;

import application.service.PartidoService;
import application.service.UsuarioService;
import domain.Apuesta;
import infrastructure.entities.ApuestaEntity;

public class ApuestaMapper {

private final UsuarioMapper usuarioMapper;
private final PartidoMapper partidoMapper;
private final UsuarioService usuarioService;
private final PartidoService partidoService;

  public ApuestaMapper() {
    this.usuarioMapper = new UsuarioMapper();
    this.partidoMapper = new PartidoMapper();
    this.usuarioService = new UsuarioService();
    this.partidoService = new PartidoService();
  }

    public ApuestaEntity toEntity(Apuesta apuesta) {
      ApuestaEntity apuestaEntity = new ApuestaEntity();
      apuestaEntity.setIdUsuario(apuesta.getUsuario().getId());
//      apuestaEntity.setIdUsuario(usuarioService.findUsuarioByEmail(apuesta.getUsuario().getEmail()).get().getId());
      apuestaEntity.setIdPartido(apuesta.getPartido().getId());
//      apuestaEntity.setIdPartido(partidoService.findPartidoById(apuesta.getPartido().getId()).get().getId());
      apuestaEntity.setGolesLocalPronosticados(apuesta.getGolesLocalPronosticados());
      apuestaEntity.setGolesVisitantePronosticados(apuesta.getGolesVisitantePronosticados());
      apuestaEntity.setMontoApostado(apuesta.getMontoApostado());
      return apuestaEntity;
    }

}
