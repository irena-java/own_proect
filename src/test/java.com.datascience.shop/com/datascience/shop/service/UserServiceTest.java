package com.datascience.shop.service;

import com.datascience.shop.connection.pool.Context;
import com.datascience.shop.connection.pool.PostgresUtils;

import com.datascience.shop.dao.impl.DaoException;
import com.datascience.shop.dao.impl.UserDaoImpl;
import com.datascience.shop.entity.User;
import com.datascience.shop.entity.UserRole;
import com.datascience.shop.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    private UserDaoImpl userDao = mock(UserDaoImpl.class);
    private com.datascience.shop.connection.pool.PostgresUtils postgresUtils =
            mock(PostgresUtils.class);
    private UserServiceImpl userService;
    User testUser1, testUser2, testUser3;
    List<User> testUsers1, testUsers2;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    @Before
    public void init() {
        userService = new UserServiceImpl();
        testUser1 = new User(151, "user-1", UserRole.CLIENT, "0065842", "Ucr", "12-56-56", "passw1");
        testUser2 = new User(5, "user-2", UserRole.ADMIN, "0065842", "Ucr", "12-56-56", "passw1");
        testUser3 = new User(75, "user-3", UserRole.MANAGER, "0065842", "Ucr", "12-56-56", "passw1");
        testUsers1 = new ArrayList<>();
        testUsers1.add(testUser1);
        testUsers1.add(testUser2);
        testUsers1.add(testUser3);
        testUsers2 = Arrays.asList(
                new User(null, "user1", UserRole.MANAGER, "0065842", "Ucr", "12-56-56", "passw1"),
                new User(15, "user2", UserRole.MANAGER, "0065842", "Ucr", "12-56-56", "passw1"),
                new User(18, "user3", UserRole.MANAGER, "0065842", "Ucr", "12-56-56", "passw1"),
                new User(88, "user4", UserRole.MANAGER, "0065842", "Ucr", "12-56-56", "passw1"));
    }

    @Test
    public void findAll_returnCorrectList() throws ServiceException, DaoException {
        when(userDao.findAll()).thenReturn(testUsers1);
        List<User> users = userService.findAll();
        utilsConfirmUsers1EqualsUser2(users.get(0), testUsers1.get(0));
        utilsConfirmUsers1EqualsUser2(users.get(1), testUsers1.get(1));
        utilsConfirmUsers1EqualsUser2(users.get(2), testUsers1.get(2));
    }

    public void utilsConfirmUsers1EqualsUser2(User user1, User user2) {
        Assert.assertEquals(user1.getId(), user2.getId());
        Assert.assertEquals(user1.getName(), user2.getName());
        Assert.assertEquals(user1.getUserRole(), user2.getUserRole());
        Assert.assertEquals(user1.getPassword(), user2.getPassword());
        Assert.assertEquals(user1.getClientInn(), user2.getClientInn());
        Assert.assertEquals(user1.getCountry(), user2.getCountry());
        Assert.assertEquals(user1.getContactInfo(), user2.getContactInfo());
    }

    @Test
    public void findAll_returnNotNullList() throws DaoException, ServiceException {
        when(userDao.findAll()).thenReturn(testUsers1);
        List<User> users = userService.findAll();
        Assert.assertNotNull(users);
    }

    @Test
    public void findAll_returnNullList() throws DaoException, ServiceException {
        when(userDao.findAll()).thenReturn(null);
        List<User> users = userService.findAll();
        Assert.assertNull(users);
    }

    @Test
    public void delete_hasMethodBeenCalledOneTimeWithSetParameters() throws ServiceException, DaoException {
        Connection connection = Context.get().get();
        doNothing().when(userDao).delete(isA(User.class));
        userService.delete(testUser1);
        verify(userDao, times(1)).delete(testUser1);
        verify(userDao, times(1)).delete(userArgumentCaptor.capture());
    }

    @Test
    public void delete_checkExpectedException() {
      Connection connection = Context.get().get();
    }

    @Test
    public void findById_returnCorrectUserById() throws ServiceException {
        when(userService.findById(eq(151))).thenReturn(testUser1);
        when(userService.findById(eq(5))).thenReturn(testUser2);
        when(userService.findById(eq(75))).thenReturn(testUser3);
        User actualUser1 = userService.findById(151);
        User actualUser2 = userService.findById(5);
        User actualUser3 = userService.findById(75);
        utilsConfirmUsers1EqualsUser2(testUser1, actualUser1);
        utilsConfirmUsers1EqualsUser2(testUser2, actualUser2);
        utilsConfirmUsers1EqualsUser2(testUser3, actualUser3);
    }
}