package infrastructure.database.persistence.implement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mysql.cj.jdbc.MysqlDataSource;
import domain.Partido;
import domain.Ronda;
import infrastructure.database.entities.ApuestaEntity;
import infrastructure.database.entities.EquipoEntity;
import infrastructure.database.entities.PartidoEntity;
import infrastructure.database.entities.RondaEntity;
import infrastructure.database.entities.UsuarioEntity;
import infrastructure.mapper.RondaMapper;
import infrastructure.database.persistence.port.IPersistence;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.*;
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
    } else if (object instanceof ApuestaEntity) {
      saveApuesta((ApuestaEntity) object);
    } else if (object instanceof RondaEntity) {
//      saveRonda((RondaEntity) object);
    }
      
    }

  private void saveApuesta(ApuestaEntity apuestaEntity) {
    String query = "INSERT INTO apuestas ("
        + "id, "
        + "idUsuario, "
        + "idPartido, "
        + "golesLocalPronosticados, "
        + "golesVisitantePronosticados, "
        + "montoApostado) VALUES (?, ?, ?, ?, ?, ?)";
    try (Connection conn = dataSource.getConnection();
        Statement stmt = conn.createStatement()) {

      // Verificar si la tabla "apuestas" existe y, si no existe, crearla
      String createTableQuery = "CREATE TABLE IF NOT EXISTS apuestas ("
          + "id VARCHAR(36) NOT NULL, "
          + "idUsuario VARCHAR(36) NOT NULL, "
          + "idPartido VARCHAR(36) NOT NULL, "
          + "golesLocalPronosticados INT NOT NULL, "
          + "golesVisitantePronosticados INT NOT NULL, "
          + "montoApostado DOUBLE NOT NULL, "
          + "PRIMARY KEY (id))";
      stmt.executeUpdate(createTableQuery);

      // Insertar los datos del partido
      PreparedStatement preparedStatement = conn.prepareStatement(query);
      preparedStatement.setString(1, apuestaEntity.getId().toString());
      preparedStatement.setString(2, apuestaEntity.getIdUsuario().toString());
      preparedStatement.setString(3, apuestaEntity.getIdPartido().toString());
      preparedStatement.setInt(4, apuestaEntity.getGolesLocalPronosticados());
      preparedStatement.setInt(5, apuestaEntity.getGolesVisitantePronosticados());
      preparedStatement.setDouble(6, apuestaEntity.getMontoApostado());
      preparedStatement.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
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

        //  esta línea está deserializando partidosLocal en un objeto Set<PartidoEntity> utilizando Jackson.
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

  @Override
  public Optional<UsuarioEntity> findByUsuarioEmail(String email) {
    String query = "SELECT * FROM usuarios WHERE email = ?";
    try (Connection conn = dataSource.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(query)) {
      pstmt.setString(1, email);
      ResultSet rs = pstmt.executeQuery();
      if (rs.next()) {
        String id = rs.getString("id");
        String nick = rs.getString("nick");
        String emailUsuario = rs.getString("email");
        String password = rs.getString("password");
        String apuestas = rs.getString("apuestas");

        Set<ApuestaEntity> apuestasSet = mapper.readValue(apuestas, new TypeReference<Set<ApuestaEntity>>() {
        });
        return Optional.of(new UsuarioEntity(UUID.fromString(id), nick, emailUsuario, password, new ArrayList<>(apuestasSet)));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
    return Optional.empty();
  }

  @Override
  public Optional<List<ApuestaEntity>> findApuestasByUsuarioId(UUID id) {
    List<ApuestaEntity> apuestas = new ArrayList<>();
    String query = "SELECT * FROM apuestas WHERE idUsuario = ?";
    try (Connection conn = dataSource.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(query)) {
      pstmt.setString(1, String.valueOf(id));
      ResultSet rs = pstmt.executeQuery();
      if (rs.next()) {
        String idApuesta = rs.getString("id");
        String idUsuario = rs.getString("idUsuario");
        String idPartido = rs.getString("idPartido");
        String golesLocalPronosticados = rs.getString("golesLocalPronosticados");
        String golesVisitantePronosticados = rs.getString("golesVisitantePronosticados");
        String montoApostado = rs.getString("montoApostado");

        apuestas.add(new ApuestaEntity(UUID.fromString(idApuesta), UUID.fromString(idUsuario), UUID.fromString(idPartido), Integer.parseInt(golesLocalPronosticados), Integer.parseInt(golesVisitantePronosticados), Double.parseDouble(montoApostado)));
        return Optional.of(apuestas);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  @Override
  public Optional<UsuarioEntity> findUsuarioById(UUID idUsuario) {
    String query = "SELECT * FROM usuarios WHERE id = ?";
    try (Connection conn = dataSource.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(query)) {
      pstmt.setString(1, String.valueOf(idUsuario));
      ResultSet rs = pstmt.executeQuery();
      if (rs.next()) {
        String id = rs.getString("id");
        String nick = rs.getString("nick");
        String emailUsuario = rs.getString("email");
        String password = rs.getString("password");
        String apuestas = rs.getString("apuestas");

        Set<ApuestaEntity> apuestasSet = mapper.readValue(apuestas, new TypeReference<Set<ApuestaEntity>>() {
        });
        return Optional.of(new UsuarioEntity(UUID.fromString(id), nick, emailUsuario, password, new ArrayList<>(apuestasSet)));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
    return Optional.empty();
  }

  @Override
  public List<RondaEntity> getAllRondas() {
    List<RondaEntity> rondas = new ArrayList<>();
    String query = "SELECT * FROM rondas";
    try (Connection conn = dataSource.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(query)) {
      ResultSet rs = pstmt.executeQuery();
      while (rs.next()) {
        String id = rs.getString("id");
        String nombreRonda = rs.getString("nombre");
        Blob partidosBlob = rs.getBlob("partidos");
        byte[] partidosBytes = partidosBlob.getBytes(1, (int) partidosBlob.length());
        List<PartidoEntity> partidos = mapper.readValue(partidosBytes, new TypeReference<List<PartidoEntity>>() {
        });

        rondas.add(new RondaEntity(UUID.fromString(id), Integer.parseInt(nombreRonda), partidos));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return rondas;
  }

  @Override
  public Partido findPartidoByFecha(LocalDate fechaPartido) {
    List<Partido> partidosPorRonda = findRondaByNumero(1).getPartidos();
    return partidosPorRonda.stream().filter(partido -> partido.getFecha().isEqual(fechaPartido)).findFirst().get();
  }

  @Override
  public void updateUser(UsuarioEntity usuarioEntity) {
    String query = "UPDATE usuarios SET nick = ?, email = ?, password = ?, apuestas = ? WHERE id = ?";
    try (Connection conn = dataSource.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(query)) {
      pstmt.setString(1, usuarioEntity.getNick());
      pstmt.setString(2, usuarioEntity.getEmail());
      pstmt.setString(3, usuarioEntity.getPassword());
      pstmt.setString(4, mapper.writeValueAsString(usuarioEntity.getApuestas()));
      pstmt.setString(5, String.valueOf(usuarioEntity.getId()));
      pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
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
    Optional<UsuarioEntity> usuario = Optional.empty();
    String query = "SELECT * FROM usuarios WHERE email = ?";
    try (Connection conn = dataSource.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(query)) {
      pstmt.setString(1, email);
      ResultSet rs = pstmt.executeQuery();
      if (rs.next()) {
        String id = rs.getString("id");
        String nick = rs.getString("nick");
        String emailUsuario = rs.getString("email");
        String password = rs.getString("password");
        String apuestas = rs.getString("apuestas");

        Set<ApuestaEntity> apuestasSet = mapper.readValue(apuestas, new TypeReference<Set<ApuestaEntity>>() {
        });
        List<ApuestaEntity> apuestasList = new ArrayList<>(apuestasSet);

        return Optional.of(new UsuarioEntity(UUID.fromString(id), nick, emailUsuario, password, apuestasList));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
    return Optional.empty();
  }

  @Override
  public Optional<PartidoEntity> findPartidoById(UUID idPartido) {
    Optional<PartidoEntity> partido = Optional.empty();
    String query = "SELECT * FROM partidos WHERE id = ?";
    try (Connection conn = dataSource.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(query)) {
      pstmt.setString(1, String.valueOf(idPartido));
      ResultSet rs = pstmt.executeQuery();
      if (rs.next()) {
        String id = rs.getString("id");
        String fecha = rs.getString("fecha");
        String ubicacion = rs.getString("ubicacion");
        String equipoLocal = rs.getString("nombreEquipoLocal");
        String equipoVisitante = rs.getString("nombreEquipoVisitante");
        String golesLocal = rs.getString("golesLocal");
        String golesVisitante = rs.getString("golesVisitante");


        return Optional.of(new PartidoEntity(UUID.fromString(id), LocalDate.parse(fecha), ubicacion, equipoLocal, equipoVisitante, Integer.parseInt(golesLocal), Integer.parseInt(golesVisitante)));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
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
