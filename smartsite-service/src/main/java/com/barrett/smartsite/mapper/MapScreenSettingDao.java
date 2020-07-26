package com.barrett.smartsite.mapper;

import com.barrett.smartsite.bean.MapScreenSetting;

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
 * @create: 2019-10-07 11:34
 **/
@Mapper
public interface MapScreenSettingDao {
    @Insert({"INSERT INTO map_screen_setting(id,main_map_type,map_loop_interval,data_loop_interval,data_type_one,data_type_two,all_display_data,all_display_maps,loop_map,loop_data) VALUES (#{id},#{main_map_type},#{map_loop_interval},#{data_loop_interval},#{data_type_one},#{data_type_two},#{all_display_data},#{all_display_maps},#{loop_map},#{loop_data})"})
    int insert(MapScreenSetting paramMapScreenSetting);

    @Update({"UPDATE map_screen_setting SET main_map_type=#{main_map_type},map_loop_interval=#{map_loop_interval},data_loop_interval=#{data_loop_interval},data_type_one=#{data_type_one},data_type_two=#{data_type_two},all_display_data=#{all_display_data},all_display_maps=#{all_display_maps},loop_map=#{loop_map},loop_data=#{loop_data} WHERE id=#{id}"})
    int update(MapScreenSetting paramMapScreenSetting);

    @Delete({"DELETE FROM map_screen_setting WHERE id=#{id}"})
    int delete(@Param("id") int paramInt);

    @Delete({"DELETE FROM map_screen_setting"})
    int deleteAll();

    @Select({"SELECT * FROM map_screen_setting WHERE id=#{id}"})
    List<MapScreenSetting> getAllById(@Param("id") int paramInt);

    @Select({"SELECT id,main_map_type,map_loop_interval,data_loop_interval,data_type_one,data_type_two,all_display_data,all_display_maps,loop_map,loop_data FROM map_screen_setting"})
    List<MapScreenSetting> getAll();
}
