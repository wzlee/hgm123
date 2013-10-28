package com.wzlee.hgm123;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sun.org.mozilla.javascript.internal.NativeArray;

import com.wzlee.hgm123.controller.SystemController;
import com.wzlee.hgm123.domain.Advertise;
import com.wzlee.hgm123.domain.Passport;
import com.wzlee.hgm123.repositories.AdvertiseRepository;
import com.wzlee.hgm123.repositories.PassportRepository;
import com.wzlee.hgm123.repositories.YoukuerRepository;
import com.wzlee.hgm123.utils.DateHelper;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations={
        "file:src/main/webapp/WEB-INF/spring/*.xml", 
        "file:src/main/webapp/WEB-INF/spring/appServlet/*.xml"})
public class MongoDBTest {
	private static final Logger logger = LoggerFactory.getLogger(SystemController.class);
	
	@Autowired
	GridFsOperations operations;
	@Autowired 
	AdvertiseRepository advertiseRepository;
	@Autowired 
	PassportRepository passportRepository;
	@Autowired 
	YoukuerRepository youkuerRepository;
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}


    @Test
    public <QAdvertise> void advertiseRepositoryTest() throws ScriptException {
//    	Map<String,String> querytimes = DateHelper.getQueryString();
    	System.out.println(DateHelper.getQueryString(0));
    	List<Advertise> advertises = advertiseRepository.findAdvertiseByOpenTime(DateHelper.getQueryString(0));
    	System.out.println(advertises.size());
//    	Advertise advertise = advertiseRepository.findOne("5188f76907ecc98d1c2ec0dd");
//    	advertise.setGameName("hahha");
//    	advertiseRepository.save(advertise);
//    	List<Passport> passports = passportRepository.findPassportByQqOpenid("72F64B6B05A9ABF5DB1D51DAC0D15DF5");
//    	System.out.println(passports.size());
//    	System.out.println(passportRepository.findPassportByLoginSequenceAndLoginTimestamp("", ""));
//    	System.out.println(advertiseRepository.findOne("51a47b29c1f3df90ad1f0e90"));
    }
	
	@Test
	public void filegridTest(){
//		Resource file = new Resource();
//		operations.store(new File("E:/Cracked.zip")., "text.text");
//		FileMetadata metadata = new FileMetadata();
//	    // populate metadata
//	    Resource file = new File("E:/Cracked.zip"); // lookup File or Resource
//
//	    operations.store(file.getInputStream(), "filename.txt", metadata);
	}
	
	@Test
	public void scriptEngineTest() throws FileNotFoundException, ScriptException, NoSuchMethodException{
		ScriptEngineManager semr = new ScriptEngineManager();
		ScriptEngine se = semr.getEngineByName("JavaScript");
		se.eval(new FileReader("src/main/webapp/html/theAds.js"));
		NativeArray theAds = (NativeArray) se.get("theAds");
		for(int i=0;i<=theAds.getLength();i++){
			logger.info(theAds.get(i, theAds).toString());
		}
	}
	
	public void youkuerTest(){
		System.out.println(youkuerRepository.findYoukuerByName("yk_hgm123"));
	}
}
