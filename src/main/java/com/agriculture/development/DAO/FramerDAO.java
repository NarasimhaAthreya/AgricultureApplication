package com.agriculture.development.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agriculture.development.entities.Farmers;

@Service
public class FramerDAO {

	@Autowired
	private FarmerDAOImpl farmer;

	public Farmers addFarmer(Farmers farm) {
		System.out.println("Adding farmer " + farm.getName());
		Farmers fa = farmer.save(farm);
		System.out.println("Farmer has been added and his unique id is " + fa.getId());
		return fa;
	}

	public List<Farmers> showFarmers() {
		System.out.println("Getting farmers details ..");
		return farmer.findAll();
	}

	public Farmers getFarmerbyId(int id) {
		System.out.println("Getting farmer details for " + id);
		return farmer.getOne(id);
	}

	public void update(Farmers farm) {
		farmer.save(farm);
	}
	
	public List<Farmers> getnonverified(){
		System.out.print("Getting list of non verified famers");
		return farmer.getNonVerified();
	}
}
