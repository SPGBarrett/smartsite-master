package com.barrett.aialertservice.mapper;

import com.barrett.aialertservice.bean.ClothesMsgData;
import com.barrett.aialertservice.bean.HelmetMsgData;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface ClothesMsgDataMapper {
    @Insert({"INSERT INTO clothes_msg_data(id,guid,parent_id,time_stamp,alert_flag,report_num) VALUES (#{id},#{guid},#{parent_id},#{time_stamp},#{alert_flag},#{report_num})"})
    int insert(ClothesMsgData clothesMsgData);

    @Update({"UPDATE clothes_msg_data SET guid=#{guid},parent_id=#{parent_id},time_stamp=#{time_stamp},alert_flag=#{alert_flag},report_num=#{report_num} WHERE id=#{id}"})
    int update(ClothesMsgData clothesMsgData);

    @Delete({"DELETE FROM clothes_msg_data WHERE guid=#{guid}"})
    int deleteByGuid(@Param("guid") int paramGuid);

    @Delete({"DELETE FROM clothes_msg_data"})
    int deleteAll();

    @Select({"SELECT * FROM clothes_msg_data WHERE guid=#{guid}"})
    List<ClothesMsgData> getAllByGuid(@Param("guid") String paramGuid);

    @Select({"SELECT * FROM clothes_msg_data WHERE parent_id=#{parentID}"})
    List<ClothesMsgData> getAllByParentID(@Param("parentID") String paramParentID);

    @Select({"SELECT id,guid,parent_id,time_stamp,alert_flag,report_num FROM clothes_msg_data"})
    List<ClothesMsgData> getAll();
}
