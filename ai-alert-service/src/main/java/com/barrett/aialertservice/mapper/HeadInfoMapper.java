package com.barrett.aialertservice.mapper;

import com.barrett.aialertservice.bean.HeadInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface HeadInfoMapper {
    @Insert({"INSERT INTO head_info(id,guid,parent_id,x,y,width,height,num_of_helmet,color) VALUES (#{id},#{guid},#{parent_id},#{x},#{y},#{width},#{height},#{num_of_helmet},#{color})"})
    int insert(HeadInfo headInfo);

    @Update({"UPDATE head_info SET guid=#{guid},parent_id=#{parent_id},x=#{x},y=#{y},width=#{width},height=#{height},num_of_helmet=#{num_of_helmet},color=#{color} WHERE id=#{id}"})
    int update(HeadInfo headInfo);

    @Delete({"DELETE FROM head_info WHERE guid=#{guid}"})
    int deleteByGuid(@Param("guid") int paramGuid);

    @Delete({"DELETE FROM head_info"})
    int deleteAll();

    @Select({"SELECT * FROM head_info WHERE guid=#{guid}"})
    List<HeadInfo> getAllByGuid(@Param("guid") String paramGuid);

    @Select({"SELECT * FROM head_info WHERE parent_id=#{parentID}"})
    List<HeadInfo> getAllByParentID(@Param("parentID") String paramParentID);

    @Select({"SELECT id,guid,parent_id,x,y,width,height,num_of_helmet,color FROM head_info"})
    List<HeadInfo> getAll();
}
