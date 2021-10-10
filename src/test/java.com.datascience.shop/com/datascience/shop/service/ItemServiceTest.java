package com.datascience.shop.service;

import com.datascience.shop.dao.impl.ItemDaoImpl;
import com.datascience.shop.entity.Item;
import com.datascience.shop.service.impl.ItemServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class ItemServiceTest {
    private ItemDaoImpl itemDao = mock(ItemDaoImpl.class);
    ItemService itemService;
    Item testItem;

    @Before
    public void setUp() throws Exception {
        testItem = new Item(15, "dfdfds", "dfsdfsdf", "fgfdgd", LocalDate.of(2020, 05, 17), LocalDate.of(2022, 05, 17), 1500.12);
        itemService = new ItemServiceImpl();
    }

    @Test
    public void testDelete1_ddf() {
        Exception exception = Assert.assertThrows(ServiceException.class, () -> {
            itemService.delete(null);
        });
        String expectedMessage = "Failed delete item";
        String actualMessage = exception.getMessage();
        Assert.assertTrue(actualMessage.contains(expectedMessage));
    }
}