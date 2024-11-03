package land.leets.Carrot.domain.user.dto.request;

import land.leets.Carrot.domain.user.entity.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BasicInfoUpdateRequest {
    // 공통
    private Gender gender;
    private Integer birthYear;

    // Employee 관련
    private String phoneNumber;
    private String employeeName;
    private String employeeAddress;

    // Ceo 관련
    private String ceoPhoneNumber;
    private String ceoName;
    private String ceoAddress;
}
