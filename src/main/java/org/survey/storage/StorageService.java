package org.survey.storage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnails;

@Scope(value="prototype")
@Service
public class StorageService {

	private static final Logger log = LoggerFactory.getLogger(StorageService.class);
    static final ByteArrayResource blankPng =new ByteArrayResource(new byte[]{(byte) 0x89, (byte) 0x50, (byte) 0x4e, (byte) 0x47, 0xd, 0xa, (byte) 0x1a, 0xa, 0x0, 0x0, 0x0, 0xd, (byte) 0x49, (byte) 0x48, (byte) 0x44, (byte) 0x52, 0x0, 0x0, 0x0, 0x1, 0x0, 0x0, 0x0, 0x1, 0x8, 0x4, 0x0, 0x0, 0x0, (byte) 0xb5, (byte) 0x1c, 0xc, 0x2, 0x0, 0x0, 0x0, 0xb, (byte) 0x49, (byte) 0x44, (byte) 0x41, (byte) 0x54, (byte) 0x78, (byte) 0xda, (byte) 0x63, (byte) 0x64, (byte) 0x60, 0x0, 0x0, 0x0, 0x6, 0x0, 0x2, (byte) 0x30, (byte) 0x81, (byte) 0xd0, (byte) 0x2f, 0x0, 0x0, 0x0, 0x0, (byte) 0x49, (byte) 0x45, (byte) 0x4e, (byte) 0x44, (byte) 0xae, (byte) 0x42, (byte) 0x60, (byte) 0x82});

    static final ByteArrayResource blankGif =new ByteArrayResource(new byte[]{ 0x47, 0x49, 0x46, 0x38, 0x39, 0x61, 0x1, 0x0, 0x1, 0x0, (byte)0x80, 0x0, 0x0, (byte)0xff, (byte)0xff, (byte)0xff, 0x0, 0x0, 0x0, 0x2c, 0x0, 0x0, 0x0, 0x0, 0x1, 0x0, 0x1, 0x0, 0x0, 0x2, 0x2, 0x44, 0x1, 0x0, 0x3b });

	private ByteArrayResource emptyResource = new ByteArrayResource(new byte[]{});

	@Value("${org.storage.path}")
    private String storagePath;

	@Value("${org.storage.thumbnails.path}")
    private String storageThumbnailsPath;



	public String saveFile(MultipartFile file) {
		String fileName = "";
	try {
		log.info(storagePath +" Multipart >>>>>>>>>>>>>>>>>>>"+file.getOriginalFilename());
		byte[] bytes = file.getBytes();
		do{
			fileName = generateUniqueFileName(FilenameUtils.getExtension(file.getOriginalFilename()));
		}
		while(new File(storagePath,fileName).exists());
		log.debug("Writing File : "+fileName+" . At Path : "+storagePath);
		Files.copy(file.getInputStream(), Paths.get(storagePath).resolve(fileName));
		System.out.println(""+file.getOriginalFilename());
		System.out.println(""+file.getContentType());
		System.out.println(""+file.hashCode());
		/*Path path = Paths.get(uploadPath,file.getOriginalFilename());
		System.out.println(""+file.getOriginalFilename());
		System.out.println(""+file.getContentType());
		System.out.println(""+file.hashCode());
		System.out.println(""+path.getFileName());
		Files.write(path, bytes);
		*/
		//if(file.getOriginalFilename().endsWith("") || )

		if(file.getContentType().startsWith("image/"))
			{
				log.debug("If IMAGE >>>>>>");
				Thumbnails.of(new File(storagePath, fileName)).size(50, 50)
						.toFile(new File(storageThumbnailsPath , "t_"+fileName));
			}
	} catch (IOException e) {
		e.printStackTrace();
	}
		return fileName;
	}



	String generateUniqueFileName(String extension) {
	    String filename = "";
	    long millis = System.currentTimeMillis();
	    String datetime = new Date().toGMTString();
	    datetime = datetime.replace(" ", "");
	    datetime = datetime.replace(":", "");
	    String rndchars = RandomStringUtils.randomAlphanumeric(16);
	    filename = rndchars + "_" + datetime + "_" + millis;
	    return filename+"."+extension;
	}



	public String getStoragePath() {
		return storagePath;
	}



	public void setStoragePath(String storagePath) {
		this.storagePath = storagePath;
	}



	public String getStorageThumbnailsPath() {
		return storageThumbnailsPath;
	}



	public void setStorageThumbnailsPath(String storageThumbnailsPath) {
		this.storageThumbnailsPath = storageThumbnailsPath;
	}


	public Resource loadResource(String fileName) throws MalformedURLException{
			log.debug("LoadResource :>>>>>>>>>>> Storage Path : {}, FileName : {} ",storagePath, fileName);
			return loadResource(new File(storagePath,fileName));

		}

	public Resource loadResource(File file) throws MalformedURLException{
		log.debug("LoadResource :>>>>>>>>>>> Storage Path : {} ",storagePath);

		log.debug("file : "+file.getAbsolutePath() );
		Resource resource = new UrlResource(file.toURI());
		if (!resource.exists() || !resource.isReadable()) {
		    return blankPng;
		    //return blankGif;
		    //return emptyResource;
//            throw new RuntimeException("FAIL!");
        }
		return resource;
	}

	public Resource loadThumbnail(String fileName) throws MalformedURLException{

		return loadResource(new File(storageThumbnailsPath,"t_"+fileName));
	}



}
