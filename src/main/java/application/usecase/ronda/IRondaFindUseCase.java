package application.usecase.ronda;

import domain.Partido;
import domain.Ronda;

import java.time.LocalDate;
import java.util.List;

public interface IRondaFindUseCase {

  Ronda findRondaByNumero(int i);

    List<Ronda> getAllRondas();

  Partido findPartidoByFecha(LocalDate fechaPartido);
}
