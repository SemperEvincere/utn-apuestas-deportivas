package domain;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements Serializable {

  private UUID id;
  private String nick;
  private String email;
  private String password;
  private List<Apuesta> apuestas;

}
