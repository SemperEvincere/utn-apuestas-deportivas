package application.service;

import application.repository.IEquipoRepository;
import application.usecase.equipo.IEquipoCreateUseCase;
import application.usecase.equipo.IEquipoFindUseCase;
import domain.Equipo;
import infrastructure.persistence.EquipoRepositoryImpl;
import java.util.Optional;

public class EquipoService implements IEquipoCreateUseCase, IEquipoFindUseCase {

  private final IEquipoRepository equipoRepository;

  public EquipoService() {
    this.equipoRepository = new EquipoRepositoryImpl();
  }

  @Override
  public void create(Equipo equipo) {
    if (equipo.getNombre() == null || equipo.getNombre().isEmpty())
      throw new RuntimeException("El nombre del equipo no puede estar vacio");
    if (equipo.getCiudadOrigen() == null || equipo.getCiudadOrigen().isEmpty())
      throw new RuntimeException("La ciudad de origen del equipo no puede ser vacia");
    if (existeEquipo(equipo.getNombre()))
      throw new RuntimeException("El equipo ya existe");

    equipoRepository.save(equipo);
  }

  private boolean existeEquipo(String nombre) {
    return this.findEquipoByNombre(nombre).isPresent();
  }

  @Override
  public Optional<Equipo> findEquipoByNombre(String nombre) {
    return equipoRepository.findEquipoByNombre(nombre);
  }

  public void createEquiposCsv() {
    equipoRepository.createEquiposCsv();
  }
}
