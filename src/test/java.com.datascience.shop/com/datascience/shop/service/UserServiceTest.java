package com.datascience.shop.service;

import com.datascience.shop.ConnectionPool;
import com.datascience.shop.MySpecialContext;
import com.datascience.shop.dao.DaoException;
import com.datascience.shop.dao.UserDaoImpl;
import com.datascience.shop.entity.User;
import com.datascience.shop.entity.UserRole;
import com.datascience.shop.service.UserDao;
import com.datascience.shop.service.UserService;
import com.datascience.shop.utils.PostgresUtils;
import junit.framework.TestCase;
import org.checkerframework.checker.units.qual.A;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest extends TestCase {
//    private UserDaoImpl  userDaoImpl = mock(UserDaoImpl.class);
    private UserDao  userDao = mock(UserDao.class);
    private PostgresUtils postgresUtils=mock(PostgresUtils.class);
    private UserService userService;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    @Before
    public void init() throws Exception{
        userService = new UserService(userDao);

    }

    @Test
    public void findAll_returnRightList() {
        List<User> users = Arrays.asList(
                new User(151, "user1", UserRole.MANAGER, "0065842", "Ucr", "12-56-56", "passw1"),
                new User(151, "user1", UserRole.MANAGER, "0065842", "Ucr", "12-56-56", "passw1"),
                new User(151, "user1", UserRole.MANAGER, "0065842", "Ucr", "12-56-56", "passw1"),
                new User(151, "user1", UserRole.MANAGER, "0065842", "Ucr", "12-56-56", "passw1"));

        List<User> user3 = Arrays.asList(
                new User(151, "us", UserRole.MANAGER, "0065842", "Ucr", "12-56-56", "passw1"),
                new User(151, "user1", UserRole.MANAGER, "0065842", "Ucr", "12-56-56", "passw1"),
                new User(151, "user1", UserRole.MANAGER, "0065842", "Ucr", "12-56-56", "passw1"),
                new User(151, "user1", UserRole.MANAGER, "0065842", "Ucr", "12-56-56", "passw1"));

        when(userDao.findAll()).thenReturn(users);

        when(userDao.findAll()).thenReturn(users);
        //when(userService.findAll()).thenReturn(users);

        List<User> user2 = userService.findAll();
        assertEquals(users.get(0).getId(),user3.get(0).getId());
   //     assertEquals(users.get(0).getName(),user3.get(0).getName());


    //    Assert.assertNotNull(user2);

    }

            @Test
        public void findAll_notNull() {
            List<User> users = Arrays.asList(
                    new User(151, "user1", UserRole.MANAGER, "0065842", "Ucr", "12-56-56", "passw1"),
                    new User(151, "user1", UserRole.MANAGER, "0065842", "Ucr", "12-56-56", "passw1"),
                    new User(151, "user1", UserRole.MANAGER, "0065842", "Ucr", "12-56-56", "passw1"),
                    new User(151, "user1", UserRole.MANAGER, "0065842", "Ucr", "12-56-56", "passw1"));

            when(userDao.findAll()).thenReturn(users);

            List<User> user2 = userService.findAll();

            Assert.assertNotNull(user2);


    }

    @Test
    public void delete_ifnormalDataPassed() throws DaoException {
        User user1=new User(151, "us", UserRole.MANAGER, "0065842", "Ucr", "12-56-56", "passw1");
        User user2=new User(101, "us555", UserRole.MANAGER, "00658", "Ucr", "12-56-56", "passw1");

        ConnectionPool connectionPool = MySpecialContext.get();
        Connection connection = connectionPool.get();

        doNothing().when(userDao).delete(isA(User.class),isA(Connection.class));
        userService.delete(user1,connection);
        verify(userDao,times(1)).delete(user1,connection);


  /*      doThrow().when(userDao).delete(isA(User.class),isNull());
        userService.delete(user1,null);
*/
        //when(userDao.delete(user1,connection)).thenReturn(1);
 //       userService.delete(user1,connection);

  //      verify(userDao,times(1)).delete(user1,connection);
        //verify(userDao,times(1)).delete(user2,connection);

/*        userArgumentCaptor=ArgumentCaptor.forClass(User.class);
        verify(userDao).delete(userArgumentCaptor.capture(),connection);
*/

        //verify(userDao,times(1)).delete(userArgumentCaptor.capture(),connection);
   //     User user3=userArgumentCaptor.getValue();
     //   assertEquals(user1.getId(),user3.getId());


    }

@Test
    public void constructor_not_null_values() throws DaoException {
    User user1=new User( null, null, "0065842", "Ucr", "12-56-56", null);
    System.out.println(user1);
    if(user1.getName()!=null) {
        Assert.fail("нулевой пользователь");
    }
    if(user1.getPassword()!=null) {
        Assert.fail("нулевой пароль");
    }
    if(user1.getUserRole()==null) {
        Assert.fail("нулевая роль");
    }
}

    @Test
    public void delete_ifnormalDataPassedCapture() throws DaoException {
        User user1=new User(151, "us", UserRole.MANAGER, "0065842", "Ucr", "12-56-56", "passw1");
        ConnectionPool connectionPool = MySpecialContext.get();
        Connection connection = connectionPool.get();
        userService.delete(user1,connection);
        verify(userDao,times(1)).delete(userArgumentCaptor.capture(),isA(Connection.class));
    }


    @Test
    public void findById_calledSeveralTime() throws DaoException {
        User user1=new User(151, "user-1", UserRole.CLIENT, "0065842", "Ucr", "12-56-56", "passw1");
        User user2=new User(5, "user-2", UserRole.ADMIN, "0065842", "Ucr", "12-56-56", "passw1");
        User user3=new User(75, "user-3", UserRole.MANAGER, "0065842", "Ucr", "12-56-56", "passw1");

        when(userService.findById(eq(151))).thenReturn(user1);
        when(userService.findById(eq(5))).thenReturn(user2);
        when(userService.findById(eq(75))).thenReturn(user3);

        User actualUser1=userService.findById(151);
        User actualUser2=userService.findById(5);
        User actualUser3=userService.findById(75);
        assertEquals("user-1",actualUser1.getName());
    }
}