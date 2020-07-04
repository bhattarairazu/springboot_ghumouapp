package com.acepirit.ghumou.main.GhumouMain.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acepirit.ghumou.main.GhumouMain.Entity.Orderpackage;
import com.acepirit.ghumou.main.GhumouMain.Entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Orderpackage, Integer> {
	
	public  List<Orderpackage> findAllByUser(User user);

	@Query("SELECT o FROM Orderpackage o WHERE o.packages.packageSellar=:packageSellar")
	List<Orderpackage> findAllByPackagessPackageSellar(@Param("packageSellar") String packageSellar);

	@Query(value = "SELECT LAST_INSERT_ID() from order_package limit 1",nativeQuery = true)
	int lastInsertId();
	
}
