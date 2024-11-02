package land.leets.Carrot.domain.user.service;

import jakarta.transaction.Transactional;
import land.leets.Carrot.domain.user.dto.request.BasicInfoUpdateRequest;
import land.leets.Carrot.domain.user.entity.Ceo;
import land.leets.Carrot.domain.user.entity.Employee;
import land.leets.Carrot.domain.user.entity.User;
import land.leets.Carrot.domain.user.exception.UserNotFoundException;
import land.leets.Carrot.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileService {
    private final UserRepository userRepository;

    @Transactional
    public void updateBasicInfo(BasicInfoUpdateRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(UserNotFoundException::new);

        // 공통 필드 업데이트
        user.updateBasicInfo(request.getGender(), request.getBirthYear());

        // Employee와 Ceo에 따라 분리
        if (user instanceof Employee employee) {
            employee.updateEmployeeInfo(
                    request.getPhoneNumber(),
                    request.getEmployeeName(),
                    request.getEmployeeAddress()
            );
        } else if (user instanceof Ceo ceo) {
            ceo.updateCeoInfo(
                    request.getCeoPhoneNumber(),
                    request.getCeoName(),
                    request.getCeoAddress()
            );
        }
    }
}
