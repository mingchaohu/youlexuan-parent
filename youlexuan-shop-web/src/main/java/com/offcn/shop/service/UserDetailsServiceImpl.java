package com.offcn.shop.service;

import com.offcn.pojo.TbSeller;
import com.offcn.sellergoods.service.SellerService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/*
UserDetailsService实现类
 */
public class UserDetailsServiceImpl implements UserDetailsService {
    /*
    自定义认证类
     */

    private SellerService sellerService;
    public void setSellerService(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    /*
     * 自定义认证
     * */
    @Override
    public UserDetails loadUserByUsername(String sellerId) throws UsernameNotFoundException {
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority("ROLE_SELLER"));
        //查询sellerId正确的密码
        TbSeller seller = sellerService.findOne(sellerId);

        if (seller == null) {
            return null;
        } else {
            if (seller.getStatus().equals("1")) {
                String password = seller.getPassword();
                return new User(sellerId, password, list);
            } else {
                return null;
            }

        }

    }

}
