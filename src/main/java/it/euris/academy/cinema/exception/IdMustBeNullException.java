package it.euris.academy.cinema.exception;

/**
 * @author Busterna Davide
 *
 * @since 2021-09-24
 */

public class IdMustBeNullException extends RuntimeException {

  private static final long serialVersionUID = 11547588023389561L;

  public IdMustBeNullException() {
    super("Id must be null. You sent a dto with an id already present");
  }

  public IdMustBeNullException(String message) {
    super(message);
  }
}
