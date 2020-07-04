package com.acepirit.ghumou.main.GhumouMain.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acepirit.ghumou.main.GhumouMain.Entity.Orderpackage;
import com.acepirit.ghumou.main.GhumouMain.Entity.Payment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
	
	public List<Payment> findByOrder(Orderpackage order);

	@Query("SELECT p FROM Payment as p WHERE p.order.user.id=:userid")
	List<Payment> findByUserid(@Param("userid") int userid);

	@Query("SELECT p FROM Payment as p WHERE p.order.packages.packageSellar=:name")
	List<Payment> findBySellar(@Param("name") String name);

}
