package com.agriculture.development.contorllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.agriculture.development.DAO.FinanceDAO;
import com.agriculture.development.DAO.FramerDAO;
import com.agriculture.development.entities.Farmers;
import com.agriculture.development.entities.Finance;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private FramerDAO farmdao;

	@Autowired
	private FinanceDAO financeDao;

	@RequestMapping("/show")
	public String showAdmin(Model model) {

		List<Farmers> farm = farmdao.getnonverified();
		List<Finance> fin = financeDao.getnonverified();

		model.addAttribute("farmers", farm);
		model.addAttribute("financers", fin);

		return "Admin";
	}

	@RequestMapping("/verify/{id}")
	public String verfifyFarmers(@PathVariable("id") int id) {
		Farmers farm = farmdao.getFarmerbyId(id);
		farm.setVerificationStatus("V");
		farmdao.update(farm);
		System.out.println("Succuessfully updated status ");
		return "redirect:/admin/show";
	}

	@RequestMapping("/verify/finance/{id}")
	public String verifyFinancers(@PathVariable("id") int id) {
		Finance fin = financeDao.getFarmerbyId(id);
		fin.setVerificationStatus("V");
		financeDao.update(fin);
		System.out.println("Succuessfully updated status ");
		return "redirect:/admin/show";
	}
}
