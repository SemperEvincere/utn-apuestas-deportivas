package application.repository;

import domain.Partido;
import domain.Ronda;

import java.time.LocalDate;
import java.util.List;

public interface IRondaRepository {

  List<Ronda> create();

  Ronda findRondaByNumero(int numeroRonda);

  List<Ronda> getAllRondas();

  Partido findPartidoByFecha(LocalDate fechaPartido);
}
