package es.edudediegolucas.petitionweb.repository.petition;

import es.edudediegolucas.petitionweb.repository.user.UserEntitiy;
import lombok.NonNull;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Repository
public class PetitionRepository {

  private final Map<String, PetitionEntity> mapOfPetitions = new HashMap<>();
  private final Map<String, List<PetitionEntity>> mapOfPetitionPerUser = new HashMap<>();

  public String insertPetition(@NonNull PetitionEntity petitionEntity, UserEntitiy userEntitiy) {
    var id = RandomStringUtils.randomAlphanumeric(8).toUpperCase();
    petitionEntity.toBuilder()
            .id(id)
            .creationTime(LocalDateTime.now())
            .build();
    mapOfPetitions.put(id, petitionEntity);
    final List<PetitionEntity> listPetition;
    if (mapOfPetitionPerUser.containsKey(userEntitiy.getId())) {
      listPetition = mapOfPetitionPerUser.get(userEntitiy.getId());
    } else {
      listPetition = new ArrayList<>();
    }
    listPetition.add(petitionEntity);
    mapOfPetitionPerUser.put(userEntitiy.getId(), listPetition);
    return id;
  }

  public PetitionEntity getPetitionById(@NonNull String id) {
    return mapOfPetitions.get(id);
  }

  public Set<PetitionEntity> getAll() {
    return new HashSet<>(mapOfPetitions.values());
  }

  public List<PetitionEntity> getPetitionPerUser(@NonNull String userId) {
    return !Objects.isNull(mapOfPetitionPerUser.get(userId)) ? mapOfPetitionPerUser.get(userId)
            : Collections.emptyList();
  }

}
