package com.dsw.iot.dal.base;

import com.dsw.iot.model.CardLocateDo;
import com.dsw.iot.model.CardLocateDoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CardLocateDoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table card_locate
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    int countByExample(CardLocateDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table card_locate
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    int deleteByExample(CardLocateDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table card_locate
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table card_locate
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    int insert(CardLocateDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table card_locate
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    int insertSelective(CardLocateDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table card_locate
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    List<CardLocateDo> selectByExampleWithBLOBs(CardLocateDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table card_locate
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    List<CardLocateDo> selectByExample(CardLocateDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table card_locate
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    CardLocateDo selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table card_locate
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    int updateByExampleSelective(@Param("record") CardLocateDo record, @Param("example") CardLocateDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table card_locate
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    int updateByExampleWithBLOBs(@Param("record") CardLocateDo record, @Param("example") CardLocateDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table card_locate
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    int updateByExample(@Param("record") CardLocateDo record, @Param("example") CardLocateDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table card_locate
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    int updateByPrimaryKeySelective(CardLocateDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table card_locate
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    int updateByPrimaryKeyWithBLOBs(CardLocateDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table card_locate
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    int updateByPrimaryKey(CardLocateDo record);
}