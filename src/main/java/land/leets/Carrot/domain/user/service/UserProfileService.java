package land.leets.Carrot.domain.user.service;

import jakarta.transaction.Transactional;
import land.leets.Carrot.domain.user.dto.request.BasicInfoUpdateRequest;
import land.leets.Carrot.domain.user.dto.request.EmployeeAdditionalInfoUpdateRequest;
import land.leets.Carrot.domain.user.dto.request.EmployeeCareerUpdateRequest;
import land.leets.Carrot.domain.user.dto.request.EmployeeSelfIntroUpdateRequest;
import land.leets.Carrot.domain.user.dto.request.EmployeeStrengthUpdateRequest;
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
            employee.updateCareer(
                    request.getWorkplace(),
                    request.getWorkType(),
                    request.getWorkYear(),
                    request.getWorkPeriod()
            );
        } else {
            throw new InvalidUserTypeException();
        }
    }

    @Transactional
    public void updateSelfIntro(EmployeeSelfIntroUpdateRequest request, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        if (user instanceof Employee employee) {
            employee.updateSelfIntro(request.getSelfIntro());
        } else {
            throw new InvalidUserTypeException();
        }
    }

    @Transactional
    public void updateAdditionalInfo(EmployeeAdditionalInfoUpdateRequest request, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        if (user instanceof Employee employee) {
            employee.updateAdditionalInfo(
                    request.isSmoke(),
                    request.isLongWork(),
                    request.isCarLicense(),
                    request.isEnglish(),
                    request.isMilitary(),
                    request.isCookLicense()
            );
        } else {
            throw new InvalidUserTypeException();
        }
    }

    @Transactional
    public void updateStrength(EmployeeStrengthUpdateRequest request, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        if (user instanceof Employee employee) {
            employee.updateStrengths(
                    request.isDiligent(),
                    request.isOnTime(),
                    request.isClean(),
                    request.isNearHome(),
                    request.isSleepless()
            );
        } else {
            throw new InvalidUserTypeException();
        }
    }

    @Transactional
    public void updateProfileImageUrl(Long userId, String imageUrl) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        user.updateProfileImageUrl(imageUrl);  // 새로운 메서드를 통한 업데이트
    }
}
