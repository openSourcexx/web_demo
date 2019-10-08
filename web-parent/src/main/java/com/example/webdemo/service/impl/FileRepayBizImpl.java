package com.example.webdemo.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.webdemo.beans.CreditFileRepay;
import com.example.webdemo.common.utils.FileReadUtil;
import com.example.webdemo.dao.CreditFileRepayMapper;
import com.example.webdemo.service.biz.FileRepayBiz;

/**
 * todo
 *
 * @author admin
 * @since 2019/10/8 15:53
 */
@Service
public class FileRepayBizImpl implements FileRepayBiz {
    @Autowired
    private CreditFileRepayMapper creditFileRepayMapper;
    @Autowired
    private FileReadUtil fileReadUtil;

    @Value("${insertLine}")
    private String insertLine;

    @Override
    public void batchInsert() {
        List<String> list = fileReadUtil.readFile("bb.txt");
        int i = 0;
        int insertLength = list.size();
        int j = Integer.parseInt(insertLine);
        List<CreditFileRepay> insertList;
        while (insertLength > j) {
            List<String> subList = list.subList(i, i + j);
            i = i + j;
            insertLength = insertLength - j;
            insertList = buildDataList(subList);
            creditFileRepayMapper.insertList(insertList);
        }
        if (insertLength > 0) {
            List<String> subList = list.subList(i, i + insertLength);
            insertList = buildDataList(subList);
            creditFileRepayMapper.insertList(insertList);
        }

    }

    private List<CreditFileRepay> buildDataList(List<String> list) {
        List<CreditFileRepay> insertList = new ArrayList<>();
        CreditFileRepay repay;
        for (String e : list) {
            String[] line = e.split("\\|");
            repay = new CreditFileRepay();
            repay.setTenantId("000");
            repay.setChannelId("01");
            repay.setApplyId(line[0]);
            repay.setRepayNum(Integer.valueOf(line[1]));
            repay.setRepayDate(line[2]);
            repay.setRepayPrincipal(new BigDecimal(line[3]));
            repay.setRepayInterest(new BigDecimal(line[4]));
            repay.setRepayPenaltyInterest(new BigDecimal(line[5]));
            repay.setRepayFlag("3");
            repay.setRemarks(line[7]);
            repay.setCreateTime(new Date());
            repay.setUpdateTime(new Date());
            insertList.add(repay);
        }
        return insertList;
    }
}
