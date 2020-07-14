package com.barrett.aialertservice.mapper;

import com.barrett.aialertservice.bean.HeadInfo;
import com.barrett.aialertservice.bean.VestInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface VestInfoMapper {
    @Insert({"INSERT INTO vest_info(id,guid,parent_id,x,y,width,height,probability) VALUES (#{id},#{guid},#{parent_id},#{x},#{y},#{width},#{height},#{probability})"})
    int insert(VestInfo vestInfo);

    @Update({"UPDATE vest_info SET guid=#{guid},parent_id=#{parent_id},x=#{x},y=#{y},width=#{width},height=#{height},probability=#{probability} WHERE id=#{id}"})
    int update(VestInfo vestInfo);

    @Delete({"DELETE FROM vest_info WHERE guid=#{guid}"})
    int deleteByGuid(@Param("guid") int paramGuid);

    @Delete({"DELETE FROM vest_info"})
    int deleteAll();

    @Select({"SELECT * FROM vest_info WHERE guid=#{guid}"})
    List<VestInfo> getAllByGuid(@Param("guid") String paramGuid);

    @Select({"SELECT * FROM vest_info WHERE parent_id=#{parentID}"})
    List<VestInfo> getAllByParentID(@Param("parentID") String paramParentID);

    @Select({"SELECT id,guid,parent_id,x,y,width,height,probability FROM vest_info"})
    List<VestInfo> getAll();
}
