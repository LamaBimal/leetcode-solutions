package com.learning.principles;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FailFastTest {

    @Test
    void negativeInitialBalanceThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new FailFast.BankAccount(-1));
    }

    @Test
    void depositIncreasesBalance() {
        FailFast.BankAccount account = new FailFast.BankAccount(100);
        account.deposit(50);
        assertEquals(150.0, account.getBalance(), 1e-9);
    }

    @Test
    void zeroDepositThrows() {
        FailFast.BankAccount account = new FailFast.BankAccount(100);
        assertThrows(IllegalArgumentException.class, () -> account.deposit(0));
    }

    @Test
    void overdraftThrows() {
        FailFast.BankAccount account = new FailFast.BankAccount(50);
        assertThrows(IllegalStateException.class, () -> account.withdraw(100));
    }

    @Test
    void validWithdrawalReducesBalance() {
        FailFast.BankAccount account = new FailFast.BankAccount(100);
        account.withdraw(40);
        assertEquals(60.0, account.getBalance(), 1e-9);
    }
}
