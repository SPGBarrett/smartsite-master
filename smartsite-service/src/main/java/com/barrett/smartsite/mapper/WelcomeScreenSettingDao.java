package com.barrett.smartsite.mapper;
import com.barrett.smartsite.bean.WelcomeScreenSetting;
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
public interface WelcomeScreenSettingDao {
    @Insert({"INSERT INTO welcome_screen_setting(id,welcome_text,bg_mode,bg_color,pic_path,txt_pos_x,txt_pos_y,txt_font,txt_size,txt_color,line_height) VALUES (#{id},#{welcome_text},#{bg_mode},#{bg_color},#{pic_path},#{txt_pos_x},#{txt_pos_y},#{txt_font},#{txt_size},#{txt_color},#{line_height})"})
    int insert(WelcomeScreenSetting paramWelcomeScreenSetting);

    @Update({"UPDATE welcome_screen_setting SET welcome_text=#{welcome_text},bg_mode=#{bg_mode},bg_color=#{bg_color},txt_pos_x=#{txt_pos_x},txt_pos_y=#{txt_pos_y},txt_font=#{txt_font},txt_size=#{txt_size},txt_color=#{txt_color},line_height=#{line_height}"})
    int update(WelcomeScreenSetting paramWelcomeScreenSetting);

    @Update({"UPDATE welcome_screen_setting SET pic_path=#{pic_path} WHERE id=#{id}"})
    int updatePicPath(@Param("id") int id, @Param("pic_path") String pic_path);

    @Delete({"DELETE FROM welcome_screen_setting WHERE id=#{id}"})
    int delete(@Param("id") int paramInt);

    @Delete({"DELETE FROM welcome_screen_setting"})
    int deleteAll();

    @Select({"SELECT * FROM welcome_screen_setting WHERE id=#{id}"})
    List<WelcomeScreenSetting> getAllById(@Param("id") int paramInt);

    @Select({"SELECT id,welcome_text,bg_mode,bg_color,pic_path,txt_pos_x,txt_pos_y,txt_font,txt_size,txt_color,line_height FROM welcome_screen_setting"})
    List<WelcomeScreenSetting> getAll();
}
