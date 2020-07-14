package com.barrett.facedetectservice.service;

import com.barrett.facedetectservice.bean.UserData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDataService {
    int insert(UserData userData);

    int insertOrUpdate(UserData userData);

    int update(UserData userData);

    int updateByWorkNo(UserData userData);

    int deleteByGuid(@Param("guid") int paramGuid);

    int deleteAll();

    List<UserData> getAllByGuid(@Param("guid") String paramGuid);

    List<UserData> getAll();

    List<Integer> getIdByCardNo(@Param("card_no") String card_no);

    List<UserData> getByCardNo(@Param("card_no") String card_no);
}
