package jlu.nan.service.impl;


import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import jlu.nan.common.enums.ExectionEnum;
import jlu.nan.common.exception.CmException;
import jlu.nan.config.UploadConfig;
import jlu.nan.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;


/**
 * @ClassName: UploadServiceImpl
 * @Description:
 * @author: Nan
 * @date: 2020/3/25 22:35
 * @version: V1.0
 */

@Service
@Slf4j
@EnableConfigurationProperties(UploadConfig.class)
public class UploadServiceImpl implements UploadService {

    @Autowired
    private UploadConfig config;

    @Autowired
    private FastFileStorageClient storageClient;




    @Override
    public String uploadImg(MultipartFile file) {
//       校验文件类型
        try {
        String contentType = file.getContentType();
        if(!config.getAllowTypes().contains(contentType)){
            throw new CmException(ExectionEnum.FILE_TYPE_NOT_ALLOW);
        }

//        检验内容
        BufferedImage image = ImageIO.read(file.getInputStream());
            if (image==null){
                throw new CmException(ExectionEnum.FILE_TYPE_NOT_ALLOW);
            }

        String suffix = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");

        StorePath storePath = storageClient.uploadImageAndCrtThumbImage(file.getInputStream(),
                file.getSize(),
                suffix, null);
                 return config.getBaseUrl()+storePath.getFullPath();
        } catch (IOException e) {
            log.error("[文件上传] 文件上传失败",e);
             throw new CmException(ExectionEnum.UPLOAD_FILE_ERROR);
        }

    }
}
