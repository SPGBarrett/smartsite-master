package com.barrett.smartsite.mapper;
import com.barrett.smartsite.bean.MultiFiles;
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
public interface MultiFilesDao {
    @Insert({"INSERT INTO multi_files(id,path,suffix,time,url_link,parent,type) VALUES (#{id},#{path},#{suffix},#{time},#{url_link},#{parent},#{type})"})
    int insert(MultiFiles paramMultiFiles);

    @Update({"UPDATE multi_files SET path=#{path},suffix=#{suffix},time=#{time},url_link=#{url_link},parent=#{parent},type=#{type} WHERE id=#{id}"})
    int update(MultiFiles paramMultiFiles);

    @Delete({"DELETE FROM multi_files WHERE id=#{id}"})
    int delete(@Param("id") int paramInt);

    @Delete({"DELETE FROM multi_files"})
    int deleteAll();

    @Delete({"DELETE FROM multi_files WHERE type=#{type} AND parent=#{parent}"})
    int deleteAllByParams(@Param("type") String typeString, @Param("parent") String parentString);

    @Select({"SELECT * FROM multi_files WHERE id=#{id}"})
    List<MultiFiles> getAllById(@Param("id") int paramInt);

    @Select({"SELECT id,path,suffix,time,url_link,parent,type FROM multi_files"})
    List<MultiFiles> getAll();

    @Select({"SELECT * FROM multi_files WHERE parent=#{parent}"})
    List<MultiFiles> getAllByParent(@Param("parent") String paramString);

    @Select({"SELECT * FROM multi_files WHERE type=#{type}"})
    List<MultiFiles> getAllByType(@Param("type") String paramString);

    @Select({"SELECT * FROM multi_files WHERE type=#{type} AND parent=#{parent}"})
    List<MultiFiles> getAllByParams(@Param("type") String typeString, @Param("parent") String parentString);
}
