package hr.algebra.springproject.error;

public class TokenExpirationException extends RuntimeException {
    private final String errorMessage;

    public TokenExpirationException(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
