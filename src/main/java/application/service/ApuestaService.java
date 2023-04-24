package application.service;

import application.repository.IApuestaRepository;
import application.usecase.apuesta.IApuestaCreateUseCase;
import application.usecase.apuesta.IApuestaFindByUsuarioEmail;
import domain.Apuesta;
import domain.Partido;
import domain.Usuario;
import infrastructure.persistence.ApuestaRepositoryImpl;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ApuestaService implements IApuestaCreateUseCase, IApuestaFindByUsuarioEmail {

  private final UsuarioService usuarioService;
  private final EquipoService equipoService;
  private final IApuestaRepository apuestaRepository;
  private final PartidoService partidoService;

  public ApuestaService() {
    this.usuarioService = new UsuarioService();
    this.equipoService = new EquipoService();
    this.apuestaRepository = new ApuestaRepositoryImpl();
    this.partidoService = new PartidoService();
  }


  @Override
  public void createApuesta(String emailUsuario,
      UUID idPartido,
      int golesLocalPronosticados,
      int golesVisitantePronosticados,
      double montoApostado) {
    Optional<Usuario> usuarioSaved = usuarioService.findUsuarioByEmail(emailUsuario);
    if(usuarioSaved.isPresent()) {
      Usuario usuario = usuarioSaved.get();
      Optional<Partido> partido = partidoService.findPartidoById(idPartido);
      if(partido.isEmpty()) {
        throw new IllegalArgumentException("El partido no existe");
      }
      Apuesta apuesta = new Apuesta(usuario, partido.get(), golesLocalPronosticados, golesVisitantePronosticados, montoApostado);
      apuestaRepository.save(apuesta);
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
}
