package com.bridgelabz.toDoApp.util;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bridgelabz.toDoApp.model.WebScrap;

@Component
public class WebScraping {
	@Autowired
	WebScrap scrap;
	
	public  void webScraping(String url) 
	{
		try{
			Document doc = Jsoup.connect(url).get();
			String title = doc.title();
			scrap.setTitle(title);
			System.out.println("web scrap title: " + title);

			Elements links = doc.select("a[href]");
			for (Element link : links) {
				System.out.println("\nlink: " + link.attr("href"));
				System.out.println("text: " + link.text());
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		

	}
}
