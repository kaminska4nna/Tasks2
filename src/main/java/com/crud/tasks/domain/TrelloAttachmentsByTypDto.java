package com.crud.tasks.domain;

import com.crud.tasks.controller.TrelloController;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrelloAttachmentsByTypDto {
    @JsonProperty("trello")
    private TrelloDto trelloDto;

}
