package test;

import main.model.Account;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    @Test
    void add() {
//        given
        Account account = new Account();

//        when
        boolean isSuccess = account.add(137.23);

//        then
        assertTrue(isSuccess);
    }

    @Test
    void addZeroAmount() {
//        given
        Account account = new Account();

//        when
        boolean isSuccess = account.add(0);

//        then
        assertFalse(isSuccess);
    }

    @Test
    void addNegativeAmount() {
//        given
        Account account = new Account();

//        when
        boolean isSuccess = account.add(-1);

//        then
        assertFalse(isSuccess);
    }

    @Test
    void Withdraw() {
//        given
        double addAmount = 100;
        double withdrawAmount = 50.5;
        Account account = new Account();
        account.add(addAmount);

//        when
        boolean isSuccess = account.withdraw(withdrawAmount);

//        then
        assertTrue(isSuccess);
        assertEquals(addAmount - withdrawAmount, account.getBalance());
    }


    @Test
    void withdrawNegativeAmount() {
//        given
        Account account = new Account();
        account.add(100);

//        when
        boolean isSuccess = account.withdraw(-1);

//        then
        assertFalse(isSuccess);
    }

    @Test
    void withdrawZeroAmount() {
//        given
        Account account = new Account();
        account.add(100);

//        when
        boolean isSuccess = account.withdraw(0);

//        then
        assertFalse(isSuccess);
    }

    @Test
    void withdrawToMuchMoney() {
//        given
        double addAmount = 100;
        double withdrawAmount = 200;
        Account account = new Account();
        account.add(addAmount);

//        when
        boolean isSuccess =  account.withdraw(withdrawAmount);

//        then
        assertFalse(isSuccess);
    }

}
