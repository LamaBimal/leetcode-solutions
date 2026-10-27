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

        /** Creates a teaching account with a finite, nonnegative balance. */
        public BankAccount(double initialBalance) {
            if (!Double.isFinite(initialBalance) || initialBalance < 0) {
                throw new IllegalArgumentException("Initial balance must be finite and nonnegative");
            }
            this.balance = initialBalance;
        }

        public void deposit(double amount) {
            validateAmount(amount);
            double updatedBalance = balance + amount;
            if (!Double.isFinite(updatedBalance)) {
                throw new IllegalArgumentException("Deposit would exceed the finite balance range");
            }
            balance = updatedBalance;
        }

        public void withdraw(double amount) {
            validateAmount(amount);
            if (amount > balance) {
                throw new IllegalStateException("Insufficient funds");
            }
            balance -= amount;
        }

        public double getBalance() { return balance; }

        private void validateAmount(double amount) {
            if (!Double.isFinite(amount) || amount <= 0) {
                throw new IllegalArgumentException("Amount must be finite and positive");
            }
        }
    }
}
