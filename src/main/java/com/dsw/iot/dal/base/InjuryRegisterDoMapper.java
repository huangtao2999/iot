package com.dsw.iot.dal.base;

import com.dsw.iot.model.InjuryRegisterDo;
import com.dsw.iot.model.InjuryRegisterDoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InjuryRegisterDoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table injury_register
     *
     * @mbggenerated Fri Mar 02 12:23:00 CST 2018
     */
    int countByExample(InjuryRegisterDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table injury_register
     *
     * @mbggenerated Fri Mar 02 12:23:00 CST 2018
     */
    int deleteByPrimaryKey(InjuryRegisterDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table injury_register
     *
     * @mbggenerated Fri Mar 02 12:23:00 CST 2018
     */
    int insert(InjuryRegisterDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table injury_register
     *
     * @mbggenerated Fri Mar 02 12:23:00 CST 2018
     */
    int insertSelective(InjuryRegisterDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table injury_register
     *
     * @mbggenerated Fri Mar 02 12:23:00 CST 2018
     */
    List<InjuryRegisterDo> selectByExample(InjuryRegisterDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table injury_register
     *
     * @mbggenerated Fri Mar 02 12:23:00 CST 2018
     */
    InjuryRegisterDo selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table injury_register
     *
     * @mbggenerated Fri Mar 02 12:23:00 CST 2018
     */
    int updateByExampleSelective(@Param("record") InjuryRegisterDo record, @Param("example") InjuryRegisterDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table injury_register
     *
     * @mbggenerated Fri Mar 02 12:23:00 CST 2018
     */
    int updateByExample(@Param("record") InjuryRegisterDo record, @Param("example") InjuryRegisterDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table injury_register
     *
     * @mbggenerated Fri Mar 02 12:23:00 CST 2018
     */
    int updateByPrimaryKeySelective(InjuryRegisterDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table injury_register
     *
     * @mbggenerated Fri Mar 02 12:23:00 CST 2018
     */
    int updateByPrimaryKey(InjuryRegisterDo record);
}