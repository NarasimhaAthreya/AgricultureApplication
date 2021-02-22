package com.agriculture.development.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.agriculture.development.entities.Farmers;

@Repository
public interface FarmerDAOImpl extends JpaRepository<Farmers, Integer> {

	@Query("Select a from Farmers a where a.verificationStatus='NV'")
	public List<Farmers> getNonVerified();
}
