package org.nhathm.config;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.errors.MinioException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;


@Data
@Configuration
public class MinioConfig {

    public static final String PUBLIC_BUCKET_NAME = "pub";

    public static final String COMMON_BUCKET_NAME = "common";

    public static final int PRESIGNED_URL_EXPIRY = 60 * 60 * 24;


    @Autowired
    private SimpMessagingTemplate template;

    @Value("${minio.url}")
    private String minioUrl;

    @Value("${minio.username}")
    private String minioUsername;

    @Value("${minio.password}")
    private String minioPassword;

    @Bean
    public MinioClient minioClient() throws Exception {
        try {
            MinioClient minioClient = MinioClient.builder()
                    .endpoint(minioUrl)
                    .credentials(minioUsername, minioPassword)
                    .build();

            if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(COMMON_BUCKET_NAME).build())) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(COMMON_BUCKET_NAME).build());
            }

            if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(PUBLIC_BUCKET_NAME).build())) {
                minioClient.makeBucket(MakeBucketArgs.builder()
                        .bucket(PUBLIC_BUCKET_NAME).build());
            }


            template.convertAndSend("/topic/times", "any3d");


//            minioClient.uploadObject(
//                    UploadObjectArgs.builder()
//                            .bucket(COMMON_BUCKET_NAME)
//                            .object("docker-compose.yml")
//                            .filename("D:\\cola\\any3d\\docker-compose.yml")
//                            .build());

            return minioClient;
        } catch (MinioException e) {
            throw new Exception("Error occurred: " + e);
        }
    }

    public String getPublicUrl(String objectName) {
        return minioUrl + "/" + PUBLIC_BUCKET_NAME + "/" + objectName;
    }
}
