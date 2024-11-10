package land.leets.Carrot.domain.user.service;

import jakarta.transaction.Transactional;
import land.leets.Carrot.domain.user.dto.request.AdditionalInfoUpdateRequest;
import land.leets.Carrot.domain.user.dto.request.BasicInfoUpdateRequest;
import land.leets.Carrot.domain.user.dto.request.CareerUpdateRequest;
import land.leets.Carrot.domain.user.dto.request.SelfIntroUpdateRequest;
import land.leets.Carrot.domain.user.dto.request.StrengthUpdateRequest;
import land.leets.Carrot.domain.user.dto.response.EmployeeProfileResponse;
import land.leets.Carrot.domain.user.dto.response.UserBasicInfoResponse;
import land.leets.Carrot.domain.user.entity.Ceo;
import land.leets.Carrot.domain.user.entity.Employee;
import land.leets.Carrot.domain.user.entity.User;
import land.leets.Carrot.domain.user.exception.InvalidUserTypeException;
import land.leets.Carrot.domain.user.exception.UserNotFoundException;
import land.leets.Carrot.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileService {
    private final UserRepository userRepository;

    @Transactional
    public UserBasicInfoResponse check(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        return UserBasicInfoResponse.from(user);
    }

    @Transactional
    public void updateBasicInfo(BasicInfoUpdateRequest request, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        // 공통 필드 업데이트
        user.updateBasicInfo(request.gender(), request.birthYear());

        // Employee와 Ceo에 따라 분리
        if (user instanceof Employee employee) {
            employee.updateEmployeeInfo(
                    request.phoneNumber(),
                    request.employeeName(),
                    request.employeeAddress()
            );
        } else if (user instanceof Ceo ceo) {
            ceo.updateCeoInfo(
                    request.ceoPhoneNumber(),
                    request.ceoName(),
                    request.ceoAddress()
            );
        }
    }

    @Transactional
    public void updateCareer(CareerUpdateRequest request, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        if (user instanceof Employee employee) {
            employee.updateCareer(
                    request.workplace(),
                    request.workType(),
                    request.workYear(),
                    request.workPeriod()
            );
        } else {
            throw new InvalidUserTypeException();
        }
    }

    @Transactional
    public void updateSelfIntro(SelfIntroUpdateRequest request, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        if (user instanceof Employee employee) {
            employee.updateSelfIntro(request.selfIntro());
        } else {
            throw new InvalidUserTypeException();
        }
    }

    @Transactional
    public void updateAdditionalInfo(AdditionalInfoUpdateRequest request, Long userId) {
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
    public void updateStrength(StrengthUpdateRequest request, Long userId) {
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

    @Transactional
    public EmployeeProfileResponse employeeAll(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        if (user instanceof Employee employee) {
            return EmployeeProfileResponse.from(employee);
        } else {
            throw new InvalidUserTypeException();
        }
    }
}
