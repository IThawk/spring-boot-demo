package com.ithawk.learn.springboot.service.impl;

import com.ithawk.learn.springboot.entity.OrmUser;
import com.ithawk.learn.springboot.entity.ShareEmailDetail;
import com.ithawk.learn.springboot.mapper.OrmUserMapper;
import com.ithawk.learn.springboot.service.OrmUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author IThawk
 * @version V1.0
 * @description:
 * @date 2020-04-11 20:53
 */
@Service
public class OrmUserServiceImpl  implements OrmUserService {

//    private List<String> lists;
//    {
//        List<String> list = new ArrayList<>(10);
//        list.add("姓名");
//        list.add("姓名");
//        list.add("姓名");
//        this.lists = list;
//    }

    @Autowired
    private OrmUserMapper ormUserMapper;

    @Override
    public int addOrmUser(OrmUser user) {
        return ormUserMapper.insert(user);
    }

    @Override
    public List<OrmUser> selectAllUser() {
        return ormUserMapper.selectAllOrmUser();
    }

    @Override
    public List<OrmUser> getData(ShareEmailDetail email) {
        System.out.println("这个是OrmUser");
        return ormUserMapper.selectAllOrmUser();
    }

    @Override
    public List<String> getColumn() {

        return OrmUser.ORM_USER_COLUMN;
    }

    @Override
    public void addData(List<OrmUser> o) {
        System.out.println("orm_user.........");
        System.out.println(o);
    }
}
