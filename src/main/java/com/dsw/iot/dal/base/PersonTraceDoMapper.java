package com.dsw.iot.dal.base;

import com.dsw.iot.model.PersonTraceDo;
import com.dsw.iot.model.PersonTraceDoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PersonTraceDoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person_trace
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    int countByExample(PersonTraceDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person_trace
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    int deleteByExample(PersonTraceDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person_trace
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person_trace
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    int insert(PersonTraceDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person_trace
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    int insertSelective(PersonTraceDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person_trace
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    List<PersonTraceDo> selectByExample(PersonTraceDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person_trace
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    PersonTraceDo selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person_trace
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    int updateByExampleSelective(@Param("record") PersonTraceDo record, @Param("example") PersonTraceDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person_trace
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    int updateByExample(@Param("record") PersonTraceDo record, @Param("example") PersonTraceDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person_trace
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    int updateByPrimaryKeySelective(PersonTraceDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person_trace
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    int updateByPrimaryKey(PersonTraceDo record);
}