package land.leets.Carrot.domain.user.dto.request;

public record CeoSignupRequest(
        String email,
        String password,
        String ceoName,
        String ceoPhoneNumber,
        String ceoNumber,
        String storeName,
        String openDate,
        String ceoAddress
) {
    public static CeoSignupRequest of(String email,
                                      String password,
                                      String ceoName,
                                      String ceoPhoneNumber,
                                      String ceoNumber,
                                      String storeName,
                                      String openDate,
                                      String ceoAddress) {
        return new CeoSignupRequest(email, password, ceoName, ceoPhoneNumber, ceoNumber, storeName, openDate,
                ceoAddress);
    }
}
