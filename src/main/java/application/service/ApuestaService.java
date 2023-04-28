package application.service;

import application.repository.IApuestaRepository;
import application.usecase.apuesta.IApuestaCreateUseCase;
import application.usecase.apuesta.IApuestaFindByUseCase;
import domain.Apuesta;
import domain.Partido;
import domain.Usuario;
import infrastructure.database.entities.ApuestaEntity;
import infrastructure.database.persistence.ApuestaRepositoryImpl;
import infrastructure.database.persistence.UsuarioRepositoryImpl;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ApuestaService implements IApuestaCreateUseCase, IApuestaFindByUseCase {

  private UsuarioService usuarioService;
  private EquipoService equipoService;
  private IApuestaRepository apuestaRepository;
  private PartidoService partidoService;

  public ApuestaService() {
    this.usuarioService = new UsuarioService();
    this.equipoService = new EquipoService();
    this.apuestaRepository = new ApuestaRepositoryImpl();
    this.partidoService = new PartidoService();
  }


  @Override
  public Apuesta createApuesta(String emailUsuario,
      Partido partido,
      int golesLocalPronosticados,
      int golesVisitantePronosticados,
      double montoApostado) {
    Optional<Usuario> usuarioSaved = usuarioService.findUsuarioByEmail(emailUsuario);
    if(usuarioSaved.isPresent()) {
      Usuario usuario = usuarioSaved.get();
//      Optional<Partido> partido = partidoService.findPartidoById(idPartido);
//      if(partido.isEmpty()) {
//        throw new IllegalArgumentException("El partido no existe");
//      }
      Apuesta apuesta = new Apuesta(usuario, partido, golesLocalPronosticados, golesVisitantePronosticados, montoApostado);
      apuestaRepository.save(apuesta);
      return apuesta;
    } else {
      throw new IllegalArgumentException("El usuario no existe");
    }
  }


  @Override
  public Optional<List<Apuesta>> findApuestasByUsuarioEmail(String email) {
    Optional<Usuario> usuario = usuarioService.findUsuarioByEmail(email);
    if(usuario.isPresent()) {
      return apuestaRepository.findApuestasByUsuarioEmail(email);
    }

    return Optional.empty();
  }

  @Override
  public ApuestaEntity findApuestaById(UUID idApuesta) {
    return apuestaRepository.findApuestaById(idApuesta);
  }
}
