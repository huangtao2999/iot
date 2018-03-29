package com.dsw.iot.service;

import com.dsw.iot.dto.RoomAreaRequest;
import com.dsw.iot.dto.RoomPropertyRequest;
import com.dsw.iot.model.RoomAreaDo;
import com.dsw.iot.model.RoomPropertyDo;
import com.dsw.iot.util.ActionResult;
import com.dsw.iot.util.BizException;
import com.dsw.iot.util.PageResult;

/**
 * 房间区域配置服务
 * @author zc
 *
 */
public interface RoomAreaService {
	
	 /**
     * 分页查询房间区域配置
     */
    public PageResult<RoomAreaDo> queryPage(RoomAreaRequest request);

    /**
     * 新增或更新房间区域配置
     */
    public void saveRoomArea(RoomAreaDo roomAreaDo) throws BizException;

	/**
	 * 根据主键id查询数据
	 * 
	 * @param id
	 * @return
	 */
	public RoomAreaDo getRoomArea(Long id);

	/**
	 * 根据主键id删除数据
	 */
	public void deleteRoomArea(String ids);

}
