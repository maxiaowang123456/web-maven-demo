package com.company.mapper;

import com.company.pojo.WorkCard;

public interface WorkCardMapper {
    WorkCard getWorkCardByEmpId(Long empId);
}
