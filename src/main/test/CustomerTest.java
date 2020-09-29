package test;

import main.model.Customer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void openAccount() {
//        given
        Customer customer = getTestCustomer();
//        when
        boolean isSuccess = customer.openAccount();
//        then
        assertTrue(isSuccess);
    }

    @Test
    void openAccountWhenOneAlreadyExist(){
//        given
        Customer customer = getTestCustomer();
        customer.openAccount();
//        when
        boolean isSuccess = customer.openAccount();
//        then
        assertFalse(isSuccess);
    }

    @Test
    void closeAccount() {
//        given
        Customer customer = getTestCustomer();
        customer.openAccount();
//        when
        boolean isSuccess = customer.closeAccount();
//        then
        assertTrue(isSuccess);
    }

    @Test
    void closeAccountWhenItDoesNotExist() {
//        given
        Customer customer = getTestCustomer();
//        when
        boolean isSuccess = customer.closeAccount();
//        then
        assertFalse(isSuccess);
    }

    @Test
    void fullName() {
//        given
        String name = "Акакий";
        String lastName = "Башмачкин";
//        when
        Customer customer = new Customer(name, lastName);
//        then
        assertEquals(lastName + " " + name, customer.fullName());
    }

    @Test
    void addMoneyToCurrentAccount(){
//        given
        Customer customer =  getTestCustomer();
        customer.openAccount();
        double amount = 137.23;
//        when
        boolean isSuccess =  customer.addMoneyToCurrentAccount(amount);
//        then
        assertTrue(isSuccess);
        assertEquals(amount,customer.getBalance());
    }

    @Test
    void withdrawFromCurrentAccount(){
//        given
        Customer customer =  getTestCustomer();
        customer.openAccount();
        double addAmount = 100;
        double withdrawAmount = 50.5;
        customer.addMoneyToCurrentAccount(addAmount);
//        when
        boolean isSuccess = customer.withdrawFromCurrentAccount(withdrawAmount);
//        then
        assertTrue(isSuccess);
        assertEquals(addAmount-withdrawAmount, customer.getBalance());
    }

    private Customer getTestCustomer(){
        return new Customer("Акакий", "Башмачкин");
    }
}