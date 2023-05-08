package gesjana.ikub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import gesjana.ikub.exception.ClassNotFoundException;
import gesjana.ikub.model.Class;
import gesjana.ikub.repository.ClassRepository;
import gesjana.ikub.repository.LevelRepository;
import gesjana.ikub.repository.RegistrationRepository;
import gesjana.ikub.repository.SchoolRepository;
import gesjana.ikub.repository.SchoolYearRepository;


/**
 * The Class ClassController.
 */
@Controller
@RequestMapping(path="/class") //aplikimi i një kërkese HTTP në një metodë duke përdorur disa kritere bazë  për të trajtuar metodat e MVC dhe REST.
public class ClassController {
	
	/** The class repository.*/
	@Autowired//mundeson lidhjen automatike midis beans-> objekte qe instantiated, montohen dhe menaxhohen nga Spring IoC
	private ClassRepository classRepository;
	
	/** The school year repository. */
	@Autowired
	private SchoolYearRepository schoolYearRepository;
	
	/** The school repository. */
	@Autowired
	private SchoolRepository schoolRepository;
	
	/** The level repository. */
	@Autowired
	private LevelRepository levelRepository;

	/** The registration repository. */
	@Autowired
	private RegistrationRepository registrationRepository;
	
	/**
	 * Class index.
	 *
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/dashboard")//request HTTP to Get specific methods of handller
    public String classIndex(Model model) {
        model.addAttribute("class", new Class());
        model.addAttribute("classes", classRepository.findAll());
        model.addAttribute("schools", schoolRepository.findAll());
        model.addAttribute("levels", levelRepository.findAll());
        model.addAttribute("schoolYears", schoolYearRepository.findAll());
        return "class/dashboard";
    }
	
	/**
	 * Gets the class.
	 *
	 * @param id the id
	 * @param model the model
	 * @return the class
	 * @throws ClassNotFoundException the class not found exception
	 */
	@GetMapping(path="/{id}")
	public String getClass (@PathVariable(value = "id") Integer id, Model model) //
			throws ClassNotFoundException {
		Class s = classRepository.findById(id)
				.orElseThrow(() -> new ClassNotFoundException());
        model.addAttribute("class", s);
        model.addAttribute("registrations", registrationRepository.findByCId(id));
		return "class/view";
	}
	
	/**
	 * Gets all classes.
	 *
	 * @return all classes
	 */
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Class> getAllClasses() {
		return classRepository.findAll();
	}
	
	/**
	 * Search class.
	 *
	 * @param q the q
	 * @param model the model
	 * @return the string
	 * @throws ClassNotFoundException the class not found exception
	 */
	@GetMapping(path="/search")
	public String searchClass (@RequestParam(value = "search", required = false) String q, Model model)
			throws ClassNotFoundException {
		Iterable<Class> classes = classRepository.findByNameContaining(q);
        model.addAttribute("classes", classes);
		return "class/result";
	}

    /**
     * Creates the class.
     *
     * @param s the s
     * @return the string
     */
    @PostMapping("/create")
    public String createClass(@ModelAttribute Class s) {
		classRepository.save(s);
        return "redirect:dashboard";
    }
    
    /**
     * View update form class.
     *
     * @param id the id
     * @param model the model
     * @return the string
     * @throws ClassNotFoundException the class not found exception
     */
    @GetMapping(path="/{id}/edit")
	public String viewUpdateFormClass(@PathVariable(value = "id") Integer id,
			Model model) throws ClassNotFoundException {
    	Class s = classRepository.findById(id)
				.orElseThrow(() -> new ClassNotFoundException());
    	model.addAttribute("class", s);
        model.addAttribute("schools", schoolRepository.findAll());
        model.addAttribute("levels", levelRepository.findAll());
        model.addAttribute("schoolYears", schoolYearRepository.findAll());
		return "class/edit";
	}
	
	/**
	 * Update class.
	 *
	 * @param s the s
	 * @return the string
	 */
	@PutMapping("/{id}/update")//update class PUT HTTP request
    public String updateClass(@ModelAttribute Class s) {
		classRepository.save(s);
		return "redirect:/class/dashboard";
    }
	
	/**
	 * Delete class.
	 *
	 * @param s the s
	 * @return the string
	 */
	@DeleteMapping("/delete")
    public String deleteClass(@ModelAttribute Class s) {
		classRepository.delete(s);
        return "redirect:dashboard";
    }
}
