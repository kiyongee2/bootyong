package com.shop.service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import com.shop.entity.ItemImg;
import com.shop.repository.ItemImgRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class ItemImgService {
	
	//application.properties에 설정한 "itemImgLocation" 프로퍼티 값을 읽어옴
	@Value("${itemImgLocation}")
    private String itemImgLocation;

    private final ItemImgRepository itemImgRepository;

    private final FileService fileService;

    //상품 이미지 저장
    public void saveItemImg(ItemImg itemImg, MultipartFile itemImgFile) throws Exception{
        String oriImgName = itemImgFile.getOriginalFilename();  //업로드했던 상품 이미지 파일의 원래 이름
        String imgName = "";  //로컬에 저장된 상품 이미지 파일의 이름
        String imgUrl = "";   //업로드 결과 로컬에 저장된 파일의 이름을 불러오는 경로

        //파일 업로드
        if(!StringUtils.isEmpty(oriImgName)){
            imgName = fileService.uploadFile(itemImgLocation, oriImgName,
                    itemImgFile.getBytes());
            imgUrl = "/images/item/" + imgName;
        }

        //상품 이미지 정보 저장
        itemImg.updateItemImg(oriImgName, imgName, imgUrl);
        itemImgRepository.save(itemImg);
    }

    //상품 이미지 수정
    public void updateItemImg(Long itemImgId, MultipartFile itemImgFile) throws Exception{
    	//상품 이미지 아이디를 이용하여 상품 이미지 엔티티를 조회
        if(!itemImgFile.isEmpty()){
            ItemImg savedItemImg = itemImgRepository.findById(itemImgId)
                    .orElseThrow(EntityNotFoundException::new);

            //기존 이미지 파일 삭제
            if(!StringUtils.isEmpty(savedItemImg.getImgName())) {
                fileService.deleteFile(itemImgLocation+"/"+
                        savedItemImg.getImgName());
            }

            //업데이트한 상품 이미지 파일을 업로드함
            String oriImgName = itemImgFile.getOriginalFilename();
            String imgName = fileService.uploadFile(itemImgLocation, oriImgName, itemImgFile.getBytes());
            String imgUrl = "/images/item/" + imgName;
            savedItemImg.updateItemImg(oriImgName, imgName, imgUrl);
        }
    }
}
