package com.barrett.smartsite.mapper;

import com.barrett.smartsite.bean.DataScreenSetting;

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
 * @create: 2019-10-07 11:29
 **/
@Mapper
public interface DataScreenSettingDao {
    @Insert({"INSERT INTO data_screen_setting(id,loop_interval,data_id_one,data_id_two,data_id_three,data_id_four,data_id_five,data_id_six,data_id_seven,data_id_eight,loop_data) VALUES (#{id},#{loop_interval},#{data_id_one},#{data_id_two},#{data_id_three},#{data_id_four},#{data_id_five},#{data_id_six},#{data_id_seven},#{data_id_eight},#{loop_data})"})
    int insert(DataScreenSetting paramDataScreenSetting);

    @Update({"UPDATE data_screen_setting SET loop_interval=#{loop_interval},data_id_one=#{data_id_one},data_id_two=#{data_id_two},data_id_three=#{data_id_three},data_id_four=#{data_id_four},data_id_five=#{data_id_five},data_id_six=#{data_id_six},data_id_seven=#{data_id_seven},data_id_eight=#{data_id_eight},loop_data=#{loop_data} WHERE id=#{id}"})
    int update(DataScreenSetting paramDataScreenSetting);

    @Delete({"DELETE FROM data_screen_setting WHERE id=#{id}"})
    int delete(@Param("id") int paramInt);

    @Delete({"DELETE FROM data_screen_setting"})
    int deleteAll();

    @Select({"SELECT * FROM data_screen_setting WHERE id=#{id}"})
    List<DataScreenSetting> getAllById(@Param("id") int paramInt);

    @Select({"SELECT id,loop_interval,data_id_one,data_id_two,data_id_three,data_id_four,data_id_five,data_id_six,data_id_seven,data_id_eight,loop_data FROM data_screen_setting"})
    List<DataScreenSetting> getAll();
}
