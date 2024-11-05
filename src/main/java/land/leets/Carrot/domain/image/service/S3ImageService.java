package land.leets.Carrot.domain.image.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import land.leets.Carrot.domain.user.exception.FileConversionFailedException;
import land.leets.Carrot.domain.user.exception.InvalidFileExtensionException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3ImageService {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    // 단일 이미지 업로드 메서드
    public String uploadImage(MultipartFile image, String dirName) {
        validateImageFileExtension(image.getOriginalFilename());

        String fileName = createUniqueFileName(image.getOriginalFilename(), dirName);
        log.info("fileName: {}", fileName);

        File uploadFile = convertToFile(image);

        try {
            return uploadToS3(uploadFile, fileName);
        } finally {
            deleteLocalFile(uploadFile);
        }
    }

    // 파일 확장자 유효성 검증
    private void validateImageFileExtension(String filename) {
        String extension = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
        if (!List.of("jpg", "jpeg", "png", "gif").contains(extension)) {
            throw new InvalidFileExtensionException();
        }
    }

    // 고유한 파일명 생성 메서드
    private String createUniqueFileName(String originalFileName, String dirName) {
        String uuid = UUID.randomUUID().toString();
        String uniqueFileName = uuid + "_" + originalFileName.replaceAll("\\s", "_");
        return dirName + "/" + uniqueFileName;
    }

    // MultipartFile을 File로 변환
    private File convertToFile(MultipartFile multipartFile) {
        File file = new File(multipartFile.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(multipartFile.getBytes());
        } catch (IOException e) {
            log.error("파일 변환 중 오류 발생: {}", e.getMessage());
            throw new FileConversionFailedException();
        }
        return file;
    }

    // S3에 업로드 및 URL 반환
    private String uploadToS3(File uploadFile, String fileName) {
        amazonS3.putObject(new PutObjectRequest(bucketName, fileName, uploadFile)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        return amazonS3.getUrl(bucketName, fileName).toString();
    }

    // 로컬 파일 삭제
    private void deleteLocalFile(File file) {
        if (file.delete()) {
            log.info("Local file deleted: {}", file.getName());
        } else {
            log.warn("Failed to delete local file: {}", file.getName());
        }
    }
}
