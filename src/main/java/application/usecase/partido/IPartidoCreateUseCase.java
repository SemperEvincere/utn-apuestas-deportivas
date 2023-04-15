package application.usecase.partido;

import java.time.LocalDate;

public interface IPartidoCreateUseCase {

  void create(String nombreEquipoUno, String nombreEquipoDos, LocalDate fecha, String ubicacion);
}
