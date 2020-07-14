package com.barrett.aialertservice.mapper;

import com.barrett.aialertservice.bean.AlertMsg;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AlertMsgMapper {
    @Insert({"INSERT INTO alert_msg(id,guid,aid,cid,cid_url,time_stamp,status,srcpic_name,srcpic_data,pic_name,pic_data,alert_type) VALUES (#{id},#{guid},#{aid},#{cid},#{cid_url},#{time_stamp},#{status},#{srcpic_name},#{srcpic_data},#{pic_name},#{pic_data},#{alert_type})"})
    int insert(AlertMsg alertMsg);

    @Update({"UPDATE alert_msg SET guid=#{guid},aid=#{aid},cid=#{cid},cid_url=#{cid_url},time_stamp=#{time_stamp},status=#{status},srcpic_name=#{srcpic_name},srcpic_data=#{srcpic_data},pic_name=#{pic_name},pic_data=#{pic_data},alert_type=#{alert_type} WHERE id=#{id}"})
    int update(AlertMsg alertMsg);

    @Delete({"DELETE FROM alert_msg WHERE guid=#{guid}"})
    int deleteByGuid(@Param("guid") int paramGuid);

    @Delete({"DELETE FROM alert_msg"})
    int deleteAll();

    @Select({"SELECT * FROM alert_msg WHERE guid=#{guid}"})
    List<AlertMsg> getAllByGuid(@Param("guid") String paramGuid);

    @Select({"SELECT id,guid,aid,cid,cid_url,time_stamp,status,srcpic_name,srcpic_data,pic_name,pic_data,alert_type FROM alert_msg"})
    List<AlertMsg> getAll();
}
