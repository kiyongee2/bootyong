package com.shop.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.extern.java.Log;

@Service
@Log
public class FileService {
	public String uploadFile(String uploadPath, String originalFileName, byte[] fileData) throws Exception{
        
		UUID uuid = UUID.randomUUID();   //서로 다른 개체들을 구별하기 위해 이름을 부여할 때 사용.
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));  //확장자
        String savedFileName = uuid.toString() + extension;  //파일이름 + 확장자
        String fileUploadFullUrl = uploadPath + "/" + savedFileName;
        
        //바이트 단위로 파일 쓰기(출력)
        FileOutputStream fos = new FileOutputStream(fileUploadFullUrl);
        fos.write(fileData);
        fos.close();
        return savedFileName;
    }

    public void deleteFile(String filePath) throws Exception{
        File deleteFile = new File(filePath);
        if(deleteFile.exists()) {
            deleteFile.delete();
            log.info("파일을 삭제하였습니다.");
        } else {
            log.info("파일이 존재하지 않습니다.");
        }
    }
}
