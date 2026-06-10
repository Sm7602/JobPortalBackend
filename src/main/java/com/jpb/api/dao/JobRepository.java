package com.jpb.api.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import com.jpb.api.entity.Job;

public interface JobRepository extends JpaRepository<Job,Long> {

}
