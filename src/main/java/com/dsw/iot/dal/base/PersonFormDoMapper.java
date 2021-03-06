package com.dsw.iot.dal.base;

import com.dsw.iot.model.PersonFormDo;
import com.dsw.iot.model.PersonFormDoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PersonFormDoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person_form
     *
     * @mbggenerated Thu Mar 22 17:36:10 CST 2018
     */
    int countByExample(PersonFormDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person_form
     *
     * @mbggenerated Thu Mar 22 17:36:10 CST 2018
     */
    int deleteByPrimaryKey(PersonFormDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person_form
     *
     * @mbggenerated Thu Mar 22 17:36:10 CST 2018
     */
    int insert(PersonFormDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person_form
     *
     * @mbggenerated Thu Mar 22 17:36:10 CST 2018
     */
    int insertSelective(PersonFormDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person_form
     *
     * @mbggenerated Thu Mar 22 17:36:10 CST 2018
     */
    List<PersonFormDo> selectByExample(PersonFormDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person_form
     *
     * @mbggenerated Thu Mar 22 17:36:10 CST 2018
     */
    PersonFormDo selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person_form
     *
     * @mbggenerated Thu Mar 22 17:36:10 CST 2018
     */
    int updateByExampleSelective(@Param("record") PersonFormDo record, @Param("example") PersonFormDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person_form
     *
     * @mbggenerated Thu Mar 22 17:36:10 CST 2018
     */
    int updateByExample(@Param("record") PersonFormDo record, @Param("example") PersonFormDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person_form
     *
     * @mbggenerated Thu Mar 22 17:36:10 CST 2018
     */
    int updateByPrimaryKeySelective(PersonFormDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person_form
     *
     * @mbggenerated Thu Mar 22 17:36:10 CST 2018
     */
    int updateByPrimaryKey(PersonFormDo record);
}