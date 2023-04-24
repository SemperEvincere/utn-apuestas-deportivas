package infrastructure.mapper;

import application.service.PartidoService;
import application.service.UsuarioService;
import domain.Apuesta;
import infrastructure.entities.ApuestaEntity;
import java.util.ArrayList;
import java.util.List;

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
      apuestaEntity.setIdPartido(apuesta.getPartido().getId());
      apuestaEntity.setGolesLocalPronosticados(apuesta.getGolesLocalPronosticados());
      apuestaEntity.setGolesVisitantePronosticados(apuesta.getGolesVisitantePronosticados());
      apuestaEntity.setMontoApostado(apuesta.getMontoApostado());
      return apuestaEntity;
    }

  public List<Apuesta> toDomain(List<ApuestaEntity> apuestasEntities) {
    List<Apuesta> apuestas = new ArrayList<>(apuestasEntities.size());
    for (ApuestaEntity apuestaEntity : apuestasEntities) {
      Apuesta apuesta = new Apuesta();
      apuesta.setId(apuestaEntity.getId());
      apuesta.setUsuario(usuarioService.findUsuarioById(apuestaEntity.getIdUsuario()).orElseThrow());
      apuesta.setPartido(partidoService.findPartidoById(apuestaEntity.getIdPartido()).orElseThrow());
      apuesta.setGolesLocalPronosticados(apuestaEntity.getGolesLocalPronosticados());
      apuesta.setGolesVisitantePronosticados(apuestaEntity.getGolesVisitantePronosticados());
      apuesta.setMontoApostado(apuestaEntity.getMontoApostado());
      apuestas.add(apuesta);
    }
    return apuestas;
  }
}
