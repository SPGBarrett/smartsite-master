package com.barrett.smartsite.mapper;

import com.barrett.smartsite.bean.ScreenInfo;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @program: smartsite
 * @description:
 * @author: Barrett
 * @create: 2019-10-07 11:37
 **/
@Mapper
public interface ScreenInfoDao {
    @Insert({"INSERT INTO screen_info(id,screen_num,screen_name,screen_content,content_type,create_date,modify_date,create_user,width,length,resolution_x,resolution_y,state,memo,data_info) VALUES (#{id},#{screen_num},#{screen_name},#{screen_content},#{content_type},#{create_date},#{modify_date},#{create_user},#{width},#{length},#{resolution_x},#{resolution_y},#{state},#{memo},#{data_info})"})
    int insert(ScreenInfo paramScreenInfo);

    @Update({"UPDATE screen_info SET screen_num=#{screen_num},screen_name=#{screen_name},screen_content=#{screen_content},content_type=#{content_type},create_date=#{create_date},modify_date=#{modify_date},create_user=#{create_user},width=#{width},length=#{length},resolution_x=#{resolution_x},resolution_y=#{resolution_y},state=#{stat}e,memo=#{memo},data_info=#{data_info} WHERE id=#{id}"})
    int update(ScreenInfo paramScreenInfo);

    @Delete({"DELETE FROM screen_info WHERE id=#{id}"})
    int delete(@Param("id") int paramInt);

    @Delete({"DELETE FROM screen_info"})
    int deleteAll();

    @Select({"SELECT * FROM screen_info WHERE id=#{id}"})
    List<ScreenInfo> getAllById(@Param("id") int paramInt);

    @Select({"SELECT id,screen_num,screen_name,screen_content,content_type,create_date,modify_date,create_user,width,length,resolution_x,resolution_y,state,memo,data_info FROM screen_info"})
    List<ScreenInfo> getAll();
}
