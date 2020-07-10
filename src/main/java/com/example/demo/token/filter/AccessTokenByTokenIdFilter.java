package com.example.demo.token.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.com.itechcorp.base.repository.filter.BaseFilter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccessTokenByTokenIdFilter implements BaseFilter {
    private String tokenId;
}
