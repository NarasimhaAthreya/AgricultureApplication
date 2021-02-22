package com.agriculture.development.contorllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.agriculture.development.DAO.FinanceDAO;
import com.agriculture.development.entities.Finance;

@Controller
@RequestMapping("/finance")
public class FinanceController {

	@Autowired
	private FinanceDAO financeDao;

	@RequestMapping("/show")
	public String showFinance(Model model) {
		List<Finance> finance = financeDao.getFinance();
		Finance fin = new Finance();
		model.addAttribute("financers", finance);
		model.addAttribute("finance", fin);
		return "showFinance";

	}

	@RequestMapping("/add")
	public String addFinance(@ModelAttribute("finance") Finance finance) {
		System.out.println("Adding new finance company ..");
		finance.setVerificationStatus("NV");
		financeDao.addFinance(finance);
		return "redirect:/finance/show";

	}
}
