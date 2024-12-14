package models;

import exceptions.*;

import java.math.BigDecimal;

/**
 * The User class represents a user in the blockchain application.
 * Each user has a unique username, an authentication token, and a balance.
 * <p>
 * Version: 1.0
 * Author: Charalampos Deligiannakis
 */
public class User {

    // The username of the user
    private String username;

    // The authentication token of the user
    private String authToken;

    // The balance of the user
    private BigDecimal balance;

    /**
     * Constructs a new User with the specified username, authentication token, and initial balance.
     *
     * @param username  The username of the user. Must consist of letters, numbers, and underscores only.
     * @param authToken The authentication token for the user.
     * @param balance   The initial balance of the user. Must be greater than or equal to zero.
     * @throws UserException If the username is invalid or the balance is negative.
     */
    public User(String username, String authToken, BigDecimal balance) throws UserException {
        setUsername(username);
        setAuthToken(authToken);
        setBalance(balance);
    }

    /**
     * Sets the username of the user.
     *
     * @param username The username to set. Must consist of letters, numbers, and underscores only.
     * @throws UserException If the username is null, empty, or contains invalid characters.
     */
    public void setUsername(String username) throws UserException {
        if (username != null && username.matches("^[A-Za-z0-9_]+$")) {
            this.username = username;
        } else {
            throw new UserException("Invalid username. Only letters, numbers, and underscores are allowed.", username);
        }
    }

    /**
     * Returns the username of the user.
     *
     * @return The username of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the authentication token for the user.
     *
     * @param authToken The authentication token to set.
     * @throws UserException If the authToken is null or empty.
     */
    public void setAuthToken(String authToken) throws UserException {
        if (authToken != null && !authToken.trim().isEmpty()) {
            this.authToken = authToken;
        } else {
            throw new UserException("Authentication token cannot be null or empty.", authToken);
        }
    }

    /**
     * Returns the authentication token of the user.
     *
     * @return The authentication token of the user.
     */
    public String getAuthToken() {
        return authToken;
    }

    /**
     * Sets the balance of the user.
     *
     * @param balance The balance to set. Must be greater than or equal to zero.
     * @throws UserException If the balance is null or negative.
     */
    public void setBalance(BigDecimal balance) throws UserException {
        if (balance != null && balance.compareTo(BigDecimal.ZERO) >= 0) {
            this.balance = balance;
        } else {
            throw new UserException("Balance must be greater than or equal to zero.", balance);
        }
    }

    /**
     * Returns the balance of the user.
     *
     * @return The balance of the user.
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * Returns a string representation of the user.
     *
     * @return A string representing the user's details, excluding the authentication token for security.
     */
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", balance=" + balance +
                '}';
    }
}