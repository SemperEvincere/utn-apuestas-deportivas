package application.service;

import application.repository.IRondaRepository;
import application.usecase.ronda.IRondaCreateUseCase;
import domain.Ronda;
import infrastructure.persistence.RondaRepositoryImpl;
import java.util.List;
import java.util.Set;

public class RondaService implements IRondaCreateUseCase {

  private IRondaRepository rondaRepository;

  public RondaService() {
    this.rondaRepository = new RondaRepositoryImpl();
  }
  @Override
  public List<Ronda> createRonda() {
    return rondaRepository.create();
  }
}
