package com.example.demo.token.db;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.token.AccessToken;
import com.example.demo.token.AccessToken_;
import com.example.demo.token.filter.AccessTokenByAuthenticationIdFilter;
import com.example.demo.token.filter.AccessTokenByClientIdAndUsernameFilter;
import com.example.demo.token.filter.AccessTokenByTokenIdFilter;
import org.springframework.stereotype.Repository;
import vn.com.itechcorp.base.repository.dao.CriteriaInfo;
import vn.com.itechcorp.base.repository.dao.hibernate.AuditableDAOHbnImpl;
import vn.com.itechcorp.base.repository.filter.BaseFilter;

@Repository
public class AccessTokenDAOImpl extends AuditableDAOHbnImpl<AccessToken, Long> implements AccessTokenDAO{
    @Override
    public Class<AccessToken> getEntityClass() {
        return AccessToken.class;
    }

    @Override
    public List<Predicate> createPredicates(CriteriaInfo criteriaInfo, BaseFilter filter) {
        if (filter instanceof AccessTokenByAuthenticationIdFilter) return createByAuthenticationIdFilter(criteriaInfo, (AccessTokenByAuthenticationIdFilter) filter);
        if (filter instanceof AccessTokenByClientIdAndUsernameFilter) return createByClientIdAndUsernameFilter(criteriaInfo, (AccessTokenByClientIdAndUsernameFilter) filter);
        if (filter instanceof AccessTokenByTokenIdFilter) return createByTokenIdFilter(criteriaInfo, (AccessTokenByTokenIdFilter) filter);
        return null;
    }

    private List<Predicate> createByAuthenticationIdFilter(CriteriaInfo criteriaInfo, AccessTokenByAuthenticationIdFilter filter) {
        List<Predicate> predicates = new ArrayList<>();

        if (filter.getAuthenticationId() != null)
            predicates.add(criteriaInfo.getBuilder().equal(criteriaInfo.getRoot().get(AccessToken_.AUTHENTICATION_ID), filter.getAuthenticationId()));

        return predicates;
    }

    private List<Predicate> createByClientIdAndUsernameFilter(CriteriaInfo criteriaInfo, AccessTokenByClientIdAndUsernameFilter filter) {
        List<Predicate> predicates = new ArrayList<>();

        if (filter.getClientId() != null)
            predicates.add(criteriaInfo.getBuilder().equal(criteriaInfo.getRoot().get(AccessToken_.CLIENT_ID), filter.getClientId()));
        if (filter.getUsername() != null)
            predicates.add(criteriaInfo.getBuilder().equal(criteriaInfo.getRoot().get(AccessToken_.USERNAME), filter.getUsername()));

        return predicates;
    }

    private List<Predicate> createByTokenIdFilter(CriteriaInfo criteriaInfo, AccessTokenByTokenIdFilter filter) {
        List<Predicate> predicates = new ArrayList<>();

        if (filter.getTokenId() != null)
            predicates.add(criteriaInfo.getBuilder().equal(criteriaInfo.getRoot().get(AccessToken_.AUTHENTICATION_ID), filter.getTokenId()));

        return predicates;
    }

}
