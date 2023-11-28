package com.crud.demo.service;

import com.crud.demo.dao.UserDao;
import com.crud.demo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UserService {
    @Resource
    private UserDao UserDao;

    public void save(User user) {
        String now = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        user.setCreateTime(now);
        UserDao.save(user);
    }

    public void delete(Long id) {

        UserDao.deleteById(id);
    }

    public User findById(Long id) {
        return UserDao.findById(id).orElse(null);//.orElse() 如果查询为空返回null 查询不为空，返回Optional<User>对象
    }

    public List<User> findAll() {
        return UserDao.findAll();
    }

    public Page<User> findPage(Integer pageNum, Integer pageSize, String name) {
        // 构建分页查询条件
        Sort sort = Sort.by(Sort.Direction.DESC, "create_time");
        Pageable pageRequest = PageRequest.of(pageNum - 1, pageSize, sort);
        return UserDao.findByNameLike(name, pageRequest);
    }
}
