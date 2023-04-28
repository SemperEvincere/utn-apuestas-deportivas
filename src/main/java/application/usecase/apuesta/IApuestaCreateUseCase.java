package application.usecase.apuesta;

import domain.Apuesta;
import domain.Partido;
import java.util.UUID;

public interface IApuestaCreateUseCase {

  Apuesta createApuesta(String emailUsuario,
      Partido partido,
      int golesLocalPronosticados,
      int golesVisitantePronosticados,
      double montoApostado);
}



