package com.barrett.aialertservice.service;

import com.barrett.aialertservice.vm.AIAlertPushMsg;
import com.barrett.aialertservice.vm.ClothesAlertInfoInput;
import com.barrett.aialertservice.vm.HelmetAlertInfoInput;
import com.barrett.aialertservice.vm.HelmetAlertInfoOutput;

import java.util.List;

public interface ClothesAlertInfoService {

    AIAlertPushMsg insertClothesAlertData(ClothesAlertInfoInput inputData);

    AIAlertPushMsg filterAlertPushData(String Guid);
}
