package com.datascience.shop.service;

import com.datascience.shop.dao.DaoException;
import com.datascience.shop.entity.Item;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class ItemServiceTest {
    private ItemDao itemDao = mock(ItemDao.class);
    ItemService itemService;
    Item testItem;


    @Before
    public void setUp() throws Exception {
        testItem = new Item(15, "dfdfds", "dfsdfsdf", "fgfdgd", LocalDate.of(2020, 05, 17), LocalDate.of(2022, 05, 17), 1500.12);
        itemService = new ItemService(itemDao);

    }

    @Test
    public void testDelete_ddf() {
        Exception exception = Assert.assertThrows(DaoException.class, () -> {
            itemService.delete(null);
        });
        //todo - почему не работает?
        String expectedMessage = "fff";
        String actualMessage = exception.getMessage();
        Assert.assertTrue(actualMessage.contains(expectedMessage));


    }

    @Test
    public void testDelete1_ddf() {
        Exception exception = Assert.assertThrows(ServiceException.class, () -> {
            itemService.delete1(null);
        });
        String expectedMessage = "fff";
        String actualMessage = exception.getMessage();
        Assert.assertTrue(actualMessage.contains(expectedMessage));
    }

}