package test;

import main.model.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void openAccount() {
        assertTrue(getTestCustomer().openAccount());
    }

    @Test
    void accountAlreadyOpened(){
        Customer customer = getTestCustomer();
        customer.openAccount();
        assertFalse(customer.openAccount());
    }

    @Test
    void closeAccount() {
        Customer customer = getTestCustomer();
        customer.openAccount();
        assertTrue(customer.closeAccount());
    }

    @Test
    void accountDoesNotExist1() {
        Customer customer = getTestCustomer();
        assertFalse(customer.closeAccount());
    }
    @Test
    void accountDoesNotExist2() {
        Customer customer = getTestCustomer();
        customer.openAccount();
        customer.closeAccount();
        assertFalse(customer.closeAccount());
    }

    @Test
    void fullName() {
        String name = "Акакий";
        String lastName = "Башмачкин";
        Customer customer = new Customer(name, lastName);
        assertEquals(lastName + " " + name, customer.fullName());
    }

    @Test
    void successAdd(){
        Customer customer =  getTestCustomer();
        customer.openAccount();
        assertTrue(customer.addMoneyToCurrentAccount(137.23));
        assertEquals(137.23,customer.getBalance());
        customer.addMoneyToCurrentAccount(100);
        assertEquals(237.23,customer.getBalance());
    }

    @Test
    void addZeroAmount(){
        Customer customer =  getTestCustomer();
        customer.openAccount();
        assertFalse(customer.addMoneyToCurrentAccount(0));
    }

    @Test
    void addNegativeAmount(){
        Customer customer =  getTestCustomer();
        customer.openAccount();
        assertFalse(customer.addMoneyToCurrentAccount(-1));
    }

    @Test
    void successWithdraw(){
        Customer customer =  getTestCustomer();
        customer.openAccount();
        customer.addMoneyToCurrentAccount(100);
        assertTrue(customer.withdrawFromCurrentAccount(50.5));
        assertEquals(49.5,customer.getBalance());
        customer.withdrawFromCurrentAccount(10);
        assertEquals(39.5, customer.getBalance());
    }


    @Test
    void withdrawNegativeAmount(){
        Customer customer =  getTestCustomer();
        customer.openAccount();
        customer.addMoneyToCurrentAccount(100);
        assertFalse(customer.withdrawFromCurrentAccount(-1));
    }

    @Test
    void withdrawZeroAmount(){
        Customer customer =  getTestCustomer();
        customer.openAccount();
        customer.addMoneyToCurrentAccount(100);
        assertFalse(customer.withdrawFromCurrentAccount(0));
    }

    @Test
    void notEnoughMoney(){
        Customer customer =  getTestCustomer();
        customer.openAccount();
        customer.addMoneyToCurrentAccount(100);
        assertFalse(customer.withdrawFromCurrentAccount(200));
    }

    private Customer getTestCustomer(){
        return new Customer("Акакий", "Башмачкин");
    }
}