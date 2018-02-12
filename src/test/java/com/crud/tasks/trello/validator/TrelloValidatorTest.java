package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloValidatorTest {

    @Autowired
    private TrelloValidator trelloValidator;

    @Test
    public void validateTrelloBoardsTest(){
        //Given
        TrelloList trelloList1 = new TrelloList("1", "test1", true);
        TrelloList trelloList2 = new TrelloList("2", "test2",false);
        TrelloList trelloList3 = new TrelloList("3", "test3",true);
        TrelloList trelloList4 = new TrelloList("4", "test4", false);
        TrelloList trelloList5 = new TrelloList("5","test5",false);

        List<TrelloList> trelloList = new ArrayList<>();
        trelloList.add(trelloList1);
        trelloList.add(trelloList2);
        trelloList.add(trelloList3);
        trelloList.add(trelloList4);
        trelloList.add(trelloList5);

        TrelloBoard trelloBoard = new TrelloBoard("1", "TestBoard", trelloList);

        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard);

        //When
        List<TrelloBoard> fillteredBoard = trelloValidator.validateTrelloBoards(trelloBoards);

        //Then
        assertEquals(1, fillteredBoard.size());
        assertEquals("TestBoard", fillteredBoard.get(0).getName());


    }
}
