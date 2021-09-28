package it.euris.academy.cinema.utils;

import java.time.Instant;

/**
 * @author Busterna Davide
 *
 * @since 2021-09-24
 */

public class UT {

  public static String toString(Object o) {
    return o == null ? null : o.toString();
  }

  public static Long toLong(String s) {
    return s == null ? null : Long.parseLong(s);
  }

  public static Boolean toBoolean(String s) {
    return s == null ? null : Boolean.valueOf(s);
  }
  
  public static Instant toInstant(String s) {
    return s == null ? null : Instant.parse(s);
  }
}
