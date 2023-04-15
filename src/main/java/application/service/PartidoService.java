package application.service;

import application.repository.IPartidoRepository;
import application.usecase.partido.IPartidoCreateUseCase;
import application.usecase.partido.IPartidoFindUseCase;
import domain.Equipo;
import domain.Partido;
import infrastructure.persistence.PartidoRepositoryImpl;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public class PartidoService implements IPartidoCreateUseCase, IPartidoFindUseCase {

  private final EquipoService equipoService;
  private final IPartidoRepository partidoRepository;

  public PartidoService() {
    this.equipoService = new EquipoService();
    this.partidoRepository = new PartidoRepositoryImpl();
  }

  @Override
  public void create(String nombreEquipoUno,
      String nombreEquipoDos,
      LocalDate fecha,
      String ubicacion) {
    Equipo equipoUno = equipoService.findEquipoByNombre(nombreEquipoUno).get();
    Equipo equipoDos = equipoService.findEquipoByNombre(nombreEquipoDos).get();
    Partido partido = new Partido(equipoUno, equipoDos, fecha, ubicacion);
    partidoRepository.save(partido);
  }


  @Override
  public Optional<Partido> findPartidoById(UUID idPartido) {
    return partidoRepository.findById(idPartido);
  }
}
