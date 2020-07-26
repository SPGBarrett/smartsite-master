package com.barrett.smartsite.mapper;

import com.barrett.smartsite.bean.MonitorScreenSetting;

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
 * @create: 2019-10-07 11:36
 **/
@Mapper
public interface MonitorScreenSettingDao {
    @Insert({"INSERT INTO monitor_screen_setting(id,display_type,loop_interval,monitor_id_one,monitor_id_two,monitor_id_three,monitor_id_four,monitor_id_five,monitor_id_six,monitor_id_seven,monitor_id_eight,loop_data) VALUES (#{id},#{display_type},#{loop_interval},#{monitor_id_one},#{monitor_id_two},#{monitor_id_three},#{monitor_id_four},#{monitor_id_five},#{monitor_id_six},#{monitor_id_seven},#{monitor_id_eight},#{loop_data})"})
    int insert(MonitorScreenSetting paramMonitorScreenSetting);

    @Update({"UPDATE monitor_screen_setting SET display_type=#{display_type},loop_interval=#{loop_interval},monitor_id_one=#{monitor_id_one},monitor_id_two=#{monitor_id_two},monitor_id_three=#{monitor_id_three},monitor_id_four=#{monitor_id_four},monitor_id_five=#{monitor_id_five},monitor_id_six=#{monitor_id_six},monitor_id_seven=#{monitor_id_seven},monitor_id_eight=#{monitor_id_eight},loop_data=#{loop_data} WHERE id=#{id}"})
    int update(MonitorScreenSetting paramMonitorScreenSetting);

    @Delete({"DELETE FROM monitor_screen_setting WHERE id=#{id}"})
    int delete(@Param("id") int paramInt);

    @Delete({"DELETE FROM monitor_screen_setting"})
    int deleteAll();

    @Select({"SELECT * FROM monitor_screen_setting WHERE id=#{id}"})
    List<MonitorScreenSetting> getAllById(@Param("id") int paramInt);

    @Select({"SELECT id,display_type,loop_interval,monitor_id_one,monitor_id_two,monitor_id_three,monitor_id_four,monitor_id_five,monitor_id_six,monitor_id_seven,monitor_id_eight,loop_data FROM monitor_screen_setting"})
    List<MonitorScreenSetting> getAll();
}
