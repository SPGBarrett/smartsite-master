package com.barrett.smartsite.mapper;
import com.barrett.smartsite.bean.DemoTest;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
/**
 * @program: smartsite
 * @description:
 * @author: Barrett
 * @create: 2019-10-07 11:32
 **/
@Mapper
public interface DemoTestDao {
    @Select({"SELECT param_one,param_two,demo_one FROM demo_test"})
    List<DemoTest> findAll();

    @Insert({"INSERT INTO demo_test(param_one, param_two, demo_one) VALUES(#{paramOne}, #{paramTwo}, #{demoOne})"})
    int insert(@Param("paramOne") String paramString1, @Param("paramTwo") String paramString2, @Param("demoOne") String paramString3);
}
