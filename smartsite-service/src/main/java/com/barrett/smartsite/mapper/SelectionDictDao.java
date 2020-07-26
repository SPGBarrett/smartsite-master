package com.barrett.smartsite.mapper;

import com.barrett.smartsite.bean.SelectionDict;

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
public interface SelectionDictDao {
    @Insert({"INSERT INTO selection_dict(id,colors,data_types,monitor_ids,bg_types,fonts,font_size,trans_styles,ui_types,map_types) VALUES (#{id},#{colors},#{data_types},#{monitor_ids},#{bg_types},#{fonts},#{font_size},#{trans_styles},#{ui_types},#{map_types})"})
    int insert(SelectionDict paramSelectionDict);

    @Update({"UPDATE selection_dict SET colors=#{colors},data_types=#{data_types},monitor_ids=#{monitor_ids},bg_types=#{bg_types},fonts=#{fonts},font_size=#{font_size},trans_styles=#{trans_styles},ui_types=#{ui_types},map_types=#{map_types} WHERE id=#{id}"})
    int update(SelectionDict paramSelectionDict);

    @Delete({"DELETE FROM selection_dict WHERE id=#{id}"})
    int delete(@Param("id") int paramInt);

    @Delete({"DELETE FROM selection_dict"})
    int deleteAll();

    @Select({"SELECT * FROM selection_dict WHERE id=#{id}"})
    List<SelectionDict> getAllById(@Param("id") int paramInt);

    @Select({"SELECT id,colors,data_types,monitor_ids,bg_types,fonts,font_size,trans_styles,ui_types,map_types FROM selection_dict"})
    List<SelectionDict> getAll();
}
