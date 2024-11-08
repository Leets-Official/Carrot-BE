package land.leets.Carrot.domain.user.service;

import static land.leets.Carrot.domain.user.controller.CeoErrorMessage.NOT_CEO_USER;

import land.leets.Carrot.domain.user.controller.SuccessMessage;
import land.leets.Carrot.domain.user.dto.response.GetCeoInfoResponse;
import land.leets.Carrot.domain.user.entity.Ceo;
import land.leets.Carrot.domain.user.exception.CeoException;
import land.leets.Carrot.domain.user.repository.CeoRepository;
import land.leets.Carrot.global.common.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CeoInfoService {
    private final CeoRepository ceoRepository;

    public ResponseDto<GetCeoInfoResponse> getCeoInfo(Long userId) {
        Ceo ceoData = ceoRepository.findByUserId(userId)
                .orElseThrow(() -> new CeoException(NOT_CEO_USER));
        return new ResponseDto(SuccessMessage.GET_CEO_INFO_SUCCESS.getCode(),
                SuccessMessage.GET_CEO_INFO_SUCCESS.getMessage(),
                new GetCeoInfoResponse(ceoData.getCeoName(), ceoData.getCeoNumber(), ceoData.getStoreName()));
    }
}
