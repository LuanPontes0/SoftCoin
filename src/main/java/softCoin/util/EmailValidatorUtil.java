package softCoin.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidatorUtil {

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private final Pattern pattern;
	private Matcher matcher;

	public EmailValidatorUtil() {

		pattern = Pattern.compile(EMAIL_PATTERN);
	}

	public boolean validar(String email) {

		matcher = pattern.matcher(email);
		return matcher.matches();

	}

}
