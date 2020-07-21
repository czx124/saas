package com.itheima.domain.cargo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Jackson
 * @date 2020/7/21 10:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractProductVO implements Serializable {
    private String customName;
    private String orderNo;
    private String productNo;
    private Integer cNumber;
    private String factoryName;
    private Date deliveryPeriod;
    private Date shipTime;
    private String tradeTerms;















}
