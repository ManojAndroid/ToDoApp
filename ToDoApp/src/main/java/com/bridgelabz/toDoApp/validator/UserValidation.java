package com.bridgelabz.toDoApp.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bridgelabz.toDoApp.model.User;

public class UserValidation implements Validator {
	private Pattern pattern;
	private Matcher matcher;

	private static String EMAIL_PATTERN = "^([a-zA-Z0-9\\-\\.\\_]+)'+'(\\@)([a-zA-Z0-9\\-\\.]+)'+'(\\.)([a-zA-Z]{2,4})$";
	private static String STRING_PATTERN = "[A-Za-z]+";
	private static String MOBILE_PATTERN = "[0-9]{10}";
	private static String PASSWORD_PATTERN = "[A-Za-z0-9]{5,10}";

	public boolean supports(Class<?> clazz) {

		return false;
	}

	public void validate(Object target, Errors errors) {
		User user = (User) target;

		ValidationUtils.rejectIfEmpty(errors, "name", "required.name", "Name is required.");

		if (!(user.getName() != null && user.getName().isEmpty()))
		{
			pattern = Pattern.compile(STRING_PATTERN);
			matcher = pattern.matcher(user.getName());
			if (!matcher.matches()) {
				errors.rejectValue("name", "name.containNonChar", "Enter a valid name");
			}

		}
		
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required.email", "Email is required.");

		// email validation in spring

		if (!(user.getEmail() != null && user.getEmail().isEmpty())) 
		{
			pattern = Pattern.compile(EMAIL_PATTERN);
			matcher = pattern.matcher(user.getEmail());
			if (!matcher.matches()) {
				errors.rejectValue("email", "email.incorrect", "Enter a correct email");
			}

		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required.password", "Password is required.");

		// password matching validation
		if (!(user.getPassword() != null && user.getPassword().isEmpty())) 
		{
			pattern = Pattern.compile(PASSWORD_PATTERN);
			matcher = pattern.matcher(user.getPassword());
			if (!matcher.matches()) {
				errors.rejectValue("Password", "password.incorrect", "Password does not match");
			}

		}
		
	

		  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone",  
		    "required.phone", "Phone is required.");  
		  
		// phone number validation  
		  if (!(user.getMobile() != null && user.getMobile().isEmpty())) 
		  {  
		   pattern = Pattern.compile(MOBILE_PATTERN);  
		   matcher = pattern.matcher(user.getMobile());  
		   if (!matcher.matches())
		   {  
		    errors.rejectValue("phone", "phone.incorrect",  
		      "Enter a correct phone number");  
		   }  
	   }
	}
}

