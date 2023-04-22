package application.service;

import application.repository.IRondaRepository;
import application.usecase.ronda.IRondaCreateUseCase;
import application.usecase.ronda.IRondaFindUseCase;
import domain.Ronda;
import infrastructure.persistence.RondaRepositoryImpl;
import java.util.List;
import java.util.Set;

public class RondaService implements IRondaCreateUseCase, IRondaFindUseCase {

  private final IRondaRepository rondaRepository;

  public RondaService() {
    this.rondaRepository = new RondaRepositoryImpl();
  }
  @Override
  public List<Ronda> createRonda() {
    return rondaRepository.create();
  }


  @Override
  public Ronda findRondaByNumero(int numeroRonda) {
    return rondaRepository.findRondaByNumero(numeroRonda);
  }
}
