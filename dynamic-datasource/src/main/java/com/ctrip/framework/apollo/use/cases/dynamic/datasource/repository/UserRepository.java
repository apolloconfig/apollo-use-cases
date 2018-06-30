package com.ctrip.framework.apollo.use.cases.dynamic.datasource.repository;

import com.ctrip.framework.apollo.use.cases.dynamic.datasource.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
}
