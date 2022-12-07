package es.edudediegolucas.petitionweb.repository.petition;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder(toBuilder = true)
public class PetitionEntity {

  String id;
  LocalDateTime creationTime;
  String data;
  String name;
}
