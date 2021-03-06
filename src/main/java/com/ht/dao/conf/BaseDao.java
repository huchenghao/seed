package com.ht.dao.conf;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
@NoRepositoryBean
public interface BaseDao <T,ID extends Serializable> extends JpaSpecificationExecutor<T>,JpaRepository<T, ID>{

}
