package com.learning.principles;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FailFastTest {

    @Test
    void rejectsNonfiniteInitialBalances() {
        for (double value : new double[]{Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY}) {
            assertThrows(IllegalArgumentException.class, () -> new FailFast.BankAccount(value));
        }
    }

    @Test
    void rejectsInvalidTransactionsWithoutChangingBalance() {
        FailFast.BankAccount account = new FailFast.BankAccount(100);
        for (double amount : new double[]{Double.NaN, Double.POSITIVE_INFINITY,
                Double.NEGATIVE_INFINITY, 0, -1}) {
            assertThrows(IllegalArgumentException.class, () -> account.deposit(amount));
            assertEquals(100.0, account.getBalance());
            assertThrows(IllegalArgumentException.class, () -> account.withdraw(amount));
            assertEquals(100.0, account.getBalance());
        }
        assertThrows(IllegalStateException.class, () -> account.withdraw(101));
        assertEquals(100.0, account.getBalance());
    }

    @Test
    void rejectsDepositOverflowWithoutChangingBalance() {
        FailFast.BankAccount account = new FailFast.BankAccount(Double.MAX_VALUE);
        assertThrows(IllegalArgumentException.class, () -> account.deposit(Double.MAX_VALUE));
        assertEquals(Double.MAX_VALUE, account.getBalance());
    }

    @Test
    void acceptsZeroBalanceAndWithdrawalOfEntireBalance() {
        FailFast.BankAccount account = new FailFast.BankAccount(0);
        account.deposit(25);
        account.withdraw(25);
        assertEquals(0.0, account.getBalance());
    }

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
