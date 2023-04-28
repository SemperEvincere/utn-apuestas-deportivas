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

    for (Partido partido : partidosApostados) {
      int golesLocal = random.nextInt(11);
      int golesVisitante = -1;
      do {
        golesVisitante = random.nextInt(11);
      } while (golesVisitante == golesLocal); // Repetir si los goles son iguales
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
        if (golesLocalesPronostico == golesLocalPartido && golesVisitantesPronostico == golesVisitantePartido) {
          aciertos += 1;
        }
      }
    }
    System.out.println("El usuario tiene un total de: " + aciertos);
  }
}
