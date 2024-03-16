package es.edudediegolucas.petitionweb.repository.petition;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class PetitionEntity {

  String id;
  LocalDateTime creationTime;
  String data;
  String name;
}
