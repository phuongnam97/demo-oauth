package com.example.demo.user.service.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.com.itechcorp.base.repository.filter.BaseFilter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserByUsernameFilter implements BaseFilter {
    private String username;
}
