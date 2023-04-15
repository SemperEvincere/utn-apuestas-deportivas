package application.usecase.apuesta;

import java.util.UUID;

public interface IApuestaCreateUseCase {

  void createApuesta(String emailUsuario,
      UUID idPartido,
      int golesLocalPronosticados,
      int golesVisitantePronosticados,
      double montoApostado);
}



