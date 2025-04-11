package exceptions;

public class CsvToTurtleConversionException extends Exception {
  public CsvToTurtleConversionException(String message) {
    super(message);
  }

  public CsvToTurtleConversionException(String message, Throwable err) {
    super(message, err);
  }
}
