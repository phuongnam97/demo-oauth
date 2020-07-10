package com.example.demo.user.service.db;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.user.model.User;
import com.example.demo.user.model.User_;
import com.example.demo.user.service.filter.UserByUsernameFilter;
import org.springframework.stereotype.Repository;
import vn.com.itechcorp.base.repository.dao.CriteriaInfo;
import vn.com.itechcorp.base.repository.dao.hibernate.AuditableDAOHbnImpl;

@Repository
public class UserDAOImpl extends AuditableDAOHbnImpl<User, Long> implements UserDAO{
    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }

    private List<Predicate> createByUsernameFilter(CriteriaInfo criteriaInfo, UserByUsernameFilter filter) {
        List<Predicate> predicates = new ArrayList<>();

        if (filter.getUsername() != null)
            predicates.add(criteriaInfo.getBuilder().equal(criteriaInfo.getRoot().get(User_.USERNAME), filter.getUsername()));

        return predicates;
    }
}
