package com.bridgelabz.toDoApp.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bridgelabz.toDoApp.model.User;

public class UserValidation implements Validator 
{
	private Pattern pattern;
	private Matcher matcher;

	private static String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static String STRING_PATTERN = "[A-Za-z]+";
	private static String MOBILE_PATTERN = "[0-9]{10}";
	private static String PASSWORD_PATTERN = "[A-Za-z0-9]{5,10}";

	public boolean supports(Class<?> clazz) 
	{

		return false;
	}

	public void validate(Object target, Errors errors)
	{
		User user = (User) target;

		ValidationUtils.rejectIfEmpty(errors, "firstname", "required.firstname", "FirstName is required.");

		if (!(user.getFirstname() != null && user.getFirstname().isEmpty())) 
		{
			pattern = Pattern.compile(STRING_PATTERN);
			matcher = pattern.matcher(user.getFirstname());
			if (!matcher.matches()) {
				errors.rejectValue("firstname", "firstname.containNonChar", "Enter a valid name");
			}

		}
		ValidationUtils.rejectIfEmpty(errors, "lastname", "required.lastname", "lastname is required.");

		if (!(user.getLastname() != null && user.getLastname().isEmpty())) 
		{
			pattern = Pattern.compile(STRING_PATTERN);
			matcher = pattern.matcher(user.getLastname());
			if (!matcher.matches()) {
				errors.rejectValue("lastname", "lastname.containNonChar", "Enter a valid name");
			}

		}

		ValidationUtils.rejectIfEmpty(errors, "email", "required.email", "Email is required.");

		// email validation

		if (!(user.getEmail() != null || user.getEmail().isEmpty())) {
			pattern = Pattern.compile(EMAIL_PATTERN);
			matcher = pattern.matcher(user.getEmail());
			if (!matcher.matches()) {
				errors.rejectValue("email", "email.incorrect", "Enter a correct email");
			}

		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required.password", "Password is required.");

		// password validation
		if (!(user.getPassword() != null && user.getPassword().isEmpty())) 
		{
			pattern = Pattern.compile(PASSWORD_PATTERN);
			matcher = pattern.matcher(user.getPassword());
			if (!matcher.matches())
			{
				errors.rejectValue("Password", "password.incorrect", " Enter Password ");
			}

		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobile", "required.mobile", "Phone is required.");

		// phone number validation
		if (!(user.getMobile() != null && user.getMobile().isEmpty())) 
		{
			pattern = Pattern.compile(MOBILE_PATTERN);
			matcher = pattern.matcher(user.getMobile());
			if (!matcher.matches())
			{
				errors.rejectValue("mobile", "mobile.incorrect", "Enter a correct phone number");
			}
		}
	}

	public void userLoginValidation(Object target1, Errors errors1) {
		User user1 = (User) target1;
	
		//  login email validation
		ValidationUtils.rejectIfEmpty(errors1, "email", "required.email", "Email is required.");

	
		if (!(user1.getEmail() != null || user1.getEmail().isEmpty())) {
			pattern = Pattern.compile(EMAIL_PATTERN);
			matcher = pattern.matcher(user1.getEmail());
			if (!matcher.matches()) {
				errors1.rejectValue("email", "email.incorrect", "Enter a correct email");
			}

		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors1, "password", "required.password", "Password is required.");

		//  login password validation
		if (!(user1.getPassword() != null && user1.getPassword().isEmpty()))
		{
			pattern = Pattern.compile(PASSWORD_PATTERN);
			matcher = pattern.matcher(user1.getPassword());
			if (!matcher.matches()) {
				errors1.rejectValue("Password", "password.incorrect", " Enter Password ");
			}

		}

	}
}
