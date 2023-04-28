package application.usecase.apuesta;

import domain.Apuesta;
import java.util.UUID;

public interface IApuestaCreateUseCase {

  Apuesta createApuesta(String emailUsuario,
      UUID idPartido,
      int golesLocalPronosticados,
      int golesVisitantePronosticados,
      double montoApostado);
}



