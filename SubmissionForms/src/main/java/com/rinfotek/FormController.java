package com.rinfotek;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FormController {
	@Autowired
	CustomerRepo repo;

//	RequestMapping Annotation
	@RequestMapping("/")
	public String details() {
		return "rinfotek";
	}

	@RequestMapping("/details")
	public String details(Customers customers) {
		repo.save(customers);
		return "rinfotek";// rinfotek.jsp pages
	}

	@RequestMapping("/getdetails")
	public String getdetails() {
		return "ViewCustomer";// ViewCustomers.jsp pages
	}

//	@GetMapping("rinfotek")
//public String rinfotek() {
//		return "rinfotek";// jsp file(users or customers jsp file)
//	}

	// for more details

	@PostMapping("/getdetails")
	public ModelAndView getdetails(@RequestParam int cid) 
//			@RequestParam("cname") String cname,
//			@RequestParam("cemail") String cemail, ModelMap modelMap) 
	{
		ModelAndView mv=new ModelAndView("Retrieve");//Retrieve.jsp
//	 in case customer id not present than id given null...
		Customers customers=repo.findById(cid).orElse(null);
		mv.addObject(customers);
		return mv;
	}

}
