package land.leets.Carrot.domain.career.service;

import static land.leets.Carrot.domain.post.controller.SuccessMessage.GET_WORK_TYPE_LIST_SUCCESS;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import land.leets.Carrot.domain.career.entity.WorkType;
import land.leets.Carrot.domain.career.repository.WorkTypeRepository;
import land.leets.Carrot.domain.post.dto.response.WorkTypeResponse;
import land.leets.Carrot.global.common.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class WorkTypeService {
    WorkTypeRepository workTypeRepository;

    public ResponseDto<WorkTypeResponse> getWorkType() {
        List<WorkType> workTypeList = workTypeRepository.findAll();
        List<String> workTypeNameList = workTypeList.stream()
                .map(WorkType::getType)
                .collect(Collectors.toList());
        return new ResponseDto(GET_WORK_TYPE_LIST_SUCCESS.getCode(), GET_WORK_TYPE_LIST_SUCCESS.getMessage(),
                workTypeNameList);
    }
}
