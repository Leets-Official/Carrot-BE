package land.leets.Carrot.domain.user.service;

import jakarta.transaction.Transactional;
import land.leets.Carrot.domain.user.dto.request.BasicInfoUpdateRequest;
import land.leets.Carrot.domain.user.dto.request.EmployeeCareerUpdateRequest;
import land.leets.Carrot.domain.user.dto.response.CeoProfileResponse;
import land.leets.Carrot.domain.user.dto.response.EmployeeProfileResponse;
import land.leets.Carrot.domain.user.entity.Ceo;
import land.leets.Carrot.domain.user.entity.Employee;
import land.leets.Carrot.domain.user.entity.User;
import land.leets.Carrot.domain.user.exception.InvalidUserTypeException;
import land.leets.Carrot.domain.user.exception.UnknownUserTypeException;
import land.leets.Carrot.domain.user.exception.UserNotFoundException;
import land.leets.Carrot.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileService {
    private final UserRepository userRepository;

    @Transactional
    public Object check(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        if (user instanceof Employee employee) {
            return new EmployeeProfileResponse(employee);
        } else if (user instanceof Ceo ceo) {
            return new CeoProfileResponse(ceo);
        } else {
            throw new UnknownUserTypeException();
        }
    }

    @Transactional
    public void updateBasicInfo(BasicInfoUpdateRequest request, Long userId) {
        User user = userRepository.findById(userId)
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

    @Transactional
    public void updateCareer(EmployeeCareerUpdateRequest request, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        if (user instanceof Employee employee) {
            employee.updateCareer(request.getCareer());
        } else {
            throw new InvalidUserTypeException();
        }
    }
}
