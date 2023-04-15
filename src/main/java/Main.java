import application.service.ApuestaService;
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

    ApuestaService apuestaService = new ApuestaService();
    apuestaService.createApuesta("elsemper@gmail.com", UUID.fromString("5f1b0b3c-4247-49a3-89bf-526267d040a4"), 1, 2, 1000);


  }

}
