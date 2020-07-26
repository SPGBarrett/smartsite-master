package com.barrett.smartsite.mapper;

import com.barrett.smartsite.bean.ProgScreenSetting;
import com.barrett.smartsite.bean.ScreenInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Description:
 * @Param:
 * @return:
 * @Author: Barrett
 * @Date:
 */
@Mapper
public interface ProgScreenSettingDao {
    @Insert({"INSERT INTO prog_screen_setting(id,screen_no,start_time,end_time,set_time,set_user) VALUES (#{id},#{screen_no},#{start_time},#{end_time},#{set_time},#{set_user})"})
    int insert(ProgScreenSetting progScreenSetting);

    @Update({"UPDATE prog_screen_setting SET screen_no=#{screen_no},start_time=#{start_time},end_time=#{end_time},set_time=#{set_time},set_user=#{set_user} WHERE id=#{id}"})
    int update(ProgScreenSetting progScreenSetting);

    @Delete({"DELETE FROM prog_screen_setting WHERE id=#{id}"})
    int delete(@Param("id") int paramInt);

    @Delete({"DELETE FROM prog_screen_setting"})
    int deleteAll();

    @Select({"SELECT * FROM prog_screen_setting WHERE id=#{id}"})
    List<ProgScreenSetting> getAllById(@Param("id") int paramInt);

    @Select({"SELECT id,screen_no,start_time,end_time,set_time,set_user FROM prog_screen_setting"})
    List<ProgScreenSetting> getAll();

}
