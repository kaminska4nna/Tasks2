package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.facade.TrelloFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @CrossOrigin("*")
    @RestController
    @RequestMapping("/v1/trello")
    public class TrelloController {

        @Autowired
        private TrelloFacade trelloFacade;

        @RequestMapping(method = RequestMethod.GET, value = "/boards")

        public List<TrelloBoardDto> getTrelloBoards() {
            return trelloFacade.fetchTrelloBoards();



          /*  List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();
            trelloBoards.stream()
                    .filter(trelloBoardDto -> !trelloBoardDto.getId().isEmpty() && trelloBoardDto.getName().contains("kodilla"))
                    .forEach(trelloBoardDto -> {

                        System.out.println(trelloBoardDto.getName() + " - " + trelloBoardDto.getId());

                        System.out.println("This board contains lists: ");

                        trelloBoardDto.getLists().forEach(trelloList ->
                                System.out.println(trelloList.getName() + " - " + trelloList.getId() + " - " + trelloList.isClosed()));
                    });*/
        }



        @RequestMapping(method = RequestMethod.POST, value = "/cards")
        public CreatedTrelloCardDto createdTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {

            return trelloFacade.createdCard(trelloCardDto);
            }


        }

