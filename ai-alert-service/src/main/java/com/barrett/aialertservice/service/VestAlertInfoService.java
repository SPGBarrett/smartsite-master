package com.barrett.aialertservice.service;

import com.barrett.aialertservice.vm.AIAlertPushMsg;
import com.barrett.aialertservice.vm.HelmetAlertInfoInput;
import com.barrett.aialertservice.vm.HelmetAlertInfoOutput;
import com.barrett.aialertservice.vm.VestAlertInfoInput;

import java.util.List;

public interface VestAlertInfoService {

    AIAlertPushMsg insertVestAlertData(VestAlertInfoInput inputData);

    AIAlertPushMsg filterAlertPushData(String Guid);
}
