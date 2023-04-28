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
      partido.setGolesLocal(random.nextInt(11));
      partido.setGolesVisitante(random.nextInt(11));
    }

    int aciertos = 0;

    for (Apuesta apuesta : apuestasDelUsuario) {
      for (Partido partido : partidosApostados) {
        if(apuesta.getGolesLocalPronosticados()==partido.getGolesLocal() && apuesta.getGolesVisitantePronosticados()==partido.getGolesLocal()) {
          aciertos+=1;
        }
      }
    }
    System.out.println("El usuario tiene un total de: " + aciertos);
  }
}
