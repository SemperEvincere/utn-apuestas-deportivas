package application.service;

import application.repository.IRondaRepository;
import application.usecase.ronda.IRondaCreateUseCase;
import application.usecase.ronda.IRondaFindUseCase;
import domain.Partido;
import domain.Ronda;
import infrastructure.database.persistence.RondaRepositoryImpl;

import java.time.LocalDate;
import java.util.List;

public class RondaService implements IRondaCreateUseCase, IRondaFindUseCase {

  private final IRondaRepository rondaRepository;

  public RondaService() {
    this.rondaRepository = new RondaRepositoryImpl();
  }

  @Override
  public List<Ronda> createRonda() {
    if (rondaRepository.getAllRondas().isEmpty()) {
      return rondaRepository.create();
    }
    return rondaRepository.getAllRondas();
  }


  @Override
  public Ronda findRondaByNumero(int numeroRonda) {
    return rondaRepository.findRondaByNumero(numeroRonda);
  }

  @Override
  public List<Ronda> getAllRondas() {
    return rondaRepository.getAllRondas();
  }

  @Override
  public Partido findPartidoByFecha(LocalDate fechaPartido) {
    return rondaRepository.findPartidoByFecha(fechaPartido);
  }
}
