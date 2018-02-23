package com.dsw.iot;

import com.dsw.iot.dal.DictionaryDoMapperExt;
import com.dsw.iot.model.DictionaryDo;
import com.dsw.iot.model.DictionaryDoExample;
import com.dsw.iot.util.DomainUtil;
import com.dsw.iot.util.PrivilegeInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

/**
 * 同步达梦数据字典
 * [报警形式,案件类别,颜色,案件状态,物品类型,强制措施类别,办案程序,物品状态,处警标识,案件副案别,性别,公安机构代码,民族,警情类别,数据来源,处警结果,文书对象]
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSyncDictionary {

    @Autowired
    @Qualifier("dmDataSource")
    private DataSource dmDataSource;
    @Autowired
    private DictionaryDoMapperExt dictionaryDoMapperExt;
    PrivilegeInfo privilegeInfo = new PrivilegeInfo();


    //同步民族
//    @Test
    public void testDataSource() {
        try {
            Connection connection = dmDataSource.getConnection();
            Statement stmt = connection.createStatement();
            String sql = "select * from SWYT_ZD WHERE DNAME like '民族'";
            ResultSet resultSet = stmt.executeQuery(sql);
            StringBuilder sb = null;
            //记录字典所有类型
            String type = "ETHNICITY";
            Set<String> set = new HashSet<>();
            DictionaryDo dictionaryDo = new DictionaryDo();
            dictionaryDo.setPid(0L);
            dictionaryDo.setType(type);
            dictionaryDo.setCode(type);
            dictionaryDo.setName("民族");
            dictionaryDo.setIsSystem("1");
            DomainUtil.setCommonValueForCreate(dictionaryDo, privilegeInfo);
            dictionaryDoMapperExt.insertSelective(dictionaryDo);
            while (resultSet.next()) {
                String code = resultSet.getString("CODE");
                DictionaryDoExample example = new DictionaryDoExample();
                example.createCriteria().andTypeEqualTo(type).andCodeEqualTo(code);
                if (dictionaryDoMapperExt.countByExample(example) == 0) {
                    DictionaryDo dictionaryDo1 = new DictionaryDo();
                    dictionaryDo1.setPid(dictionaryDo.getId());
                    dictionaryDo1.setType(type);
                    dictionaryDo1.setCode(code);
                    dictionaryDo1.setName(resultSet.getString("ITEM"));
                    dictionaryDo1.setIsSystem("1");
                    DomainUtil.setCommonValueForCreate(dictionaryDo1, privilegeInfo);
                    dictionaryDoMapperExt.insertSelective(dictionaryDo1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //颜色
//    @Test
    public void testColor() {
        try {
            Connection connection = dmDataSource.getConnection();
            Statement stmt = connection.createStatement();
            String sql = "select * from SWYT_ZD WHERE DNAME like '颜色'";
            ResultSet resultSet = stmt.executeQuery(sql);
            StringBuilder sb = null;
            //记录字典所有类型
            String type = "COLOR";
            Set<String> set = new HashSet<>();
            DictionaryDo dictionaryDo = new DictionaryDo();
            dictionaryDo.setPid(0L);
            dictionaryDo.setType(type);
            dictionaryDo.setCode(type);
            dictionaryDo.setName("颜色");
            dictionaryDo.setIsSystem("1");
            DomainUtil.setCommonValueForCreate(dictionaryDo, privilegeInfo);
            dictionaryDoMapperExt.insertSelective(dictionaryDo);
            while (resultSet.next()) {
                String code = resultSet.getString("CODE");
                DictionaryDoExample example = new DictionaryDoExample();
                example.createCriteria().andTypeEqualTo(type).andCodeEqualTo(code);
                if (dictionaryDoMapperExt.countByExample(example) == 0) {
                    DictionaryDo dictionaryDo1 = new DictionaryDo();
                    dictionaryDo1.setPid(dictionaryDo.getId());
                    dictionaryDo1.setType(type);
                    dictionaryDo1.setCode(code);
                    dictionaryDo1.setName(resultSet.getString("ITEM"));
                    dictionaryDo1.setIsSystem("1");
                    DomainUtil.setCommonValueForCreate(dictionaryDo1, privilegeInfo);
                    dictionaryDoMapperExt.insertSelective(dictionaryDo1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //报警形式
//    @Test
    public void test报警形式() {
        try {
            Connection connection = dmDataSource.getConnection();
            Statement stmt = connection.createStatement();
            String sql = "select * from SWYT_ZD WHERE DNAME like '报警形式'";
            ResultSet resultSet = stmt.executeQuery(sql);
            StringBuilder sb = null;
            //记录字典所有类型
            String type = "ALARM_TYPE";
            Set<String> set = new HashSet<>();
            DictionaryDo dictionaryDo = new DictionaryDo();
            dictionaryDo.setPid(0L);
            dictionaryDo.setType(type);
            dictionaryDo.setCode(type);
            dictionaryDo.setName("报警形式");
            dictionaryDo.setIsSystem("1");
            DomainUtil.setCommonValueForCreate(dictionaryDo, privilegeInfo);
            dictionaryDoMapperExt.insertSelective(dictionaryDo);
            while (resultSet.next()) {
                String code = resultSet.getString("CODE");
                DictionaryDoExample example = new DictionaryDoExample();
                example.createCriteria().andTypeEqualTo(type).andCodeEqualTo(code);
                if (dictionaryDoMapperExt.countByExample(example) == 0) {
                    DictionaryDo dictionaryDo1 = new DictionaryDo();
                    dictionaryDo1.setPid(dictionaryDo.getId());
                    dictionaryDo1.setType(type);
                    dictionaryDo1.setCode(code);
                    dictionaryDo1.setName(resultSet.getString("ITEM"));
                    dictionaryDo1.setIsSystem("1");
                    DomainUtil.setCommonValueForCreate(dictionaryDo1, privilegeInfo);
                    dictionaryDoMapperExt.insertSelective(dictionaryDo1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //同步 湖北省-黄石市  机构
    @Test
    public void test_机构() {
//        建一个机构组织ORG GOV
//        建一个湖北省 42
//        建一个黄石市 4202
        try {
            //黄石市局 4202 00 00 0000
            String type = "ORG";
            Set<String> set = new HashSet<>();
            DictionaryDo rootDictionaryDo = new DictionaryDo();
            rootDictionaryDo.setPid(232L);
            rootDictionaryDo.setType(type);
            rootDictionaryDo.setCode("420200000000");
            rootDictionaryDo.setName("黄石市公安局");
            rootDictionaryDo.setIsSystem("1");
            DomainUtil.setCommonValueForCreate(rootDictionaryDo, privilegeInfo);
            dictionaryDoMapperExt.insertSelective(rootDictionaryDo);
            //分局4202 XX 00 0000
            Connection connection = dmDataSource.getConnection();
            Statement stmt = connection.createStatement();
            String sql = "select distinct(code) ,* from swyt.SWYT_ZD WHERE DNAME like '公安机构代码' AND REGEXP_LIKE(CODE,'4202[[:digit:]]{2}000000')";
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                String code = resultSet.getString("CODE");
                String item = resultSet.getString("ITEM");
                DictionaryDo fjDictionaryDo = new DictionaryDo();
                fjDictionaryDo.setPid(rootDictionaryDo.getId());
                fjDictionaryDo.setType(type);
                fjDictionaryDo.setCode(code);
                fjDictionaryDo.setName(item);
                fjDictionaryDo.setIsSystem("1");
                DomainUtil.setCommonValueForCreate(fjDictionaryDo, privilegeInfo);
                dictionaryDoMapperExt.insertSelective(fjDictionaryDo);
                //派出所 4202 XX XX 0000
                String shiCode = code.substring(2, 4);
                String quCode = code.substring(4, 6);
                sql = "select distinct(code) ,* from swyt.SWYT_ZD WHERE DNAME like '公安机构代码' AND REGEXP_LIKE(CODE,'4202" + quCode + "[[:digit:]]{2}0000') AND CODE <>'" + code +
                        "'";
                ResultSet resultSet1 = stmt.executeQuery(sql);
                while (resultSet1.next()) {
                    String pcsCode = resultSet1.getString("CODE");
                    String pcsItem = resultSet1.getString("ITEM");
                    DictionaryDo pcsDictionaryDo = new DictionaryDo();
                    pcsDictionaryDo.setPid(fjDictionaryDo.getId());
                    pcsDictionaryDo.setType(type);
                    pcsDictionaryDo.setCode(pcsCode);
                    pcsDictionaryDo.setName(pcsItem);
                    pcsDictionaryDo.setIsSystem("1");
                    DomainUtil.setCommonValueForCreate(pcsDictionaryDo, privilegeInfo);
                    dictionaryDoMapperExt.insertSelective(pcsDictionaryDo);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}