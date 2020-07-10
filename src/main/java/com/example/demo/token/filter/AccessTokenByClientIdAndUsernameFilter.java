package com.example.demo.token.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.com.itechcorp.base.repository.filter.BaseFilter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccessTokenByClientIdAndUsernameFilter implements BaseFilter {
    private String clientId;
    private String username;
}
