package es.edudediegolucas.petitionweb.model;

import es.edudediegolucas.petitionweb.repository.petition.PetitionEntity;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetitionBean implements Serializable {

  private String id;
  private String data;
  private String name;
  private LocalDateTime creationTime;

  public static PetitionBean mapToPetitionBean(PetitionEntity petitionEntity) {
    return PetitionBean.builder()
        .id(petitionEntity.getId())
        .creationTime(petitionEntity.getCreationTime())
        .data(petitionEntity.getData())
        .name(petitionEntity.getName())
        .build();
  }

  public static PetitionEntity mapToPetitionEntity(PetitionBean petitionBean) {
    return PetitionEntity.builder()
        .id(petitionBean.getId())
        .name(petitionBean.getName())
        .data(petitionBean.getData())
        .creationTime(petitionBean.getCreationTime())
        .build();
  }
}
