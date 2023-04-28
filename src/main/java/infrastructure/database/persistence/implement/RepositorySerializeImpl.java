package infrastructure.database.persistence.implement;

import domain.Partido;
import domain.Ronda;
import infrastructure.database.entities.*;
import infrastructure.database.persistence.port.IPersistence;
import infrastructure.serialize.in.SerializeLoader;
import infrastructure.serialize.out.SerializeWriter;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class RepositorySerializeImpl implements IPersistence {

  private SerializeWriter serializeWriter;
  private SerializeLoader serializeLoader;
  String pathUsuarios = "./app/src/main/resources/csv/usuarios/usuarios.dat";

  public RepositorySerializeImpl() {
    this.serializeWriter = new SerializeWriter();
    this.serializeLoader = new SerializeLoader();
  }
  @Override
  public void save(Object object) {
    serializeWriter.save(object);
  }

  @Override
  public void readUsuarios() {
    serializeLoader.read(pathUsuarios);
  }

  @Override
  public List<EquipoEntity> getAllEquiposCsv() {
    return null;
  }

  @Override
  public List<EquipoEntity> getAllEquipos() {
    return serializeLoader.getAllEquipos();
  }

  @Override
  public void saveRonda(Ronda ronda) {

  }

  @Override
  public Ronda findRondaByNumero(int numeroRonda) {
    return null;
  }

  @Override
  public Optional<UsuarioEntity> findByUsuarioEmail(String email) {
    return Optional.empty();
  }

  @Override
  public Optional<List<ApuestaEntity>> findApuestasByUsuarioId(UUID id) {
    return Optional.empty();
  }

  @Override
  public Optional<UsuarioEntity> findUsuarioById(UUID idUsuario) {
    return Optional.empty();
  }

  @Override
  public List<RondaEntity> getAllRondas() {
    return null;
  }

  @Override
  public Partido findPartidoByFecha(LocalDate fechaPartido) {
    return null;
  }

  @Override
  public void updateUser(UsuarioEntity usuarioEntity) {

  }

  @Override
  public ApuestaEntity findApuestaById(UUID idApuesta) {
    return null;
  }

  @Override
  public void saveAll(List<EquipoEntity> equipos) {

  }

  @Override
  public Optional<EquipoEntity> findEquipoByNombre(String nombre) {
    return Optional.empty();
  }

  @Override
  public Optional<UsuarioEntity> findUsuarioByEmail(String email) {
    return Optional.empty();
  }

  @Override
  public Optional<PartidoEntity> findPartidoById(UUID idPartido) {
    return Optional.empty();
  }
}
