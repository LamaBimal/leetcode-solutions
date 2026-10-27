package com.learning.principles;

/**
 * Fail Fast
 *
 * Validate inputs and surface errors as early as possible.
 * Don't let bad state silently propagate through the system.
 *
 * BAD: accept null/invalid input, produce wrong results later.
 * GOOD: throw immediately at the boundary so the caller knows right away.
 */
public class FailFast {

    public static class BankAccount {
        private double balance;

        public BankAccount(double initialBalance) {
            if (initialBalance < 0) {
                throw new IllegalArgumentException("Initial balance cannot be negative");
            }
            this.balance = initialBalance;
        }

        public void deposit(double amount) {
            if (amount <= 0) {
                throw new IllegalArgumentException("Deposit amount must be positive");
            }
            balance += amount;
        }

        public void withdraw(double amount) {
            if (amount <= 0) {
                throw new IllegalArgumentException("Withdrawal amount must be positive");
            }
            if (amount > balance) {
                throw new IllegalStateException("Insufficient funds");
            }
            balance -= amount;
        }

        public double getBalance() { return balance; }
    }
}
