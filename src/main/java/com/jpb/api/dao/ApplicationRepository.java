package com.jpb.api.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import com.jpb.api.entity.Application;

public interface ApplicationRepository extends JpaRepository<Application,Long> {

}
