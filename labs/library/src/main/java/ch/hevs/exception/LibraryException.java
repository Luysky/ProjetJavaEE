package ch.hevs.exception;

public class LibraryException extends RuntimeException {

	public LibraryException() {
		super();
	}

	public LibraryException(String arg0) {
		super(arg0);
	}

	public LibraryException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public LibraryException(Throwable arg0) {
		super(arg0);
	}

}
