package land.leets.Carrot.domain.user.service;

import jakarta.transaction.Transactional;
import land.leets.Carrot.domain.user.dto.request.CeoSignupRequest;
import land.leets.Carrot.domain.user.entity.Ceo;
import land.leets.Carrot.domain.user.exception.CeoNameAlreadyExistsException;
import land.leets.Carrot.domain.user.exception.CeoNumberAlreadyExistsException;
import land.leets.Carrot.domain.user.exception.EmailAlreadyExistsException;
import land.leets.Carrot.domain.user.exception.TelAlreadyExistsException;
import land.leets.Carrot.domain.user.repository.CeoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CeoSignupService {
    private final CeoRepository ceoRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void ceoSignup(CeoSignupRequest request) {
        validateCeo(request);
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        Ceo ceo = new Ceo(
                request.getEmail(), encodedPassword, request.getCeoName(), request.getCeoPhoneNumber(),
                request.getCeoNumber(),
                request.getStoreName(), request.getOpenDate(), request.getCeoAddress()
        );
        ceoRepository.save(ceo);
    }

    private void validateCeo(CeoSignupRequest request) {
        if (ceoRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException();
        }
        if (ceoRepository.existsByCeoPhoneNumber(request.getCeoPhoneNumber())) {
            throw new TelAlreadyExistsException();
        }
        if (ceoRepository.existsByCeoNumber(request.getCeoNumber())) {
            throw new CeoNumberAlreadyExistsException();
        }
        if (ceoRepository.existsByCeoName(request.getCeoName())) {
            throw new CeoNameAlreadyExistsException();
        }
    }
}
