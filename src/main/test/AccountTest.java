package test;

import main.model.Account;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    @Test
    void successAdd(){
        Account account = new Account();
        assertTrue(account.add(137.23));
        assertEquals(137.23,account.getBalance());
        account.add(100);
        assertEquals(237.23,account.getBalance());
    }

    @Test
    void addZeroAmount(){
        Account account = new Account();
        assertFalse(account.add(0));
    }

    @Test
    void addNegativeAmount(){
        Account account = new Account();
        assertFalse(account.add(-1));
    }

    @Test
    void successWithdraw(){
        Account account = new Account();
        account.add(100);
        assertTrue(account.withdraw(50.5));
        assertEquals(49.5,account.getBalance());
        account.withdraw(10);
        assertEquals(39.5, account.getBalance());
    }


    @Test
    void withdrawNegativeAmount(){
        Account account = new Account();
        account.add(100);
        assertFalse(account.withdraw(-1));
    }

    @Test
    void withdrawZeroAmount(){
        Account account = new Account();
        account.add(100);
        assertFalse(account.withdraw(0));
    }

    @Test
    void notEnoughMoney(){
        Account account = new Account();
        account.add(100);
        assertFalse(account.withdraw(200));
    }

}
