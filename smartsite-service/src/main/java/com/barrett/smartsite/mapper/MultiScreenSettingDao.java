package com.barrett.smartsite.mapper;
import com.barrett.smartsite.bean.MultiScreenSetting;
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
public interface MultiScreenSettingDao {
    @Insert({"INSERT INTO multi_screen_setting(id,display_type,multi_path,loop_interval,trans_style) VALUES (#{id},#{display_type},#{multi_path},#{loop_interval},#{trans_style})"})
    int insert(MultiScreenSetting paramMultiScreenSetting);

    @Update({"UPDATE multi_screen_setting SET display_type=#{display_type},multi_path=#{multi_path},loop_interval=#{loop_interval},trans_style=#{trans_style} WHERE id=#{id}"})
    int update(MultiScreenSetting paramMultiScreenSetting);

    @Delete({"DELETE FROM multi_screen_setting WHERE id=#{id}"})
    int delete(@Param("id") int paramInt);

    @Delete({"DELETE FROM multi_screen_setting"})
    int deleteAll();

    @Select({"SELECT * FROM multi_screen_setting WHERE id=#{id}"})
    List<MultiScreenSetting> getAllById(@Param("id") int paramInt);

    @Select({"SELECT id,display_type,multi_path,loop_interval,trans_style FROM multi_screen_setting"})
    List<MultiScreenSetting> getAll();
}
