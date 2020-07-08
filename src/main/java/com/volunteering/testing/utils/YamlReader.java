package com.volunteering.testing.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class YamlReader {

	
	public static void readvaluefromYamlfile(String token) 
	   {
		   Reader docReader= null;
		try {
			docReader = new FileReader("C:\\Users\\gaurav.malik\\eclipse-workspace\\VolunteeringAutomation\\src\\main\\java\\com\\volunteering\\testing\\testdata\\yaml\\commonTestData.yml");
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		   Yaml ymlobject=new Yaml();
		   Map <String,Object > obj =  (Map <String,Object >) ymlobject.load(docReader);
		  // System.out.println(obj.toString());
		  String  token = "address.zip";
		  String []s=token.split("\\.");
		// System.out.println(s[0]);
		// System.out.println(s[1]);
		// System.out.println("Hello");

		System.out.println(parseMap(obj,token).get(s[s.length-1]).toString());
		  
	   }
	    public static  Map<String ,Object> parseMap(Map<String ,Object>object,String token)
	   {
		   if(token.contains("."))
		   {
				  String []s=token.split("\\.");
				  object=parseMap((Map<String ,Object>) object.get(s[0]),token.replace(s[0]+".",""));

		   }
		   return object;
	   }

}