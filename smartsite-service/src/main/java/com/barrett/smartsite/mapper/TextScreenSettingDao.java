package com.barrett.smartsite.mapper;

import com.barrett.smartsite.bean.TextScreenSetting;

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
 * @create: 2019-10-07 11:39
 **/
@Mapper
public interface TextScreenSettingDao {
    @Insert({"INSERT INTO text_screen_setting (id,total_num,loop_interval,trans_style,loop_data) VALUES (#{id},#{total_num},#{loop_interval},#{trans_style},#{loop_data})"})
    int insert(TextScreenSetting paramTextScreenSetting);

    @Update({"UPDATE text_screen_setting SET total_num=#{total_num},loop_interval=#{loop_interval},trans_style=#{trans_style},loop_data=#{loop_data} WHERE id=#{id}"})
    int update(TextScreenSetting paramTextScreenSetting);

    @Delete({"DELETE FROM text_screen_setting WHERE id=#{id}"})
    int delete(@Param("id") int paramInt);

    @Delete({"DELETE FROM text_screen_setting"})
    int deleteAll();

    @Select({"SELECT * FROM text_screen_setting WHERE id=#{id}"})
    List<TextScreenSetting> getAllById(@Param("id") int paramInt);

    @Select({"SELECT id,total_num,loop_interval,trans_style,loop_data FROM text_screen_setting"})
    List<TextScreenSetting> getAll();
}
