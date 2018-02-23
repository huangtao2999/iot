package com.dsw.iot.dal.base;

import com.dsw.iot.model.RoomAreaDo;
import com.dsw.iot.model.RoomAreaDoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoomAreaDoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_area
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    int countByExample(RoomAreaDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_area
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    int deleteByExample(RoomAreaDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_area
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_area
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    int insert(RoomAreaDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_area
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    int insertSelective(RoomAreaDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_area
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    List<RoomAreaDo> selectByExample(RoomAreaDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_area
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    RoomAreaDo selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_area
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    int updateByExampleSelective(@Param("record") RoomAreaDo record, @Param("example") RoomAreaDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_area
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    int updateByExample(@Param("record") RoomAreaDo record, @Param("example") RoomAreaDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_area
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    int updateByPrimaryKeySelective(RoomAreaDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_area
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    int updateByPrimaryKey(RoomAreaDo record);
}