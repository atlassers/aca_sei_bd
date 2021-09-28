package it.euris.academy.cinema.exception;

/**
 * @author Busterna Davide
 *
 * @since 2021-09-24
 */

public class IdMustNotBeNullException extends RuntimeException {

  private static final long serialVersionUID = 11547588023389561L;

  public IdMustNotBeNullException() {
    super("Id must not be null.");
  }

  public IdMustNotBeNullException(String message) {
    super(message);
  }
}
