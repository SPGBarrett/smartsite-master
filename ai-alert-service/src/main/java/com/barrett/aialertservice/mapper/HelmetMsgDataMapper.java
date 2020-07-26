package com.barrett.aialertservice.mapper;

import com.barrett.aialertservice.bean.HelmetMsgData;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface HelmetMsgDataMapper {
    @Insert({"INSERT INTO helmet_msg_data(id,guid,parent_id,time_stamp,num_of_head,alert_flag) VALUES (#{id},#{guid},#{parent_id},#{time_stamp},#{num_of_head},#{alert_flag})"})
    int insert(HelmetMsgData helmetMsgData);

    @Update({"UPDATE helmet_msg_data SET guid=#{guid},parent_id=#{parent_id},time_stamp=#{time_stamp},num_of_head=#{num_of_head},alert_flag=#{alert_flag} WHERE id=#{id}"})
    int update(HelmetMsgData helmetMsgData);

    @Delete({"DELETE FROM helmet_msg_data WHERE guid=#{guid}"})
    int deleteByGuid(@Param("guid") int paramGuid);

    @Delete({"DELETE FROM helmet_msg_data"})
    int deleteAll();

    @Select({"SELECT * FROM helmet_msg_data WHERE guid=#{guid}"})
    List<HelmetMsgData> getAllByGuid(@Param("guid") String paramGuid);

    @Select({"SELECT * FROM helmet_msg_data WHERE parent_id=#{parentID}"})
    List<HelmetMsgData> getAllByParentID(@Param("parentID") String paramParentID);

    @Select({"SELECT id,guid,parent_id,time_stamp,num_of_head,alert_flag FROM helmet_msg_data"})
    List<HelmetMsgData> getAll();
}
