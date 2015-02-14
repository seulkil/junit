package edu.csupomona.cs480.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.*;

import edu.csupomona.cs480.App;
import edu.csupomona.cs480.data.User;
import edu.csupomona.cs480.data.provider.UserManager;
import edu.csupomona.cs480.util.ResourceResolver;

/**
 * This is the controller used by Spring framework.
 * <p>
 * The basic function of this controller is to map each HTTP API Path to the
 * correspondent method.
 *
 */

@RestController
public class WebController {

	// /////////////// All code between are examples from the professor
	// //////////////

	/**
	 * When the class instance is annotated with {@link Autowired}, it will be
	 * looking for the actual instance from the defined beans.
	 * <p>
	 * In our project, all the beans are defined in the {@link App} class.
	 */
	@Autowired
	private UserManager userManager;

	/**
	 * This is a simple example of how the HTTP API works. It returns a String
	 * "OK" in the HTTP response. To try it, run the web application locally, in
	 * your web browser, type the link: http://localhost:8080/cs480/ping
	 */
	@RequestMapping(value = "/cs480/ping", method = RequestMethod.GET)
	String healthCheck() {
		// You can replace this with other string,
		// and run the application locally to check your changes
		// with the URL: http://localhost:8080/
		return "Hello World! Seulki ⚘ Hetal ⚘ Khamille";
	}

	/**
	 * This is a simple example of how to use a data manager to retrieve the
	 * data and return it as an HTTP response.
	 * <p>
	 * Note, when it returns from the Spring, it will be automatically converted
	 * to JSON format.
	 * <p>
	 * Try it in your web browser: http://localhost:8080/cs480/user/user101
	 */
	@RequestMapping(value = "/cs480/user", method = RequestMethod.GET)
	User getUser(@RequestParam("UserId") String userId) {
		User user = userManager.getUser(userId);
		return user;
	}

	/**
	 * This is an example of sending an HTTP POST request to update a user's
	 * information (or create the user if not exists before).
	 *
	 * You can test this with a HTTP client by sending
	 * http://localhost:8080/cs480/user/user101 name=John major=CS
	 *
	 * Note, the URL will not work directly in browser, because it is not a GET
	 * request. You need to use a tool such as curl.
	 *
	 * @param id
	 * @param name
	 * @param major
	 * @return
	 */
	@RequestMapping(value = "/cs480/user/{userId}", method = RequestMethod.POST)
	User updateUser(@PathVariable("userId") String id,
			@RequestParam("name") String name,
			@RequestParam(value = "major", required = false) String major) {
		User user = new User();
		user.setId(id);
		// user.setMajor(major);
		// user.setName(name);
		userManager.updateUser(user);
		return user;
	}

	/**
	 * This API deletes the user. It uses HTTP DELETE method.
	 *
	 * @param userId
	 */
	@RequestMapping(value = "/cs480/user/{userId}", method = RequestMethod.DELETE)
	void deleteUser(@PathVariable("userId") String userId) {
		userManager.deleteUser(userId);
	}

	/**
	 * This API lists all the users in the current database.
	 *
	 * @return
	 */
	@RequestMapping(value = "/cs480/users/list", method = RequestMethod.GET)
	List<User> listAllUsers() {
		return userManager.listAllUsers();
	}

	// /////////////// All code between are examples from the professor
	// //////////////

	@RequestMapping(value = "/cs480/users/files", method = RequestMethod.GET)
	List<User> listFiles() {
		return userManager.listFiles();
	}

	/********************************** Testing for Problems **********************************/
	/**
	 * Below are all the methods that I am adding to work with displaying
	 * Problems.
	 */

	/**
	 * When the class instance is annotated with {@link Autowired}, it will be
	 * looking for the actual instance from the defined beans.
	 * 
	 * @RequestMapping(value = "/cs480/users/files", method = RequestMethod.GET)
	 *                       List<User> listFiles() { return
	 *                       userManager.listFiles(); }
	 * 
	 *                       /*********** Web UI Test Utility
	 **********/
	/**
	 * This method provide a simple web UI for you to test the different
	 * functionalities used in this web service.
	 */
	@RequestMapping(value = "/cs480/home", method = RequestMethod.GET)
	ModelAndView getUserHomepage() {
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("users", listAllUsers());
		return modelAndView;
	}

	@RequestMapping(value = "/cs480/codeSubmit", method = RequestMethod.GET)
	ModelAndView getUsercodeSubmit() {
		ModelAndView modelAndView = new ModelAndView("codeSubmit");
		System.out.println(listAllUsers());
		modelAndView.addObject("users", listAllUsers());
		modelAndView.addObject("files ", listFiles());

		return modelAndView;
	}

	@RequestMapping(value = "/cs480/loginHome", method = RequestMethod.GET)
	ModelAndView getlogin() {
		ModelAndView modelAndView = new ModelAndView("loginHome");

		return modelAndView;
	}

	/********************************** Testing for Problems **********************************/
	/**
	 * Below are all the methods that I am adding to work with displaying
	 * Problems.
	 */

	/**
	 * When the class instance is annotated with {@link Autowired}, it will be
	 * looking for the actual instance from the defined beans.
	 * <p>
	 * In our project, all the beans are defined in the {@link App} class.
	 */
	// @Autowired
	// private UserManager userManager;

	/**
	 * This is a simple example of how to use a data manager to retrieve the
	 * data and return it as an HTTP response.
	 * <p>
	 * Note, when it returns from the Spring, it will be automatically converted
	 * to JSON format.
	 * <p>
	 * Try it in your web browser: http://localhost:8080/cs480/problem/12709
	 */
	/*
	 * @RequestMapping(value = "/cs480/problem/{problemId}", method =
	 * RequestMethod.GET) Problem getProblem(@PathVariable("problemId") String
	 * problemId) { Problem problem = problemManager.getProblem(problemId);
	 * return problem; }
	 */

	/**
	 * This is an example of sending an HTTP POST request to update a problem's
	 * information (or create the problem if it doesn't exist yet).
	 *
	 * You can test this with a HTTP client by sending
	 * http://localhost:8080/cs480/problem/12709 ==name=John major=CS==
	 *
	 * Note, the URL will not work directly in browser, because it is not a GET
	 * request. You need to use a tool such as curl.
	 *
	 * @param id
	 * @param name
	 * @param major
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/cs480/problem/{problemId}", method =
	 * RequestMethod.POST) Problem updateProblem(
	 * 
	 * @PathVariable("problemId") String id,
	 * 
	 * @RequestParam(value = "title", required = false) String title,
	 * 
	 * @RequestParam("quarter") String quarter,
	 * 
	 * @RequestParam("year") String year,
	 * 
	 * @RequestParam("week") String week,
	 * 
	 * @RequestParam("pdfUrl") String pdfUrl) { Problem problem = new Problem();
	 * problem.setId(id); // would like to change next 3 to one "setSession()"
	 * problem.setTitle(title); problem.setQuarter(quarter);
	 * problem.setYear(year); problem.setWeek(week); problem.setPdfUrl(pdfUrl);
	 * problemManager.updateProblem(problem); return problem; }
	 */

	/**
	 * Testing File upload
	 * 
	 * if you visit
	 * 
	 * http://localhost:8080 /cs480/codesubmit
	 * 
	 * , you can see the upload page .
	 * 
	 * If the file was upload,
	 * 
	 * it will show you the message
	 * 
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public @ResponseBody String provideUploadInfo() {
		return "You can upload a file by posting to this same URL.";
	}

	/**
	 * Download file, done
	 * 
	 * file should be in the repository folder
	 * 
	 *  
	 * @param fileName
	 * @param response
	 * @return
	 * @throws IOException
	 */
	
	//@RequestMapping(value = "/file/{fileName}/user/{user}/download", method = RequestMethod.GET)
	@RequestMapping(value = "/user/{user}/download", method = RequestMethod.GET)
	public void getFile(/*@PathVariable("fileName") String fileName,*/
			@PathVariable("user") String userId, HttpServletResponse response) throws IOException {    
		//System.out.println(fileName);
		User user = userManager.getUser(userId);
		String path = user.getFilePath();
		System.out.println(path);
		File f = new File(path);/*ResourceResolver.getUploadedFile(fileName);*/
		System.out.println(f.getAbsolutePath() + " " + f.exists());
		// 1.GetthefileofyourphotobasedontheuserIdandphotoId
		InputStream file = new FileInputStream(f);

		//2.Returnthephotofileasanoutputstreamusingthecodebelow
		IOUtils.copy(file,response.getOutputStream());
		//response.setContentType("image/jpeg");
		response.setHeader("Content-Disposition","attachment; filename= \"" + f.getName() + "\"");//fileName);

		response.flushBuffer();

	}

	/**
	 * Khamille's contribution for Assignment 5: HashBasedTable using
	 * Google Guava.
	 */
	
	
	@RequestMapping(value = "/cs480/guava", method = RequestMethod.GET)
	public String guava() {
		
		HashBasedTable twoKeyMap = HashBasedTable.create();
		return "Hello World! I created a HashTable whose values are accessible using two key values using Google Guava.";
	}
    
    /** 
     * Testing File upload
     * 
     * if you visit 
     * 
     * http://localhost:8080 /cs480/codesubmit
     * 
     * , you can see the upload page .
     * 
     * If the file was upload, 
     * 
     * it will show you the message
     * 
     *//*
    @RequestMapping(value="/upload", method=RequestMethod.GET)
    public @ResponseBody String provideUploadInfo() {
        return "You can upload a file by posting to this same URL.";
    }*/

	@RequestMapping(value="/cs480/codeSubmit", method=RequestMethod.POST)
   public @ResponseBody ModelAndView handleFileUpload(
		    @RequestParam("UserID") String id,
    		@RequestParam("ProblemID") String promb,
    		@RequestParam("Weeks") int weekNo,
            @RequestParam("file") MultipartFile file){
    	String name = null;                             
    	String dir = System.getProperty("user.home") + "\\cs480\\";
        if (!file.isEmpty()) {
            try {
            	
            	User user = new User();
            	name = file.getOriginalFilename();
            	user.setId(id);
            	user.setWeek(weekNo);
            	user.setScore("-");
            	user.setprob(promb);
            	user.setStatus(true);
            	user.setFileName(name);
            	user.setFilePath(dir + name);
            	user.setStat();
            	userManager.updateUser(user);// add
            	
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(dir + name)));
                stream.write(bytes);
                stream.close();

                ModelAndView modelAndView = new ModelAndView("/codeSubmit");
                modelAndView.addObject("users", listAllUsers());
                
                return modelAndView;
            } catch (Exception e) {
            	  ModelAndView modelAndView = new ModelAndView("You failed to upload "  + " => " + e.getMessage());
            	  return modelAndView;
            }
        } else {
        	
        	ModelAndView modelAndView = new ModelAndView("You failed to upload "  + " because the file was empty.");
        	return modelAndView;
        }	

    }

}