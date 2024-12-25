package xyz.devcmb.ic.challenges;

public class ChallengeNotRegisteredException extends RuntimeException {
    public ChallengeNotRegisteredException(String message) {
        super(message);
    }
}
