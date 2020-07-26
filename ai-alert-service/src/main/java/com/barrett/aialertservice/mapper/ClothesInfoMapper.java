package com.barrett.aialertservice.mapper;

import com.barrett.aialertservice.bean.ClothesInfo;
import com.barrett.aialertservice.bean.HeadInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClothesInfoMapper {
    @Insert({"INSERT INTO clothes_info(id,guid,parent_id,x,y,width,height) VALUES (#{id},#{guid},#{parent_id},#{x},#{y},#{width},#{height})"})
    int insert(ClothesInfo clothesInfo);

    @Update({"UPDATE clothes_info SET guid=#{guid},parent_id=#{parent_id},x=#{x},y=#{y},width=#{width},height=#{height} WHERE id=#{id}"})
    int update(ClothesInfo clothesInfo);

    @Delete({"DELETE FROM clothes_info WHERE guid=#{guid}"})
    int deleteByGuid(@Param("guid") int paramGuid);

    @Delete({"DELETE FROM clothes_info"})
    int deleteAll();

    @Select({"SELECT * FROM clothes_info WHERE guid=#{guid}"})
    List<ClothesInfo> getAllByGuid(@Param("guid") String paramGuid);

    @Select({"SELECT * FROM clothes_info WHERE parent_id=#{parentID}"})
    List<ClothesInfo> getAllByParentID(@Param("parentID") String paramParentID);

    @Select({"SELECT id,guid,parent_id,x,y,width,height FROM clothes_info"})
    List<ClothesInfo> getAll();
}
