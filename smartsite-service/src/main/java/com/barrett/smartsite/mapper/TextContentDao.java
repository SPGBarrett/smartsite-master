package com.barrett.smartsite.mapper;

import com.barrett.smartsite.bean.TextContent;

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
 * @create: 2019-10-07 11:38
 **/
@Mapper
public interface TextContentDao {
    @Insert({"INSERT INTO text_content(id,txt_set_num,content,txt_pos_x,txt_pos_y,txt_font,txt_size,txt_color,title_txt) VALUES (#{id},#{txt_set_num},#{content},#{txt_pos_x},#{txt_pos_y},#{txt_font},#{txt_size},#{txt_color},#{title_txt})"})
    int insert(TextContent paramTextContent);

    @Update({"UPDATE text_content SET txt_set_num=#{txt_set_num},content=#{content},txt_pos_x=#{txt_pos_x},txt_pos_y=#{txt_pos_y},txt_font=#{txt_font},txt_size=#{txt_size},txt_color=#{txt_color},title_txt=#{title_txt} WHERE id=#{id}"})
    int update(TextContent paramTextContent);

    @Delete({"DELETE FROM text_content WHERE id=#{id}"})
    int delete(@Param("id") int paramInt);

    @Delete({"DELETE FROM text_content"})
    int deleteAll();

    @Select({"SELECT * FROM text_content WHERE id=#{id}"})
    List<TextContent> getAllById(@Param("id") int paramInt);

    @Select({"SELECT id,txt_set_num,content,txt_pos_x,txt_pos_y,txt_font,txt_size,txt_color,title_txt FROM text_content"})
    List<TextContent> getAll();
}
