package com.dsw.iot.dal.base;

import com.dsw.iot.model.CardManageDo;
import com.dsw.iot.model.CardManageDoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CardManageDoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table card_manage
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    int countByExample(CardManageDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table card_manage
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    int deleteByExample(CardManageDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table card_manage
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table card_manage
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    int insert(CardManageDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table card_manage
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    int insertSelective(CardManageDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table card_manage
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    List<CardManageDo> selectByExample(CardManageDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table card_manage
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    CardManageDo selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table card_manage
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    int updateByExampleSelective(@Param("record") CardManageDo record, @Param("example") CardManageDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table card_manage
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    int updateByExample(@Param("record") CardManageDo record, @Param("example") CardManageDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table card_manage
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    int updateByPrimaryKeySelective(CardManageDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table card_manage
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    int updateByPrimaryKey(CardManageDo record);
}