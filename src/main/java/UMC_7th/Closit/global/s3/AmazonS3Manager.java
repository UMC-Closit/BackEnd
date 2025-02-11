package UMC_7th.Closit.global.s3;

import UMC_7th.Closit.global.config.AmazonConfig;
import UMC_7th.Closit.global.s3.entity.Uuid;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class AmazonS3Manager {

    private final AmazonS3 amazonS3;

    private final AmazonConfig amazonConfig;

    private final UuidRepository uuidRepository;

    public String uploadFile(String keyName, MultipartFile file){
        ObjectMetadata metadata = new ObjectMetadata();
        log.info("File size: {}", file.getSize());
        log.info("File extension: {}", file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));
        log.info("Original file name: {}", file.getOriginalFilename());

        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());
        try {
            amazonS3.putObject(new PutObjectRequest(amazonConfig.getBucket(), keyName, file.getInputStream(), metadata));
        } catch (IOException e){
            log.error("error at AmazonS3Manager uploadFile : {}", (Object) e.getStackTrace());
        }

        return amazonS3.getUrl(amazonConfig.getBucket(), keyName).toString();
    }

    public void deleteFile (String profileImage) {
        if (profileImage == null || profileImage.isEmpty()) {
            log.info("profileImage is null or empty");
            return;
        }

        try {
            String fileKey = extractS3Key(profileImage);
            amazonS3.deleteObject(amazonConfig.getBucket(), fileKey);
            log.info("file deleted successfully");
        } catch (Exception e) {
            log.error("error at AmazonS3Manager deleteFile : {}", (Object) e.getStackTrace());
        }


        amazonS3.deleteObject(amazonConfig.getBucket(), profileImage);
    }

    private String extractS3Key(String fileUrl) {
        String bucketUrl = "https://" + amazonConfig.getBucket() + ".s3." + amazonConfig.getRegion() + ".amazonaws.com/";

        if (fileUrl.startsWith(bucketUrl)) {
            return fileUrl.substring(bucketUrl.length());
        }

        throw new IllegalArgumentException("잘못된 S3 파일 URL: " + fileUrl);
    }

    public String generateTestKeyName(Uuid uuid) {
        return amazonConfig.getTestPath() + '/' + uuid.getUuid();
    }

    public String generateProfileImageKeyName(Uuid uuid) {
        return amazonConfig.getProfileImagePath() + '/' + uuid.getUuid();
    }


}
