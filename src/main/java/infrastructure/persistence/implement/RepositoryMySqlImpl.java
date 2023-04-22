package infrastructure.persistence.implement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mysql.cj.jdbc.MysqlDataSource;
import domain.Partido;
import domain.Ronda;
import infrastructure.entities.EquipoEntity;
import infrastructure.entities.PartidoEntity;
import infrastructure.entities.RondaEntity;
import infrastructure.entities.UsuarioEntity;
import infrastructure.mapper.RondaMapper;
import infrastructure.persistence.port.IPersistence;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import javax.sql.DataSource;

public class RepositoryMySqlImpl implements IPersistence {

  private final DataSource dataSource;
  private final ObjectMapper mapper;
  private final RepositoryFileImpl repositoryFileImpl;
  private final RondaMapper rondaMapper;

  public RepositoryMySqlImpl() {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

    MysqlDataSource mysqlDataSource = new MysqlDataSource();
    mysqlDataSource.setURL("jdbc:mysql://localhost:3306/pronosticos?useUnicode=true&characterEncoding=utf-8");
    mysqlDataSource.setUser("root");
    mysqlDataSource.setPassword("root");

    this.dataSource = mysqlDataSource;
    this.mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    this.repositoryFileImpl = new RepositoryFileImpl();
    this.rondaMapper = new RondaMapper();
  }

  @Override
  public void save(Object object) {
    if (object instanceof UsuarioEntity) {
      saveUsuario((UsuarioEntity) object);
    } else if (object instanceof EquipoEntity) {
      saveEquipo((EquipoEntity) object);
    } else if (object instanceof PartidoEntity) {
      savePartido((PartidoEntity) object);
    }
  }

  private void savePartido(PartidoEntity partido) {
    String query = "INSERT INTO partidos ("
        + "id, "
        + "fecha, "
        + "ubicacion, "
        + "nombreEquipoLocal, "
        + "nombreEquipoVisitante, "
        + "golesLocal, "
        + "golesVisitante) VALUES (?, ?, ?, ?, ?, ?, ?)";
    try (Connection conn = dataSource.getConnection();
        Statement stmt = conn.createStatement()) {

      // Verificar si la tabla "partidos" existe y, si no existe, crearla
      String createTableQuery = "CREATE TABLE IF NOT EXISTS partidos ("
          + "id VARCHAR(36) NOT NULL, "
          + "fecha VARCHAR(255) NOT NULL, "
          + "ubicacion VARCHAR(255) NOT NULL, "
          + "nombreEquipoLocal VARCHAR(255) NOT NULL, "
          + "nombreEquipoVisitante VARCHAR(255) NOT NULL, "
          + "golesLocal INT NOT NULL, "
          + "golesVisitante INT NOT NULL, "
          + "PRIMARY KEY (id))";
      stmt.executeUpdate(createTableQuery);

      // Insertar los datos del partido
      PreparedStatement pstmt = conn.prepareStatement(query);
      pstmt.setString(1, partido.getId().toString());
      pstmt.setString(2, partido.getFecha().toString());
      pstmt.setString(3, partido.getUbicacion());
      pstmt.setString(4, partido.getNombreEquipoLocal());
      pstmt.setString(5, partido.getNombreEquipoVisitante());
      pstmt.setInt(6, partido.getGolesLocal());
      pstmt.setInt(7, partido.getGolesVisitante());
      pstmt.executeUpdate();
      pstmt.close();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private void saveEquipo(EquipoEntity equipo) {
    String query = "INSERT INTO equipos (id, nombre, ciudadOrigen, partidosLocal, partidosVisitante) VALUES (?, ?, ?, ?, ?)";
    try (Connection conn = dataSource.getConnection();
        Statement stmt = conn.createStatement()) {

      // Verificar si la tabla "equipos" existe y, si no existe, crearla
      String createTableQuery = "CREATE TABLE IF NOT EXISTS equipos ("
          + "id VARCHAR(36) NOT NULL, "
          + "nombre VARCHAR(255) NOT NULL, "
          + "ciudadOrigen VARCHAR(255) NOT NULL, "
          + "partidosLocal VARCHAR(255) NOT NULL, "
          + "partidosVisitante VARCHAR(255) NOT NULL, "
          + "PRIMARY KEY (id))";
      stmt.executeUpdate(createTableQuery);

      String partidosLocal = mapper.writeValueAsString(equipo.getPartidosLocal());
      String partidosVisitante = mapper.writeValueAsString(equipo.getPartidosVisitante());
      // Insertar los datos del equipo
      PreparedStatement pstmt = conn.prepareStatement(query);
      pstmt.setString(1, equipo.getId().toString());
      pstmt.setString(2, equipo.getNombre());
      pstmt.setString(3, equipo.getCiudadOrigen());
      pstmt.setString(4, partidosLocal);
      pstmt.setString(5, partidosVisitante);
      pstmt.executeUpdate();
      pstmt.close();

    } catch (SQLException e) {
      e.printStackTrace();
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  private void saveUsuario(UsuarioEntity usuario) {
    String query = "INSERT INTO usuarios (id, nick, email, password, apuestas) VALUES (?, ?, ?, ?, ?)";
    try (Connection conn = dataSource.getConnection();
        Statement stmt = conn.createStatement()) {

      // Verificar si la tabla "usuario" existe y, si no existe, crearla
      String createTableQuery = "CREATE TABLE IF NOT EXISTS usuarios ("
          + "id VARCHAR(36) NOT NULL, "
          + "nick VARCHAR(255) NOT NULL, "
          + "email VARCHAR(255) NOT NULL, "
          + "password VARCHAR(255) NOT NULL, "
          + "apuestas VARCHAR(255) NOT NULL, "
          + "PRIMARY KEY (id))";
      stmt.executeUpdate(createTableQuery);

      String apuestas = mapper.writeValueAsString(usuario.getApuestas());

      // Insertar los datos del usuario
      PreparedStatement pstmt = conn.prepareStatement(query);
      pstmt.setString(1, usuario.getId().toString());
      pstmt.setString(2, usuario.getNick());
      pstmt.setString(3, usuario.getEmail());
      pstmt.setString(4, usuario.getPassword());
      pstmt.setString(5, apuestas);
      pstmt.executeUpdate();
      pstmt.close();

    } catch (SQLException e) {
      e.printStackTrace();
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void saveAll(List<EquipoEntity> equipos) {
    equipos.forEach(this::save);
  }

  @Override
  public List<EquipoEntity> getAllEquipos() {
    List<EquipoEntity> equipos = new ArrayList<>();
    String query = "SELECT * FROM equipos";
    try (Connection conn = dataSource.getConnection();
        Statement stmt = conn.createStatement()) {
      ResultSet rs = stmt.executeQuery(query);
      while (rs.next()) {
        String id = rs.getString("id");
        String nombre = rs.getString("nombre");
        String ciudadOrigen = rs.getString("ciudadOrigen");
        String partidosLocal = rs.getString("partidosLocal");
        String partidosVisitante = rs.getString("partidosVisitante");

        //  esta línea está deserializando la cadena JSON partidosLocal en un objeto Set<PartidoEntity> utilizando Jackson.
        Set<PartidoEntity> partidosLocalSet = mapper.readValue(partidosLocal, new TypeReference<Set<PartidoEntity>>() {
        });
        Set<PartidoEntity> partidosVisitanteSet = mapper.readValue(partidosVisitante, new TypeReference<Set<PartidoEntity>>() {
        });
        equipos.add(new EquipoEntity(UUID.fromString(id), nombre, ciudadOrigen, new ArrayList<>(partidosLocalSet), new ArrayList<>(partidosVisitanteSet)));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
    return equipos;
  }


  @Override
  public void saveRonda(Ronda ronda) {
    RondaEntity rondaEntity = rondaMapper.toEntity(ronda);
    String query = "INSERT INTO rondas (id, nombre, partidos) VALUES (?, ?, ?)";
    try (Connection conn = dataSource.getConnection();
        Statement stmt = conn.createStatement()) {

      // Verificar si la tabla "rondas" existe y, si no existe, crearla
      String createTableQuery = "CREATE TABLE IF NOT EXISTS rondas ("
          + "id VARCHAR(36) NOT NULL, "
          + "nombre VARCHAR(255) NOT NULL, "
          + "partidos BLOB NOT NULL, "
          + "PRIMARY KEY (id))";
      stmt.executeUpdate(createTableQuery);

      // Insertar los datos de la ronda
      PreparedStatement pstmt = conn.prepareStatement(query);
      pstmt.setString(1, String.valueOf(rondaEntity.getId()));
      pstmt.setString(2, String.valueOf(rondaEntity.getNumeroRonda()));
      byte[] partidosBytes = mapper.writeValueAsBytes(rondaEntity.getPartidos());
      ByteArrayInputStream bais = new ByteArrayInputStream(partidosBytes);
      pstmt.setBinaryStream(3, bais, partidosBytes.length);
      pstmt.executeUpdate();
      pstmt.close();

    } catch (SQLException e) {
      e.printStackTrace();
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Ronda findRondaByNumero(int numeroRonda) {
    String query = "SELECT * FROM rondas WHERE nombre = ?";
    try (Connection conn = dataSource.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(query)) {
      pstmt.setInt(1, numeroRonda);
      ResultSet rs = pstmt.executeQuery();
      if (rs.next()) {
        String id = rs.getString("id");
        String nombreRonda = rs.getString("nombre");
        Blob partidosBlob = rs.getBlob("partidos");
        byte[] partidosBytes = partidosBlob.getBytes(1, (int) partidosBlob.length());
        Set<PartidoEntity> partidos = mapper.readValue(partidosBytes, new TypeReference<Set<PartidoEntity>>() {
        });
        return rondaMapper.toDomain(UUID.fromString(id), nombreRonda, partidos);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return null;
  }


  public Optional<EquipoEntity> findEquipoByNombre(String nombre) {
    Optional<EquipoEntity> equipo = Optional.empty();
    String query = "SELECT * FROM equipos WHERE nombre = ?";
    try (Connection conn = dataSource.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(query)) {
      pstmt.setString(1, nombre);
      ResultSet rs = pstmt.executeQuery();
      if (rs.next()) {
        String id = rs.getString("id");
        String nombreEquipo = rs.getString("nombre");
        String ciudadOrigen = rs.getString("ciudadOrigen");
        String partidosLocal = rs.getString("partidosLocal");
        String partidosVisitante = rs.getString("partidosVisitante");

        Set<PartidoEntity> partidosLocalSet = mapper.readValue(partidosLocal, new TypeReference<Set<PartidoEntity>>() {
        });
        Set<PartidoEntity> partidosVisitanteSet = mapper.readValue(partidosVisitante, new TypeReference<Set<PartidoEntity>>() {
        });

        List<PartidoEntity> partidosLocalList = new ArrayList<>(partidosLocalSet);
        List<PartidoEntity> partidosVisitanteList = new ArrayList<>(partidosVisitanteSet);

        return Optional.of(new EquipoEntity(UUID.fromString(id), nombreEquipo, ciudadOrigen, partidosLocalList, partidosVisitanteList));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
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

  @Override
  public void readUsuarios() {

  }

  @Override
  public List<EquipoEntity> getAllEquiposCsv() {
    return repositoryFileImpl.getAllEquiposCsv();
  }


}
