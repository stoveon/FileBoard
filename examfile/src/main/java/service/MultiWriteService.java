package service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Service
public class MultiWriteService {
	//UUID uuid = UUID.randomUUID();		
	private RandomString rs = new RandomString(8);			//랜덤으로 뽑아올 자릿수 입력
	
	public List<String> fileSave(String title, String content, MultipartHttpServletRequest mpfile) {
		
		String path = "C:\\mulitFile\\upload\\";
		
		File saveDir = new File(path);
		
		if(!(saveDir.exists())) {
			saveDir.mkdirs();
		}
		
		List<String> nameList = new ArrayList<String>();
		
		List<MultipartFile> fileList = mpfile.getFiles("imageFile");
		
		String code = rs.nextString();
		
		for(MultipartFile mf : fileList) {
			String originFileName = mf.getOriginalFilename();
			nameList.add(originFileName);
			
			long fileSize = mf.getSize();
			
			//String saveName = uuid.toString().substring(6) + "_" + originFileName;			
			String saveName = code + "_" + originFileName;		
			
			try {
				mf.transferTo(new File(path + saveName));
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("name : " + saveName);
			System.out.println("size : " + fileSize);
		}
		
		return nameList;
		
	}
}
