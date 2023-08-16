package app.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import app.request.signUp;

@Component
public class SignupValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return signUp.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "required", "Your name is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required", "Your email is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required", "Password is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname", "required", "Firstname is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "required", "Lastname is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "required", "Address is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "required", "Phone is required");
		


	}
}
