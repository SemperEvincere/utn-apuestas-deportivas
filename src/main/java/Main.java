import application.service.ApuestaService;
import application.service.EquipoService;
import application.service.PartidoService;
import application.service.RondaService;
import application.service.UsuarioService;
import domain.Ronda;
import domain.Usuario;
import infrastructure.persistence.EquipoRepositoryImpl;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main {



  public static void main(String[] args) {
//    IUsuarioCreateUseCase usuarioCreateUseCase = new UsuarioService();
//    IUsuarioFindUseCase usuarioFindUseCase = new UsuarioService();
//    Usuario usuario = new Usuario();
//    usuario.setNick("Evincere");
//    usuario.setEmail("evincere@gmail.com");
//    usuario.setPassword("123456");

//    usuarioCreateUseCase.create(usuario);

//    System.out.println(usuarioFindUseCase.findUsuarioByEmail("elsemper@gmail.com"));

//    IEquipoCreateUseCase equipoCreateUseCase = new EquipoService();
//
//    Equipo equipoUno = new Equipo();
//    equipoUno.setNombre("Equipo 1");
//    equipoUno.setCiudadOrigen("Ciudad 1");
//    Equipo equipoDos = new Equipo();
//    equipoDos.setNombre("Equipo 2");
//    equipoDos.setCiudadOrigen("Ciudad 2");
//    Equipo equipoTres = new Equipo();
//    equipoTres.setNombre("Equipo 3");
//    equipoTres.setCiudadOrigen("Ciudad 3");

//    equipoCreateUseCase.create(equipoUno);
//    equipoCreateUseCase.create(equipoDos);
//    equipoCreateUseCase.create(equipoTres);

//    IPartidoCreateUseCase partidoCreateUseCase = new PartidoService();
//    partidoCreateUseCase.create("Equipo 1", "Equipo 3", LocalDate.parse("2020-12-12"), "Ciudad 1");

//    ApuestaService apuestaService = new ApuestaService();
//    apuestaService.createApuesta("elsemper@gmail.com", UUID.fromString("5f1b0b3c-4247-49a3-89bf-526267d040a4"), 1, 2, 1000);

//    UsuarioService usuarioService = new UsuarioService();
//    Usuario usuario = new Usuario();
//    usuario.setNick("Semper Evincere");
//    usuario.setEmail("elsemper@gmail.com");
//    usuario.setPassword("123456");
//    usuario.setApuestas(new ArrayList<>());
//    usuarioService.create(usuario);

//    usuarioService.read();

//    EquipoRepositoryImpl equipoRepository = new EquipoRepositoryImpl();
//    equipoRepository.readEquiposCsv();

//    RondaService rondaService = new RondaService();
//    List<Ronda> rondas = rondaService.createRonda();
//    for (Ronda ronda : rondas) {
//      System.out.println("Ronda N° " + ronda.getNumero());
//      ronda.getPartidos().forEach(partido -> {
//      System.out.println("El partido se celebra en: " + partido.getUbicacion());
//      System.out.println("La fecha del partido es : " + partido.getFecha());
//      System.out.println("se enfrenta " + partido.getEquipoLocal().getNombre() + " como equipo LOCAL");
//      System.out.println("contra " + partido.getEquipoVisitante().getNombre() + " como equipo VISITANTE");});
//    }

//    UsuarioService usuarioService = new UsuarioService();
//    Usuario usuario = new Usuario();
//    usuario.setNick("Semper Evincere");
//    usuario.setEmail("elsemper@gmail.com");
//    usuario.setPassword("123456");
//    usuario.setApuestas(new ArrayList<>());
//    usuarioService.create(usuario);

//    EquipoService equipoService = new EquipoService();
//    equipoService.createEquiposCsv();

//    PartidoService partidoService = new PartidoService();
//    partidoService.save("FC Barcelona", "Real Madrid", LocalDate.parse("2020-12-12"), "Madrid");

    RondaService rondaService = new RondaService();
//    rondaService.createRonda();
    Ronda ronda = rondaService.findRondaByNumero(1);

      System.out.println("El partido se celebra en: " + ronda.getPartidos().get(0).getUbicacion());
      System.out.println("La fecha del partido es : " + ronda.getPartidos().get(0).getFecha());
      System.out.println("se enfrenta " + ronda.getPartidos().get(0).getEquipoLocal().getNombre() + " como equipo LOCAL");
      System.out.println("contra " + ronda.getPartidos().get(0).getEquipoVisitante().getNombre() + " como equipo VISITANTE");


  }

}
