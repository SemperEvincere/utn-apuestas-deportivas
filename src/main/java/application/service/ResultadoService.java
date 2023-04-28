package application.service;

import application.usecase.resultado.IResultadoUseCase;
import domain.Apuesta;
import domain.Partido;
import domain.Usuario;
import java.util.List;
import java.util.Random;

public class ResultadoService implements IResultadoUseCase {


  @Override
  public void calcularResultadoDeApuesta(Usuario usuario) {
    List<Apuesta> apuestasDelUsuario = usuario.getApuestas();
    List<Partido> partidosApostados = apuestasDelUsuario.stream().map(Apuesta::getPartido).toList();
    Random random = new Random();
    Random random1 = new Random();

    for (Partido partido : partidosApostados) {
      int golesLocal = random.nextInt(11);
      int golesVisitante = random1.nextInt(11);
      partido.setGolesLocal(golesLocal);
      partido.setGolesVisitante(golesVisitante);
    }

    int aciertos = 0;

    for (Apuesta apuesta : apuestasDelUsuario) {
      for (Partido partido : partidosApostados) {
        int golesLocalesPronostico = apuesta.getGolesLocalPronosticados();
        int golesVisitantesPronostico = apuesta.getGolesVisitantePronosticados();

        int golesLocalPartido = partido.getGolesLocal();
        int golesVisitantePartido = partido.getGolesVisitante();
        System.out.println("En el partido que se celebro en la ciudad de " + partido.getUbicacion());
        System.out.println("Se enfrentaron " + partido.getEquipoLocal().getNombre() + " vs " + partido.getEquipoVisitante().getNombre());
        System.out.println("Goles Local: " + partido.getGolesLocal());
        System.out.println("Goles visitante: " + partido.getGolesVisitante());
        System.out.println("El apostador con nick: " + usuario.getNick());
        System.out.println("Pronosticó que :");
        System.out.println("El equipo local marcaría: " + apuesta.getGolesLocalPronosticados());
        System.out.println("El equipo visitante marcaría: " + apuesta.getGolesVisitantePronosticados());
        if (golesLocalesPronostico == golesLocalPartido && golesVisitantesPronostico == golesVisitantePartido) {
          aciertos += 1;
        }
      }
    }
    System.out.println("El usuario tiene un total de: " + aciertos);
  }
}
