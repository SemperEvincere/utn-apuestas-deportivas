import application.service.ApuestaService;
import application.service.EquipoService;
import application.service.PartidoService;
import application.service.ResultadoService;
import application.service.RondaService;
import application.service.UsuarioService;
import domain.Apuesta;
import domain.Equipo;
import domain.Partido;
import domain.Usuario;

import infrastructure.database.entities.ApuestaEntity;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Main {

  private static UsuarioService usuarioService = new UsuarioService();
  private static EquipoService equipoService = new EquipoService();
  private static RondaService rondaService = new RondaService();
  private static PartidoService partidoService = new PartidoService();
  private static ApuestaService apuestaService = new ApuestaService();
  private static ResultadoService resultadoService = new ResultadoService();

  public static void main(String[] args) {

//    USUARIOS
//    crearUnUsuario("Semper", "elsemper@gmail.com", "123456");
//    mostrarUsuario("elsemper@gmail.com");

//    EQUIPOS
//    guardarEquiposEnBD();
//    mostrarEquiposCargados();

//    RONDAS
//    crearRondaDePartidos();
//    mostrarRondaDePartidos(1);

//    APUESTA
//    Optional<Partido> partido = partidoService.findPartidoById(UUID.fromString("03ab8efa-4d36-4fbb-bca3-1401f571ef62"));
//    crearUnaApuesta("elsemper@gmail.com", partido.get(),
//            2,
//            1,
//            10000d);

//    mostrarApuestasDeUsuario("elsemper@gmail.com");
//    mostrarResultadosDePartido(UUID.fromString("03ab8efa-4d36-4fbb-bca3-1401f571ef62"));

    Optional<Usuario> usuario = usuarioService.findUsuarioByEmail("elsemper@gmail.com");

    calcularResultadoDeApuesta(usuario.get());

  }

  private static void calcularResultadoDeApuesta(Usuario usuario) {
    resultadoService.calcularResultadoDeApuesta(usuario);
  }

  private static void mostrarApuestasDeUsuario(String mail) {
    Optional<Usuario> usuario = usuarioService.findUsuarioByEmail(mail);
    if(usuario.isEmpty()) {
      System.out.println("No existe un usuario con el email: " + mail);
      return;
    }
    List<Apuesta> listaApuestas = usuario.get().getApuestas();
    for (Apuesta apuesta : listaApuestas) {
      System.out.println(apuesta.toString());

    }
  }



  private static void crearUnaApuesta(
          String emailApostador,
          Partido partido,
          int golesLocal,
          int golesVisitante,
          double montoApuesta) {
    Optional<Usuario> usuarioSaved = usuarioService.findUsuarioByEmail(emailApostador);
    if(usuarioSaved.isEmpty()) {
      System.out.println("No existe un usuario con el email: " + emailApostador);
      return;
    }
    Usuario usuario = usuarioSaved.get();

    Apuesta apuestaCreated = apuestaService.createApuesta(
        emailApostador,
        partido,
        golesLocal,
        golesVisitante,
        montoApuesta);

    usuarioService.updateUser(usuario, apuestaCreated.getId(), apuestaService);

  }

  private static void mostrarRondaDePartidos(int rondaNumero) {
    rondaService.findRondaByNumero(rondaNumero)
            .getPartidos()
            .stream()
            .sorted(Comparator.comparing(Partido::getFecha))
            .forEach(partido ->
                    System.out.println(
                            "En fecha " + partido.getFecha() + " se enfrentan "
                                    + partido.getEquipoLocal().getNombre()
                                    + " vs "
                                    + partido.getEquipoVisitante().getNombre() +
                                    " en el estadio " + partido.getUbicacion())
            );
  }


  private static void crearRondaDePartidos() {
    rondaService.createRonda();
  }

  private static void mostrarEquiposCargados() {
    equipoService.getAllEquipos().stream().map(Equipo::toString).forEach(System.out::println);
  }


  private static void guardarEquiposEnBD() {
    equipoService.createEquiposCsv();
  }

  private static void mostrarUsuario(String email) {
    Optional<Usuario> usuarioSaved = usuarioService.findUsuarioByEmail(email);
    usuarioSaved.ifPresent(System.out::println);
  }

  private static void crearUnUsuario(String nick, String email, String password) {
    if(verificarSiExisteUsuario(email)) {
      System.out.println("Ya existe un usuario con el email: " + email);
      return;
    }

    Usuario usuarioNuevo = new Usuario(nick, email, password);
    usuarioService.create(usuarioNuevo);
    System.out.println("Usuario creado con éxito");
  }


  private static boolean verificarSiExisteUsuario(String email) {
    return usuarioService.findUsuarioByEmail(email).isPresent();
  }


}
