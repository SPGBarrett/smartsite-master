package com.barrett.facedetectservice.mapper;

import com.barrett.facedetectservice.bean.UserData;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDataMapper {

    @Insert({"INSERT INTO user_data(id,guid,user_name,card_no,card_type,work_no,update_date,id_card,status,card_right,auth_type) VALUES (#{id},#{guid},#{user_name},#{card_no},#{card_type},#{work_no},#{update_date},#{id_card},#{status},#{card_right},#{auth_type})"})
    int insert(UserData userData);


    // 存在即更新，否则插入，需要在数据库设置unique字段
    @Insert({"INSERT INTO user_data(id,guid,user_name,card_no,card_type,work_no,update_date,id_card,status,card_right,auth_type)" +
            " VALUES (#{id},#{guid},#{user_name},#{card_no},#{card_type},#{work_no},#{update_date},#{id_card},#{status},#{card_right},#{auth_type}) " +
            "ON DUPLICATE KEY UPDATE user_name=#{user_name},card_type=#{card_type},work_no=#{work_no},update_date=#{update_date},id_card=#{id_card},status=#{status},card_right=#{card_right},auth_type=#{auth_type}"})
    int insertOrUpdate(UserData userData);

    @Update({"UPDATE user_data SET guid=#{guid},user_name=#{user_name},card_no=#{card_no},card_type=#{card_type},work_no=#{work_no},update_date=#{update_date},id_card=#{id_card},status=#{status},card_right=#{card_right},auth_type=#{auth_type} WHERE id=#{id}"})
    int update(UserData userData);

    @Update({"UPDATE user_data SET user_name=#{user_name},card_no=#{card_no},card_type=#{card_type},update_date=#{update_date},id_card=#{id_card},status=#{status},card_right=#{card_right},auth_type=#{auth_type} WHERE work_no=#{work_no}"})
    int updateByWorkNo(UserData userData);

    @Delete({"DELETE FROM user_data WHERE guid=#{guid}"})
    int deleteByGuid(@Param("guid") int paramGuid);

    @Delete({"DELETE FROM user_data"})
    int deleteAll();

    @Select({"SELECT * FROM user_data WHERE guid=#{guid}"})
    List<UserData> getAllByGuid(@Param("guid") String paramGuid);

    @Select({"SELECT id,guid,user_name,card_no,card_type,work_no,update_date,id_card,status,card_right,auth_type FROM user_data"})
    List<UserData> getAll();

    @Select({"SELECT id FROM user_data WHERE card_no=#{card_no}"})
    List<Integer> getIdByCardNo(@Param("card_no") String card_no);

    @Select({"SELECT * FROM user_data WHERE card_no=#{card_no}"})
    List<UserData> getByCardNo(@Param("card_no") String card_no);

}
