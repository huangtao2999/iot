package com.dsw.iot.service.impl;

import com.dsw.iot.service.RoleService;

import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
//    @Autowired
//    private TpRoleDoMapper tpRoleDoMapper;

    /**
     * 每5秒执行一次任务
     */
//    @Scheduled(cron="0/5 * * * * ?")
    @Override
    public void print() {
//        System.out.println(LocalDateTime.now());
//        TpRoleDoExample example = new TpRoleDoExample();
//        List<TpRoleDo> list = tpRoleDoMapper.selectByExample(example);
//        list.stream().forEach(item->{
//            System.out.println(item.getId() +":"+item.getRolename());
//        });
    }
}
