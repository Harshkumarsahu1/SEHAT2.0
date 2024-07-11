//package com.scm.controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import com.scm.entities.User;
//import com.scm.forms.UserForm;
//import com.scm.helpers.Message;
//import com.scm.helpers.MessageType;
//import com.scm.services.UserService;
//
//import jakarta.servlet.http.HttpSession;
//import jakarta.validation.Valid;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//
//@Controller
//public class PageController {
//
//    @Autowired
//    private UserService userService;
//
//    @GetMapping("/")
//    public String index() {
//        return "redirect:/home";
//    }
//
//    @RequestMapping("/home")
//    public String home(Model model) {
//        System.out.println("Home page handler");
//        // sending data to view
//        model.addAttribute("name", "Substring Technologies");
//        model.addAttribute("youtubeChannel", "Learn Code With Durgesh");
//        model.addAttribute("githubRepo", "https://github.com/learncodewithdurgesh/");
//        return "home";
//    }
//
//    // about route
//
//    @RequestMapping("/about")
//    public String aboutPage(Model model) {
//        model.addAttribute("isLogin", true);
//        System.out.println("About page loading");
//        return "about";
//    }
//
//    // services
//
//    @RequestMapping("/services")
//    public String servicesPage() {
//        System.out.println("services page loading");
//        return "services";
//    }
//
//    // contact page
//
//    @GetMapping("/contact")
//    public String contact() {
//        return new String("contact");
//    }
//
//    // this is showing login page
//    @GetMapping("/login")
//    public String login() {
//        return new String("login");
//    }
//
//    // registration page
//    @GetMapping("/register")
//    public String register(Model model) {
//
//        UserForm userForm = new UserForm();
//        // default data bhi daal sakte hai
//        // userForm.setName("Durgesh");
//        // userForm.setAbout("This is about : Write something about yourself");
//        model.addAttribute("userForm", userForm);
//
//        return "register";
//    }
//
//    // processing register
//
//    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
//    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult,
//            HttpSession session) {
//        System.out.println("Processing registration");
//        // fetch form data
//        // UserForm
//        System.out.println(userForm);
//
//        // validate form data
//        if (rBindingResult.hasErrors()) {
//            return "register";
//        }
//
//        // TODO::Validate userForm[Next Video]
//
//        // save to database
//
//        // userservice
//
//        // UserForm--> User
//        // User user = User.builder()
//        // .name(userForm.getName())
//        // .email(userForm.getEmail())
//        // .password(userForm.getPassword())
//        // .about(userForm.getAbout())
//        // .phoneNumber(userForm.getPhoneNumber())
//        // .profilePic(
//        // "https://www.learncodewithdurgesh.com/_next/image?url=%2F_next%2Fstatic%2Fmedia%2Fdurgesh_sir.35c6cb78.webp&w=1920&q=75")
//        // .build();
//
//        User user = new User();
//        user.setName(userForm.getName());
//        user.setEmail(userForm.getEmail());
//        user.setPassword(userForm.getPassword());
//        user.setAbout(userForm.getAbout());
//        user.setPhoneNumber(userForm.getPhoneNumber());
//        user.setProfilePic(
//                "https://www.learncodewithdurgesh.com/_next/image?url=%2F_next%2Fstatic%2Fmedia%2Fdurgesh_sir.35c6cb78.webp&w=1920&q=75");
//
//        User savedUser = userService.saveUser(user);
//
//        System.out.println("user saved :");
//
//        // message = "Registration Successful"
//
//        // add the message:
//
//        Message message = Message.builder().content("Registration Successful").type(MessageType.green).build();
//
//        session.setAttribute("message", message);
//
//        // redirectto login page
//        return "redirect:/register";
//    }
//
//}
package com.scm.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.scm.APICall;
import com.scm.repsitories.InputRepo;
import com.scm.entities.Input;
import com.scm.entities.Recs;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.services.UserService;

//import com.hemlata.app.APICall;
//import com.hemlata.app.model.Input;
//import com.hemlata.app.repository.InputRepo;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @Autowired
    private InputRepo irepo;

    @Autowired
    private APICall ap;

    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }

    @RequestMapping("/home")
    public String home(Model model) {
        System.out.println("Home page handler");
        // sending data to view
        model.addAttribute("name", "Substring Technologies");
        model.addAttribute("youtubeChannel", "Learn Code With harshh");
        model.addAttribute("githubRepo", "https://github.com/Harshkumarsahu1/");
        return "home";
    }

    @RequestMapping("/about")
    public String aboutPage(Model model) {
        model.addAttribute("isLogin", true);
        System.out.println("About page loading");
        return "about";
    }

    @RequestMapping("/services")
    public String servicesPage() {
        System.out.println("services page loading");
        return "services";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        return "register";
    }

    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult, HttpSession session) {
        System.out.println("Processing registration");
        System.out.println(userForm);

        if (rBindingResult.hasErrors()) {
            return "register";
        }

        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setProfilePic(
                "https://www.learncodewithdurgesh.com/_next/image?url=%2F_next%2Fstatic%2Fmedia%2Fdurgesh_sir.35c6cb78.webp&w=1920&q=75");

        User savedUser = userService.saveUser(user);
        System.out.println("User saved : " + savedUser);

        Message message = Message.builder().content("Registration Successful").type(MessageType.green).build();
        session.setAttribute("message", message);

        return "redirect:/register";
    }

    @RequestMapping(value = "/quickanswer", method = RequestMethod.GET)
    public ModelAndView getQuery(ModelAndView modelAndView, Input input) throws InterruptedException, IOException {
        modelAndView.setViewName("quickAnswer");
        return modelAndView;
    }

    @RequestMapping(value = "/quickanswer", method = RequestMethod.POST)
    public ModelAndView displayForm(ModelAndView modelAndView, Input input) throws InterruptedException, IOException {
        String ques = input.getQues();
        String str = ap.data(ques);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> restMap = mapper.readValue(str, Map.class);
        String Answer = restMap.get("answer").toString();
        String URL = restMap.get("image").toString();
        modelAndView.addObject("answer", Answer);
        modelAndView.addObject("URL", URL);
        modelAndView.setViewName("quickAnswer");
        return modelAndView;
    }

    @RequestMapping(value = "/chillax", method = RequestMethod.GET)
    public ModelAndView chillaxHome(ModelAndView modelAndView) {
        modelAndView.setViewName("chillaxHome");
        return modelAndView;
    }

    @RequestMapping(value = "/GetJoke", method = RequestMethod.POST)
    public ModelAndView getJoke(ModelAndView modelAndView) throws IOException, InterruptedException {
        String joke = ap.Joke();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> jokeMap = mapper.readValue(joke, Map.class);
        String fjoke = jokeMap.get("text").toString();
        modelAndView.addObject("joke", fjoke);
        modelAndView.setViewName("chillaxHome");
        return modelAndView;
    }

    @RequestMapping(value = "/GetTrivia", method = RequestMethod.POST)
    public ModelAndView getTrivia(ModelAndView modelAndView) throws IOException, InterruptedException {
        String triviaRes = ap.trivia();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> jokeMap = mapper.readValue(triviaRes, Map.class);
        String trivia = jokeMap.get("text").toString();
        modelAndView.addObject("trivia", trivia);
        modelAndView.setViewName("chillaxHome");
        return modelAndView;
    }

    @RequestMapping(value = "/quickbrowse", method = RequestMethod.GET)
    public ModelAndView quickBrowseHome(ModelAndView modelAndView) throws IOException, InterruptedException {
        modelAndView.setViewName("quickBrowse");
        return modelAndView;
    }

    @RequestMapping(value = "/resbycal", method = RequestMethod.GET)
    public ModelAndView resByCalGet(ModelAndView modelAndView, Input input) throws IOException, InterruptedException {
        modelAndView.setViewName("GetByCal");
        return modelAndView;
    }

    @RequestMapping(value = "/resbycal", method = RequestMethod.POST)
    public ModelAndView resByCalPost(ModelAndView modelAndView, Input input) throws IOException, InterruptedException {
        String map = ap.recipeByCal(input.getCal());
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> RecMap = mapper.readValue(map, Map.class);
        ArrayList<String> meals = (ArrayList<String>) RecMap.get("meals");
        Iterable<Object> iterable = StreamSupport.stream(Spliterators.spliteratorUnknownSize(meals.iterator(), 0), false)
                .collect(Collectors.toList());
        modelAndView.addObject("list", iterable);
        modelAndView.setViewName("GetByCal");
        return modelAndView;
    }

    @RequestMapping(value = "/convertamount", method = RequestMethod.GET)
    public ModelAndView convertAmountGet(ModelAndView modelAndView, Input input) throws IOException, InterruptedException {
        modelAndView.setViewName("convertAmount");
        return modelAndView;
    }

    @RequestMapping(value = "/convertamount", method = RequestMethod.POST)
    public ModelAndView convertAmountPost(ModelAndView modelAndView, Input input) throws IOException, InterruptedException {
        String iname = input.getIname();
        String wunit = input.getWeightUnit();
        String sunit = input.getSunit();
        float samount = input.getSamount();
        String str = ap.convertAmount(iname, wunit, sunit, samount);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> restMap = mapper.readValue(str, Map.class);
        String Answer = restMap.get("answer").toString();
        modelAndView.addObject("answer", Answer);
        modelAndView.setViewName("convertAmount");
        return modelAndView;
    }

//    @RequestMapping(value = "/nutbydish", method = RequestMethod.GET)
//    public ModelAndView nutByDishGet(ModelAndView modelAndView, Input input) throws IOException, InterruptedException {
//        modelAndView.setViewName("NutByDish");
//        return modelAndView;
//    }
//
//    @RequestMapping(value = "/nutbydish", method = RequestMethod.POST)
//    public ModelAndView nutByDishPost(ModelAndView modelAndView, Input input) throws IOException, InterruptedException {
//        String dname = input.getDishname();
//        String str = ap.nutByDish(dname);
//        ObjectMapper mapper = new ObjectMapper();
//        Map<String, Object> restMap = mapper.readValue(str, Map.class);
//        String cals = restMap.get("calories").toString();
//        String prots = restMap.get("protein").toString();
//        String fat = restMap.get("fat").toString();
//        String carbs = restMap.get("carbs").toString();
//        modelAndView.addObject("cals", cals);
//        modelAndView.addObject("prots", prots);
//        modelAndView.addObject("fat", fat);
//        modelAndView.addObject("carbs", carbs);
//        modelAndView.setViewName("NutByDish");
//        return modelAndView;
//    }
@RequestMapping(value = "/nutbydish", method = RequestMethod.GET)
public ModelAndView nutByDishGet(ModelAndView modelAndView, Input input) {
    modelAndView.setViewName("NutByDish");
    return modelAndView;
}

    @SneakyThrows
    @RequestMapping(value = "/nutbydish", method = RequestMethod.POST)
    public ModelAndView nutByDishPost(ModelAndView modelAndView, Input input) throws IOException {
        String dname = input.getDishname();
        String str = ap.nutByDish(dname);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> restMap = mapper.readValue(str, Map.class);

        // Extract nested values
        Map<String, Object> calories = (Map<String, Object>) restMap.get("calories");
        Map<String, Object> protein = (Map<String, Object>) restMap.get("protein");
        Map<String, Object> fat = (Map<String, Object>) restMap.get("fat");
        Map<String, Object> carbs = (Map<String, Object>) restMap.get("carbs");

        // Get the value field from each map
        String cals = calories.get("value").toString();
        String prots = protein.get("value").toString();
        String fatVal = fat.get("value").toString();
        String carbsVal = carbs.get("value").toString();

        // Add attributes to the model
        modelAndView.addObject("cals", cals);
        modelAndView.addObject("prots", prots);
        modelAndView.addObject("fat", fatVal);
        modelAndView.addObject("carbs", carbsVal);
        modelAndView.setViewName("NutByDish");
        return modelAndView;
    }
    @RequestMapping(value = "/smartsearch", method = RequestMethod.GET)
    public ModelAndView smartSearchGet(ModelAndView modelAndView, Input input) throws IOException, InterruptedException {
        modelAndView.setViewName("search");
        return modelAndView;
    }

    @RequestMapping(value = "/yourquery", method = RequestMethod.GET)
    public ModelAndView yourQueryGet(ModelAndView modelAndView) {
        modelAndView.setViewName("yourQuery");
        return modelAndView;
    }
}
