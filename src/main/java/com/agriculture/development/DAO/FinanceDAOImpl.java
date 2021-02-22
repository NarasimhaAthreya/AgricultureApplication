package com.agriculture.development.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.agriculture.development.entities.Finance;

@Repository
public interface FinanceDAOImpl extends JpaRepository<Finance, Integer> {
	
	@Query("Select a from Finance a where a.verificationStatus='NV'")
	public List<Finance> getNonVerified();

}
