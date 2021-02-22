package com.agriculture.development.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agriculture.development.entities.Farmers;
import com.agriculture.development.entities.Finance;

@Service
public class FinanceDAO {

	@Autowired
	private FinanceDAOImpl financeDAO;

	public void addFinance(Finance finance) {

		System.out.println("Adding finance .. ");
		financeDAO.save(finance);
		System.out.println("Added Financing company .. ");
	}

	public List<Finance> getFinance() {

		System.out.println("Getting  finance details .. ");
		return financeDAO.findAll();

	}

	public List<Finance> getnonverified() {
		System.out.print("Getting list of non verified finance");
		return financeDAO.getNonVerified();
	}

	public Finance getFarmerbyId(int id) {
		System.out.println("Getting finance details for " + id);
		return financeDAO.getOne(id);
	}

	public void update(Finance fin) {
		financeDAO.save(fin);
	}
}
