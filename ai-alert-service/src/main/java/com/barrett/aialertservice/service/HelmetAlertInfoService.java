package com.barrett.aialertservice.service;

import com.barrett.aialertservice.vm.AIAlertPushMsg;
import com.barrett.aialertservice.vm.HelmetAlertInfoInput;
import com.barrett.aialertservice.vm.HelmetAlertInfoOutput;

import java.util.List;

public interface HelmetAlertInfoService {
    List<HelmetAlertInfoOutput> getByGuid(String paramGuid);

    List<HelmetAlertInfoOutput> getAll();

    AIAlertPushMsg insertHelmetAlertData(HelmetAlertInfoInput inputData);

    AIAlertPushMsg filterAlertPushData(String Guid);
}
