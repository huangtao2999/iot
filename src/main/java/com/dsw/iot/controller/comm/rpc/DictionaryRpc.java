package com.dsw.iot.controller.comm.rpc;

import com.dsw.iot.vo.DictionaryVo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 字典请求rpc
 *
 * @author huangt
 * @create 2018-01-27 18:32
 **/
@RestController
@RequestMapping("/Dictionary")
public class DictionaryRpc {

    @RequestMapping("/getDicts")
    public List<DictionaryVo> getDicts(String type) {
        List<DictionaryVo> list = new ArrayList<>();
        DictionaryVo dictionaryVo = null;
        if ("country".equals(type)) {
            for (int i = 0; i < 5; i++) {
                dictionaryVo = new DictionaryVo();
                dictionaryVo.setId((long) i);
                dictionaryVo.setCode("COUNTRY_CODE" + i);
                dictionaryVo.setName("地区" + i);
                list.add(dictionaryVo);
            }
        } else if ("type".equals(type)) {
            dictionaryVo = new DictionaryVo();
            dictionaryVo.setId((long) 1);
            dictionaryVo.setCode("小");
            dictionaryVo.setName("小");
            list.add(dictionaryVo);
            dictionaryVo = new DictionaryVo();
            dictionaryVo.setId((long) 2);
            dictionaryVo.setCode("中");
            dictionaryVo.setName("中");
            list.add(dictionaryVo);
            dictionaryVo = new DictionaryVo();
            dictionaryVo.setId((long) 3);
            dictionaryVo.setCode("大");
            dictionaryVo.setName("大");
            list.add(dictionaryVo);

        } else {
            for (int i = 0; i < 5; i++) {
                dictionaryVo = new DictionaryVo();
                dictionaryVo.setId((long) i);
                dictionaryVo.setCode("COUNTRY_CODE" + i);
                dictionaryVo.setName("其它" + i);
                list.add(dictionaryVo);
            }
        }
        return list;
    }
}
