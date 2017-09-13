package com.bridgelabz.toDoApp.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.toDoApp.JSONResponse.ErrorResponse;
import com.bridgelabz.toDoApp.JSONResponse.Response;
import com.bridgelabz.toDoApp.JSONResponse.UserResponse;
import com.bridgelabz.toDoApp.model.User;
import com.bridgelabz.toDoApp.service.serviceImplem.UserServiceImplem;
import com.bridgelabz.toDoApp.social.JavaMail;
import com.bridgelabz.toDoApp.util.CryptoUtil;
import com.bridgelabz.toDoApp.util.OtpNumber;
import com.bridgelabz.toDoApp.validator.UserValidation;

/**
 * @author bridgeit
 *
 */
@RestController
public class UserController {
	private static final Logger logger = Logger.getLogger(UserController.class);
	@Autowired
	private UserServiceImplem userService;

	@Autowired
	private UserValidation userValidation;
	@Autowired
	private UserResponse userResponse;
	@Autowired
	private ErrorResponse errorResponse;
	@Autowired
	private JavaMail javaMail;
	@Autowired
	private OtpNumber otpNumber;

	/**
	 * @param user
	 * @param result
	 * @return
	 * @throws Exception
	 * 
	 * 
	 */
	@PostMapping(value = "/signup", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Response> signUp(@RequestBody User user, BindingResult result) throws Exception {
		System.out.println("insidebackend");
		userValidation.validate(user, result);
		String userEmailAuthToken=UUID.randomUUID().toString().replace("-", "");
		user.setUseremailtoken(userEmailAuthToken);
		if (result.hasErrors()) {
			logger.error("Registration Failed.... try Again");
			List<FieldError> list = result.getFieldErrors();
			errorResponse.setList(list);
			errorResponse.setStatus(-1);
			errorResponse.setMessage("binding result error");
			return new ResponseEntity<Response>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
		}
		try {
			user.setPassword(CryptoUtil.getDigest(user.getPassword()));
			
			userService.signUp(user);
			logger.info("Registered successfully!");
			userResponse.setStatus(1);
			userResponse.setMessage("Sucessfully Registered");
			userResponse.setUser(null);
			javaMail.userEmailAuth(user.getEmail(), userEmailAuthToken, user.getId());
			

			return new ResponseEntity<Response>(userResponse, HttpStatus.OK);
		} catch (Exception exception) {
			exception.printStackTrace();
			logger.error("Registration Failed");
			errorResponse.setStatus(-1);
			errorResponse.setMessage("  Internal server error....");
			return new ResponseEntity<Response>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@RequestMapping(value = "/activatestatuscode/{userid}/{token}",method=RequestMethod.GET)
	public ResponseEntity<Response> activateStatusCode(@PathVariable("userid") int userid,@PathVariable("token") String useremailtoken,HttpServletResponse response) {
		
	boolean status=	userService.userEmailAuthtokenValidation(userid,useremailtoken);
	if(status==true)
	{
		userService.userStatusSave(userid,status);
		try {
			response.sendRedirect("http://localhost:8008/ToDoApp/#!/signin");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
		return new ResponseEntity<Response>(HttpStatus.OK);

	}
	
	@PutMapping(value = "/uploadprofile")
	public ResponseEntity<Response> userProfile(@RequestBody User user) throws Exception {
		System.out.println("inside uploade profile" + user);
		System.out.println(user.getId());
		System.out.println(user.getProfile());
		boolean profileresult = userService.uploadeProfile(user.getId(), user.getProfile());
		System.out.println("inside uploade profile" +profileresult);

		try {
			if (profileresult == true) {
				userResponse.setStatus(1);
				userResponse.setMessage("Image Uploadation sucess");
				userResponse.setUser(null);
				return new ResponseEntity<Response>(userResponse, HttpStatus.OK);
			} else {
				userResponse.setStatus(-1);
				userResponse.setMessage("Image Uploadation fld");
				userResponse.setUser(null);
				return new ResponseEntity<Response>(userResponse, HttpStatus.OK);

			}
		}

		catch (Exception exception) {
			exception.printStackTrace();
			userResponse.setStatus(-1);
			userResponse.setMessage("  Internal server error....");
			return new ResponseEntity<Response>(userResponse, HttpStatus.NOT_ACCEPTABLE);
		}

	}

	@PostMapping(value = "/gatemail")
	public ResponseEntity<Response> getEmail(@RequestBody User user,HttpServletRequest request) throws Exception {
		User user1 = userService.getUserByEmail(user.getEmail());
		
		try {
			if (user1 == null) {
				userResponse.setStatus(-1);
				userResponse.setMessage("email not exist");
				userResponse.setUser(null);
				
				return new ResponseEntity<Response>(userResponse, HttpStatus.OK);
			}

			else {
				System.out.println(user.getEmail());
			    javaMail.javaMail(user.getEmail());
				userResponse.setStatus(1);
				userResponse.setMessage("email Found");
				userResponse.setUser(user1);
				HttpSession httpSession=request.getSession();
				httpSession.setAttribute("userforgetid",user1.getId());
				return new ResponseEntity<Response>(userResponse, HttpStatus.OK);
			}

		}

		catch (Exception exception) {
			exception.printStackTrace();
			userResponse.setStatus(-1);
			userResponse.setMessage("  Internal server error....");
			return new ResponseEntity<Response>(userResponse, HttpStatus.NOT_ACCEPTABLE);
		}

	}
	
	@PostMapping(value = "/resetpassword")
	public ResponseEntity<Response> resetPassword(@RequestBody User user,HttpServletRequest request) throws Exception {
		System.out.println("user id"+user.getId());
		HttpSession httpSession=request.getSession();
		 int userid= (Integer) httpSession.getAttribute("userforgetid");
		 user.setPassword(CryptoUtil.getDigest(user.getPassword()));
		boolean result = userService.resetPassword(userid,user.getPassword());
		System.out.println("resetpassword"+result);
		try {
			if (result == true) {
				userResponse.setStatus(1);
				userResponse.setMessage("Password Reset Sucessfully");
				userResponse.setUser(null);
				return new ResponseEntity<Response>(userResponse, HttpStatus.OK);
			}

			else {
				userResponse.setStatus(-1);
				userResponse.setMessage("Password Reset fld ....try Again");
				userResponse.setUser(null);
				return new ResponseEntity<Response>(userResponse, HttpStatus.OK);
			}

		}

		catch (Exception exception) {
			exception.printStackTrace();
			userResponse.setStatus(-1);
			userResponse.setMessage("  Internal server error....");
			return new ResponseEntity<Response>(userResponse, HttpStatus.NOT_ACCEPTABLE);
		}

	}
	@PostMapping(value = "/otp")
	public ResponseEntity<Response> checkOtpNumber(@RequestBody Map< String,Object> otp ) throws Exception {
		
		
		 boolean otpnumber=OtpNumber.OtpValidation((String)otp.get("otpnumber"));
		 System.out.println(otpnumber);
		try {
			if (otpnumber == true) {
				userResponse.setStatus(1);
				userResponse.setMessage("otp matched Sucessfully");
				userResponse.setUser(null);
				return new ResponseEntity<Response>(userResponse, HttpStatus.OK);
			}

			else {
				userResponse.setStatus(-1);
				userResponse.setMessage("otp invalid or time out....try Again");
				userResponse.setUser(null);
				return new ResponseEntity<Response>(userResponse, HttpStatus.OK);
			}

		}

		catch (Exception exception) {
			exception.printStackTrace();
			userResponse.setStatus(-1);
			userResponse.setMessage("  Internal server error....");
			return new ResponseEntity<Response>(userResponse, HttpStatus.NOT_ACCEPTABLE);
		}

	}


}
