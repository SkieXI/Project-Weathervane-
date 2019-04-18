package controller;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.interceptor.Interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import beans.*;
import business.*;
import util.*;
/**
 * @Author Jerran Fredricks/Joe Lean
 * 10/21/2018
 **/
@Named
@ViewScoped
@Interceptors(LoggingIntercepter.class)
public class UserController implements Serializable{
	Logger logger = LoggerFactory.getLogger(RestService.class);
	
	@EJB
	WeatherManagerInterface mgr;
	
	/**This is for Register
	 * @param user
	 * @return web page that user will be passed to
	 */
	public String onSubmit(User user) {
		logger.info("onSubmit() called");
		try {
			//Takes the information and passes it to the WeatherManager.
			//If it succeeds, then it will return the mainmenu.xhtml.
			
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
			
			
			if(mgr.validateLogin(user) == false)
			{
			mgr.validateRegister(user);
			logger.info("passing to MainMenu.xhtml");
			return "MainMenu.xhtml";
			}
			else
			{
				logger.info("passing to Register.xhtml");
				return "Register.xhtml";
			}
		}
		//If it fails, then it will return the register page again.
		catch(Exception e) {
		logger.error("Register Error Occured, returning to Register Page");
		return "Register.xhtml";
		}
	}
	
	/** Takes the information from the login page and compares it to the database to see if it already exist or not.
	 * 
	 * @param user
	 * @return web page that user will be passed to
	 */
	public String login(User user) {
		logger.info("login() called");	
		try {
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
			if(mgr.validateLogin(user) == true)
			{
				return "MainMenu.xhtml";
			}
			else
			{
				return "Login.xhtml";
			}
		} catch (Exception e) {
			logger.error("Login Error Occured, returning to Register Page");
		}
		logger.info("login() called");
		return "passing to MainMenu.xhtml";
	}

	
}