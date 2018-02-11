package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TrelloMapperTest {

    @Test
    public void testMapToBoards() {
        //Given
        TrelloMapper trelloMapper = new TrelloMapper();

        List<TrelloListDto> trelloListDtoList = new ArrayList<>();
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        trelloBoardDtoList.add(new TrelloBoardDto("1", "new", trelloListDtoList));

        List<TrelloList> trelloLists = new ArrayList<>();
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("1", "new", trelloLists));
        //When
        List<TrelloBoard> trelloBoardList = trelloMapper.mapToBoards(trelloBoardDtoList);
        //Then
        assertEquals(1, trelloBoardList.size());
        assertEquals("new", trelloBoardList.get(0).getName());
        assertEquals("1", trelloBoardList.get(0).getId());
        assertEquals(trelloBoards.get(0).getLists(), trelloBoardList.get(0).getLists());
    }

    @Test
    public void testMapToBoardsDto() {
        //Given
        TrelloMapper trelloMapper = new TrelloMapper();

        List<TrelloList> trelloLists = new ArrayList<>();
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("1", "new", trelloLists));

        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(new TrelloBoardDto("1", "new", trelloListDtos));
        //When
        List<TrelloBoardDto> trelloBoardDtoList = trelloMapper.mapToBoardsDto(trelloBoards);

        //Then
        assertEquals(1, trelloBoardDtoList.size());
        assertEquals("1", trelloBoardDtoList.get(0).getId());
        assertEquals("new", trelloBoardDtoList.get(0).getName());
        assertEquals(trelloBoardDtoList.get(0).getLists(), trelloBoardDtos.get(0).getLists());
    }

    @Test
    public void testMapToList() {
        //Given
        TrelloMapper trelloMapper = new TrelloMapper();

        List<TrelloListDto> trelloListDtoList = new ArrayList<>();
        trelloListDtoList.add(new TrelloListDto("1", "new", false));
        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDtoList);
        //Then
        assertEquals(1, trelloLists.size());
        assertEquals("1", trelloLists.get(0).getId());
        assertEquals("new", trelloLists.get(0).getName());
        Assert.assertFalse(trelloLists.get(0).isClosed());
    }

    @Test
    public void testMapToListDto() {
        //Given
        TrelloMapper trelloMapper = new TrelloMapper();

        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1", "new", true));
        //When
        List<TrelloListDto> trelloListDtos = trelloMapper.mapToListDto(trelloLists);
        //Then
        assertEquals(1, trelloListDtos.size());
    }

    @Test
    public void testMapToCard() {
        //Given
        TrelloMapper trelloMapper = new TrelloMapper();

        TrelloCardDto trelloCardDto = new TrelloCardDto("new", "new1", "new2", "1");
        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertEquals("new", trelloCard.getName());
        assertEquals("new1", trelloCard.getDescription());
        assertEquals("new2", trelloCard.getPos());
        assertEquals("1", trelloCard.getListId());
    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloMapper trelloMapper = new TrelloMapper();

        TrelloCard trelloCard = new TrelloCard("new", "new1", "new2", "2");
        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertEquals("new", trelloCardDto.getName());
        assertEquals("new1", trelloCardDto.getDescription());
        assertEquals("new2", trelloCardDto.getPos());
        assertEquals("2", trelloCardDto.getListId());
    }
}