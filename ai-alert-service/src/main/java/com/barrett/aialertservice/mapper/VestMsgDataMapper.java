package com.barrett.aialertservice.mapper;

import com.barrett.aialertservice.bean.HelmetMsgData;
import com.barrett.aialertservice.bean.VestMsgData;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface VestMsgDataMapper {
    @Insert({"INSERT INTO vest_msg_data(id,guid,parent_id,message,nums_of_wrong_clothes,alert_flag,time_stamp) VALUES (#{id},#{guid},#{parent_id},#{message},#{nums_of_wrong_clothes},#{alert_flag},#{time_stamp})"})
    int insert(VestMsgData vestMsgData);

    @Update({"UPDATE vest_msg_data SET guid=#{guid},parent_id=#{parent_id},message=#{message},nums_of_wrong_clothes=#{nums_of_wrong_clothes},alert_flag=#{alert_flag},time_stamp=#{time_stamp} WHERE id=#{id}"})
    int update(VestMsgData vestMsgData);

    @Delete({"DELETE FROM vest_msg_data WHERE guid=#{guid}"})
    int deleteByGuid(@Param("guid") int paramGuid);

    @Delete({"DELETE FROM vest_msg_data"})
    int deleteAll();

    @Select({"SELECT * FROM vest_msg_data WHERE guid=#{guid}"})
    List<VestMsgData> getAllByGuid(@Param("guid") String paramGuid);

    @Select({"SELECT * FROM vest_msg_data WHERE parent_id=#{parentID}"})
    List<VestMsgData> getAllByParentID(@Param("parentID") String paramParentID);

    @Select({"SELECT id,guid,parent_id,message,nums_of_wrong_clothes,alert_flag,time_stamp FROM vest_msg_data"})
    List<VestMsgData> getAll();
}
