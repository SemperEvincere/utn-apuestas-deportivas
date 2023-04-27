import application.service.EquipoService;
import application.service.RondaService;
import application.service.UsuarioService;
import domain.Equipo;
import domain.Partido;
import domain.Usuario;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Optional;

public class Main {

  private static final UsuarioService usuarioService = new UsuarioService();
  private static final EquipoService equipoService = new EquipoService();
  private static final RondaService rondaService = new RondaService();


  public static void main(String[] args) {

//    USUARIOS
//    crearUnUsuario("Semper", "elsemper@gmail.com", "123456");
//    mostrarUsuario("elsemper@gmail.com");

//    EQUIPOS
//    guardarEquiposEnBD();
//    mostrarEquiposCargados();

//    RONDAS
//    crearRondaDePartidos();
    mostrarRondaDePartidos(1);

//    APUESTA
    crearUnaApuesta("elsemper@gmail.com",
            LocalDate.of(2023,9,22),
            2,
            1,
            10000d);



  }

  private static void crearUnaApuesta(
          String emailApostador,
          LocalDate fechaPartido,
          int golesLocal,
          int golesVisitante,
          double montoApuesta) {
    Optional<Usuario> usuarioSaved = usuarioService.findUsuarioByEmail(emailApostador);
    if(usuarioSaved.isEmpty()) {
      System.out.println("No existe un usuario con el email: " + emailApostador);
      return;
    }
    Usuario usuario = usuarioSaved.get();
    Partido partidoParaApuesta = rondaService.findPartidoByFecha(fechaPartido);
    if(partidoParaApuesta == null) {
      System.out.println("No existe un partido en la fecha: " + fechaPartido);
      return;
    }
    
    System.out.println(partidoParaApuesta.getEquipoLocal().getNombre());
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
