package com.barrett.smartsite.mapper;
import com.barrett.smartsite.bean.MainScreenSetting;
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
 * @create: 2019-10-07 11:33
 **/
@Mapper
public interface MainScreenSettingDao {
    @Insert({"INSERT INTO main_screen_setting(id,selected_screen,loop_interval,main_screen,loop_screen) VALUES (#{id},#{selected_screen},#{loop_interval},#{main_screen},#{loop_screen})"})
    int insert(MainScreenSetting paramMainScreenSetting);

    @Update({"UPDATE main_screen_setting SET selected_screen=#{selected_screen},loop_interval=#{loop_interval},main_screen=#{main_screen},loop_screen=#{loop_screen} WHERE id=#{id}"})
    int update(MainScreenSetting paramMainScreenSetting);

    @Update({"UPDATE main_screen_setting SET loop_screen=#{isloop}"})
    int updateIsloop(@Param("isloop") int isloop);

    @Delete({"DELETE FROM main_screen_setting WHERE id=#{id}"})
    int delete(@Param("id") int paramInt);

    @Delete({"DELETE FROM main_screen_setting"})
    int deleteAll();

    @Select({"SELECT * FROM main_screen_setting WHERE id=#{id}"})
    List<MainScreenSetting> getAllById(@Param("id") int paramInt);

    @Select({"SELECT id,selected_screen,loop_interval,main_screen,loop_screen FROM main_screen_setting"})
    List<MainScreenSetting> getAll();
}
