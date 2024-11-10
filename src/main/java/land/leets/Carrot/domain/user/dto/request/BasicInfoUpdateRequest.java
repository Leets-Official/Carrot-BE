package land.leets.Carrot.domain.user.dto.request;

import land.leets.Carrot.domain.user.entity.Gender;


public record BasicInfoUpdateRequest(
        Gender gender,
        Integer birthYear,
        String phoneNumber, // Employee 관련
        String employeeName,
        String employeeAddress,
        String ceoPhoneNumber, // Ceo 관련
        String ceoName,
        String ceoAddress
) {
}